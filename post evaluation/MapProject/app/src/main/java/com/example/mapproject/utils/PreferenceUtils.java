package com.example.mapproject.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mapproject.model.User;

public class PreferenceUtils {
    private static Context mCtx;
    private static PreferenceUtils mInstance;
    private static final String SHARED_PREF_NAME = "mysharedpref12";

    public PreferenceUtils(){
    }
    private PreferenceUtils(Context context) {
        mCtx = context;

    }



    public static Boolean saveEmail (String email, Context context ){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_EMAIL, email);
        prefsEditor.apply();
        return true;
    }

    public static Boolean isLoggedIn(User user){
      //  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(user.getFirstName() != null){
            return true;
        }
        return false;
    }

    public static void loggedout ( Context context ){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.clear();

        prefsEditor.apply();
    }

    public static String getEmail(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_EMAIL, null);
    }

    public static Boolean savePassword (String password, Context context ){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_PASSWORD, password);
        prefsEditor.apply();
        return true;
    }
    public static String getPassword(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_PASSWORD, null);
    }

}
