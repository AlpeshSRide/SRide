package com.sride.home;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.sride.BaseActivity;
import com.sride.BaseFragment;
import com.sride.R;
import com.utils.StaticData;
import com.utils.StaticUtils;
import com.utils.map.MapWrapperLayout;
import com.utils.map.MySupportMapFragment;

public class HomeFragment extends BaseFragment implements OnClickListener,
		OnCameraChangeListener {

	private View mCurrentView;

	private GoogleMap mapView;
	private MapWrapperLayout mapWrapperLayout;
	private LatLng currentLatlng = null;
	private SupportMapFragment mapSupportFragment;

	private ImageView mbtnMenu;

	private TextView mbtnDriver, mbtnRider, mtxtTitle;
	private EditText meditSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mCurrentView = inflater.inflate(R.layout.home_layout, null);
		getLayoutReferences();
		setClickListeners();
		return mCurrentView;
	}

	private void setClickListeners() {
		mbtnMenu.setOnClickListener(this);
		mbtnDriver.setOnClickListener(this);
		mbtnRider.setOnClickListener(this);
		btnDriverClicked();
	}

	private void getLayoutReferences() {
		mbtnMenu = (ImageView) mCurrentView.findViewById(R.id.btnMenu);
		mbtnDriver = (TextView) mCurrentView.findViewById(R.id.txtDriver);
		mbtnRider = (TextView) mCurrentView.findViewById(R.id.txtRider);
		mtxtTitle = (TextView) mCurrentView.findViewById(R.id.txtTitle);
		meditSearch = (EditText) mCurrentView.findViewById(R.id.editSearch);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initializeMap();
		setTitle();

	}

	private void setTitle() {
		mtxtTitle.setText(R.string.create_trip);
		StaticUtils.setEditTextHintFont(meditSearch, getActivity(), 0);
	}

	public void setMap(GoogleMap map) {
		this.mapView = map;
		mapView.getUiSettings().setMyLocationButtonEnabled(true);
		mapView.getUiSettings().setCompassEnabled(true);
		mapWrapperLayout = (MapWrapperLayout) mCurrentView
				.findViewById(R.id.map_relative_layout);
		mapWrapperLayout.init(getMap(), getPixelsFromDp(getActivity(), -20));
		initMap();
	}

	public void initMap() {
		mapView.setOnCameraChangeListener(this);
	}

	public GoogleMap getMap() {
		return mapView;
	}

	public static int getPixelsFromDp(Context context, float dp) {
		return (int) (dp * StaticData.SCALE + 0.5f);
	}

	public void onLocatinChange(Location location) {
		if (location != null && getMap() != null) {
			currentLatlng = new LatLng(location.getLatitude(),
					location.getLongitude());
			animateToLocation(currentLatlng);
		}
	}

	private void animateToLocation(LatLng mLatLng) {
		CameraPosition.Builder builder = new CameraPosition.Builder();
		builder.zoom(15);
		builder.target(mLatLng);
		getMap().animateCamera(
				CameraUpdateFactory.newCameraPosition(builder.build()));
	}

	private void initializeMap() {
		try {
			MapsInitializer.initialize(getActivity());
			if (getChildFragmentManager().findFragmentById(R.id.mapContainer) == null) {
				mapSupportFragment = MySupportMapFragment
						.newInstatnce(new Bundle());
				mapSupportFragment.setTargetFragment(this, 100);
				if (getArguments() != null)
					mapSupportFragment.setArguments(getArguments());

				getChildFragmentManager().beginTransaction()
						.add(R.id.mapContainer, mapSupportFragment).commit();
			}
		} catch (GooglePlayServicesNotAvailableException e) {
			GooglePlayServicesUtil
					.getOpenSourceSoftwareLicenseInfo(getActivity());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMenu:
			btnMenuClicked();
			break;
		case R.id.txtDriver:
			btnDriverClicked();
			break;
		case R.id.txtRider:
			btnRiderClicked();
			break;
		default:
			break;
		}
	}

	private void btnRiderClicked() {
		mbtnRider.setSelected(true);
		mbtnDriver.setSelected(false);
	}

	private void btnDriverClicked() {
		mbtnRider.setSelected(false);
		mbtnDriver.setSelected(true);
	}

	private void btnMenuClicked() {
		((BaseActivity) getActivity()).toggleMenu();
	}

	@Override
	public void onCameraChange(CameraPosition arg0) {
		LatLng center = mapView.getCameraPosition().target;
		getLocationName(center);

	}

	private void getLocationName(LatLng center) {
		Geocoder geocoder;
		List<Address> addresses;
		geocoder = new Geocoder(getActivity(), Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(center.latitude,
					center.longitude, 1);
			if (addresses.size() != 0) {
				String address = addresses.get(0).getAddressLine(0);
				String city = addresses.get(0).getAddressLine(1);
				meditSearch.setText(address + "," + city);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
