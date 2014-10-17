package com.sride.home;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.sride.BaseFragment;
import com.sride.R;
import com.utils.StaticData;
import com.utils.map.MapWrapperLayout;
import com.utils.map.MySupportMapFragment;

public class HomeFragment extends BaseFragment {

	private View mCurrentView;
	private GoogleMap mapView;
	private MapWrapperLayout mapWrapperLayout;
	private LatLng currentLatlng = null;
	private SupportMapFragment mapSupportFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mCurrentView = inflater.inflate(R.layout.home_layout, null);
		return mCurrentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initializeMap();
	}

	public void setMap(GoogleMap map) {
		this.mapView = map;
		initMap();
		mapView.getUiSettings().setMyLocationButtonEnabled(true);
		mapView.getUiSettings().setCompassEnabled(true);

		mapWrapperLayout = (MapWrapperLayout) mCurrentView
				.findViewById(R.id.map_relative_layout);
		mapWrapperLayout.init(getMap(), getPixelsFromDp(getActivity(), -20));

	}

	public void initMap() {
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

}
