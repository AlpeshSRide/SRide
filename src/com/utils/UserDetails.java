package com.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserDetails {

	public static final String PREF_NAME = "user_data";
	private static UserDetails instance;
	private SharedPreferences sh;
	private Editor edit;

	private UserDetails(Context mContext) {
		sh = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		edit = sh.edit();
	}

	public static synchronized UserDetails getInstance(Context mContext) {
		if (instance == null) {
			instance = new UserDetails(mContext);
		}
		return instance;
	}

	public void setUserName(String membertype) {
		edit.putString("username", membertype).commit();
	}

	public String getUserName() {
		return sh.getString("username", "");
	}

	public void setUserAvatar(String membertype) {
		edit.putString("useravatar", membertype).commit();
	}

	public String getUserAvatar() {
		return sh.getString("useravatar", "");
	}

	public void setUserMiddleName(String membertype) {
		edit.putString("usermiddlename", membertype).commit();
	}

	public String getUserMiddleName() {
		return sh.getString("usermiddlename", "");
	}

	public void setUserFirstName(String membertype) {
		edit.putString("usermiddlename", membertype).commit();
	}

	public String getUserFirstName() {
		return sh.getString("usermiddlename", "");
	}

	public void setUserLastName(String membertype) {
		edit.putString("usermiddlename", membertype).commit();
	}

	public String getUserLastName() {
		return sh.getString("usermiddlename", "");
	}

	public void setUserFullName(String membertype) {
		edit.putString("userfullname", membertype).commit();
	}

	public String getUserFullName() {
		return sh.getString("userfullname", "");
	}

	public void setLoginName(String membertype) {
		edit.putString("loginname", membertype).commit();
	}

	public String getLoginName() {
		return sh.getString("loginname", "");
	}

	public void setAutoLogin(boolean membertype) {
		edit.putBoolean("auto_login", membertype).commit();
	}

	public boolean getAutoLogin() {
		return sh.getBoolean("auto_login", false);
	}

	public int getUserId() {
		return sh.getInt("userid", 0);
	}

	public void setUserId(int userid) {
		edit.putInt("userid", userid).commit();
	}

	public void clear() {
		edit.clear().commit();
	}

	public void setRemeber(boolean isflag) {
		edit.putBoolean("remember", isflag).commit();
	}

	public boolean isRemember() {
		return sh.getBoolean("remember", false);
	}

	public String getUserEmail() {
		return sh.getString("useremail", "");
	}

	public void setUserEmail(String useremail) {
		edit.putString("useremail", useremail).commit();
	}

	public String getPassword() {
		return sh.getString("password", "");
	}

	public String getPersonalphoneno() {
		return sh.getString("personal_phone_no", "");
	}

	public void setPassword(String password) {
		edit.putString("password", password).commit();
	}

	public void setPersonalphoneno(String personal_phone_no) {
		edit.putString("personal_phone_no", personal_phone_no).commit();
	}

	public void setDeviceToken(String device_token) {
		edit.putString("device_token", device_token).commit();
	}

	public String getDeviceToken() {
		return sh.getString("device_token", "");
	}

	public void setWorkphoneno(String work_phone_no) {
		edit.putString("work_phone_no", work_phone_no).commit();
	}

	public String getWorkphoneno() {
		return sh.getString("work_phone_no", "");
	}

}