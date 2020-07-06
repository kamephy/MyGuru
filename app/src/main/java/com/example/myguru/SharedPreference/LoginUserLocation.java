package com.example.myguru.SharedPreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class LoginUserLocation {

    private SharedPreferences prefUser;
    private SharedPreferences.Editor editor;
    private Context _context;

    // shared pref mode
    private int PRIVATE_MODE = 0;

    //shared preference file name
    private static final String LOCATION_PREF = "user_location";  // replace with name of the shared preference

    private static final String USER_LOCATION = "user_Location";  // title of the stored shared preference

    @SuppressLint("CommitPrefEdits")
    public LoginUserLocation(Context context) {
        this._context = context;
        prefUser = _context.getSharedPreferences(LOCATION_PREF, PRIVATE_MODE);
        editor = prefUser.edit();
    }

    public boolean saveUserLocation(String location) {
        editor.putString(USER_LOCATION, location);
        if (editor.commit())
            return true;
        else
            return false;
    }

    public String getUserLocation() {
        return prefUser.getString(USER_LOCATION, "");
    }


    public boolean clearUserLocation() {
        editor.clear();
        if (editor.commit())
            return true;
        else
            return false;
    }


}
