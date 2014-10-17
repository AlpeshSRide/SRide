package com.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

	private Map<String, Typeface> fontCache = new HashMap<String, Typeface>();
	private static FontManager instance = null;
	private Context mContext;

	/*
	 * 
	 * Constructor
	 */

	private FontManager(Context mContext2) {
		mContext = mContext2;
	}

	/*
	 * 
	 * Single Ton Instance for the Class
	 */

	public synchronized static FontManager getInstance(Context mContext) {
		if (instance == null) {
			instance = new FontManager(mContext);
		}
		return instance;
	}

	/*
	 * 
	 * Method for Loading the font type that which you have Specified
	 */

	public Typeface loadFont(String font) {
		if (false == fontCache.containsKey(font)) {
			fontCache.put(font,
					Typeface.createFromAsset(mContext.getAssets(), font));
		}
		return fontCache.get(font);
	}
}
