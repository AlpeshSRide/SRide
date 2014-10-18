package com.sride;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.slidingmenu.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sride.home.HomeFragment;
import com.sride.trips.TripsFragment;

public class BaseActivity extends SlidingFragmentActivity implements
		OnClickListener {

	private SlidingMenu mSlidingMenu;

	private TextView mbtnHome, mbtnDashboard, mbtnTrips, mbtnRequests,
			mbtnProfile, mbtnPayment, mbtnLogout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSlidingMenu();
		getLayoutReferences();
		setOnClickListeners();
		loadHomeInitialFragment();

	}

	private void setOnClickListeners() {
		mbtnHome.setOnClickListener(this);
		mbtnDashboard.setOnClickListener(this);
		mbtnTrips.setOnClickListener(this);
		mbtnRequests.setOnClickListener(this);
		mbtnProfile.setOnClickListener(this);
		mbtnPayment.setOnClickListener(this);
		mbtnLogout.setOnClickListener(this);
	}

	private void getLayoutReferences() {
		mbtnHome = (TextView) findViewById(R.id.txtHome);
		mbtnDashboard = (TextView) findViewById(R.id.txtDashboard);
		mbtnTrips = (TextView) findViewById(R.id.txtTrips);
		mbtnRequests = (TextView) findViewById(R.id.txtRequests);
		mbtnProfile = (TextView) findViewById(R.id.txtProfile);
		mbtnPayment = (TextView) findViewById(R.id.txtPayment);
		mbtnLogout = (TextView) findViewById(R.id.txtLogout);
	}

	private void setSlidingMenu() {
		setBehindContentView(R.layout.sliding_layout);
		setSlidingActionBarEnabled(true);
		setContentView(R.layout.base_layout);

		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setShadowWidthRes(R.dimen.slider_shadow_width);
		mSlidingMenu.setShadowDrawable(R.drawable.slider_shadow);
		mSlidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txtHome:
			btnHomeClicked();
			break;
		case R.id.txtDashboard:
			btnHomeClicked();
			break;
		case R.id.txtTrips:
			btnTripsClicked();
			break;
		case R.id.txtRequests:
			btnHomeClicked();
			break;
		case R.id.txtProfile:
			btnHomeClicked();
			break;
		case R.id.txtPayment:
			btnHomeClicked();
			break;
		case R.id.txtLogout:
			btnHomeClicked();
			break;
		default:
			break;
		}
	}

	private void btnTripsClicked() {
		mSlidingMenu.toggle();
		TripsFragment mObjFragment = new TripsFragment();
		clearStack();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.home_container, mObjFragment)
				.commitAllowingStateLoss();
	}

	public void clearStack() {
		for (int i = 0; i < getSupportFragmentManager()
				.getBackStackEntryCount(); ++i) {
			getSupportFragmentManager().popBackStack();
		}
	}

	private void btnHomeClicked() {
		mSlidingMenu.toggle();
		mbtnHome.setSelected(true);
		HomeFragment mObjFragment = new HomeFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.home_container, mObjFragment)
				.commitAllowingStateLoss();
	}

	private void loadHomeInitialFragment() {
		mbtnHome.setSelected(true);
		HomeFragment mObjFragment = new HomeFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.home_container, mObjFragment)
				.commitAllowingStateLoss();
	}

	public void toggleMenu() {
		mSlidingMenu.toggle();
	}

	public void setSlidingTouchControl() {
		if (mSlidingMenu != null)
			mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}

}
