package com.matthewpatience.hockeyappmanager.api.hockeyapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.matthewpatience.hockeyappmanager.app.Env;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Apps;
import com.matthewpatience.hockeyappmanager.app.UserState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpatience on 10/26/13.
 */
public class AppRequests {

    public static final String ENDPOINT_APPS = "/apps";

    public static final String ACTION_NEW = "/new";
    public static final String ACTION_UPLOAD = "/upload";

    public static HockeyRequest<Apps> createGetAppsRequest(Context context,
            Response.Listener<Apps> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<Apps>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_APPS + Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<Apps>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<App> createNewAppRequest(Context context, App app,
            Response.Listener<App> listener, Response.ErrorListener errorListener) {

        HockeyRequest<App> request = new HockeyRequest<App>(Request.Method.POST,
                Env.BASE_URL + ENDPOINT_APPS + ACTION_NEW + Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<App>(){}.getType(),
                listener, errorListener);

        Map<String, String> params = new HashMap<String, String>();
        params.put("title", app.getTitle());
        params.put("bundle_identifier", app.getBundleIdentifier());
        params.put("platform", app.getPlatform());
        params.put("release_type", String.valueOf(app.getReleaseType()));
        request.setParams(params);

        return request;
    }

    public static HockeyRequest<String> createDeleteAppRequest(Context context, App app,
            Response.Listener<String> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<String>(Request.Method.DELETE,
                Env.BASE_URL + ENDPOINT_APPS + "/" + app.getPublicIdentifier() + Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<String>(){}.getType(),
                listener, errorListener);
    }

}
