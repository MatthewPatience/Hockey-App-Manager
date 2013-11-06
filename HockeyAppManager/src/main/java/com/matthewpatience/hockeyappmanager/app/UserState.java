package com.matthewpatience.hockeyappmanager.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mpatience on 10/26/13.
 */
public class UserState {

    public static UserState instance;

    private String token;
    private String email;

    private UserState(Context context) {

        loadUserState(context);

    }

    private void loadUserState(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        token = prefs.getString(PrefsKey.TOKEN, null);
        email = prefs.getString(PrefsKey.EMAIL, null);

    }

    public static UserState getInstance(Context context) {

        if (instance == null) {
            instance = new UserState(context);
        }

        return instance;
    }

    public boolean isSignedIn() {

        if (token != null) {
            return true;
        }

        return false;
    }

    public String getToken() {

        return token;
    }

    public String getEmail() {

        return email;
    }

    public void saveToken(Context context, String token) {

        this.token = token;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(PrefsKey.TOKEN, token).commit();

    }

    public void saveEmail(Context context, String email) {

        this.email = email;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(PrefsKey.EMAIL, email).commit();

    }

}
