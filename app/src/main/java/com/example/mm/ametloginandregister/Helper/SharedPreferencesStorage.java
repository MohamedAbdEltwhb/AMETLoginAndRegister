package com.example.mm.ametloginandregister.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.mm.ametloginandregister.Helper.Constants.KEY_EMAIL;
import static com.example.mm.ametloginandregister.Helper.Constants.KEY_PASSWORD;

public class SharedPreferencesStorage {

    public static boolean saveEmail(String email, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_EMAIL , email);
        editor.apply();
        return true;
    }

    public static boolean savePassword(String password, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
        return true;
    }

    public static String getEmail(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String email = preferences.getString(KEY_EMAIL, null);
        return email;
    }

    public static String getPassword(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String password = preferences.getString(KEY_PASSWORD, null);
        return password;
    }
}
