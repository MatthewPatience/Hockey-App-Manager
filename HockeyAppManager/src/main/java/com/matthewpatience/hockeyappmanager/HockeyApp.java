package com.matthewpatience.hockeyappmanager;

import android.app.Application;

import com.android.volley.VolleyLog;

/**
 * Created by mpatience on 10/23/13.
 */
public class HockeyApp extends Application {

    private static final String VOLLEY_LOG_TAG = "HAM-Volley";

    @Override
    public void onCreate() {
        super.onCreate();

        VolleyLog.setTag(VOLLEY_LOG_TAG);

    }

}
