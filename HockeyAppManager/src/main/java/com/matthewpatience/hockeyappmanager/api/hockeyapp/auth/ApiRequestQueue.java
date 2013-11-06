package com.matthewpatience.hockeyappmanager.api.hockeyapp.auth;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mpatience on 10/26/13.
 */
public class ApiRequestQueue {

    private static ApiRequestQueue instance;
    private RequestQueue queue;

    private ApiRequestQueue(Context context) {

        queue = Volley.newRequestQueue(context);

    }

    public static ApiRequestQueue getInstance(Context context) {

        if (instance == null) {
            instance = new ApiRequestQueue(context);
        }

        return instance;
    }

    public RequestQueue getQueue() {

        return queue;
    }

}
