package com.sride.trips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sride.BaseFragment;
import com.sride.R;

public class TripsFragment extends BaseFragment {

	private View mCurrentView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mCurrentView = inflater.inflate(R.layout.trips_layout, null);
		return mCurrentView;
	}

}
