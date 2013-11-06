package com.matthewpatience.hockeyappmanager.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by mpatience on 10/22/13.
 */
public class Env {

    public static final boolean DEBUG = true;
    public static final String TAG_HTTP_REQUEST = "HAM-Network";

    public static String API_VERSION = "2";
    public static String BASE_URL = "https://rink.hockeyapp.net/api/" + API_VERSION;
    public static String RESPONSE_FORMAT = "?format=json";


    public static void log(String tag, String message) {

        if (DEBUG) Log.d(tag, message);

    }

    public static void logWarning(String tag, String message) {

        if (DEBUG) Log.w(tag, message);

    }

    public static void logError(String tag, String message) {

        if (DEBUG) Log.e(tag, message);

    }

    public static void logError(String tag, String message, Exception e) {

        if (DEBUG) Log.e(tag, message, e);

    }

    public static boolean isNetworkOnline(Context context) {

        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return status;

    }

}
