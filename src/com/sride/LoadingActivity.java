package com.sride;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.sride.home.BaseActivity;
import com.utils.StaticData;

public class LoadingActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		initiateHandler();

	}

	/*
	 * 
	 * Initiating Handler
	 */
	private void initiateHandler() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				navigateToHomeActivity();
			}

		}, StaticData.SPLASH_DURATION);
	}

	private void navigateToHomeActivity() {
		Intent mIntent = new Intent(this, BaseActivity.class);
		startActivity(mIntent);
		finish();
	}

}
