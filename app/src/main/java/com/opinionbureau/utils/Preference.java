package com.opinionbureau.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private static Preference mInstance = null;
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static String SharedPreferenceKey = "OpinionBureau"   ;

    private Preference() {
    }

    public static Preference getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preference();
        }
        if (mPreferences == null) {
            mPreferences = context.getApplicationContext().getSharedPreferences(SharedPreferenceKey, Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();
            mEditor.commit();
        }
        return mInstance;
    }

    public void saveInPreference(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getFromPreference(String key) {
        return mPreferences.getString(key, "");
    }

    public void setRefresh(String key, boolean status) {
        mPreferences.edit().putBoolean(key, status).apply();
    }
    public boolean getRefresh(String key) {
        return mPreferences.getBoolean(key, false);
    }
}



//  HOW TO USE
//  Preference.getInstance(this).saveInPreference("dev","DEVENDRA");
//                Preference.getInstance(this).getFromPreference("dev");
//                System.out.println( "DEV "+Preference.getInstance(this).getFromPreference("dev"));