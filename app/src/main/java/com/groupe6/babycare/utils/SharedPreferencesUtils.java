package com.groupe6.babycare.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    private static final String PREF_NAME = "MyPrefs";

    private static SharedPreferences sharedPreferences;

    private static SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils();

    public static SharedPreferencesUtils getInstance(Context context) {
        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferencesUtils;
    }

    public void store(String key, String value) {
        if(sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }


    public String getValue(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void clairValue(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }




}
