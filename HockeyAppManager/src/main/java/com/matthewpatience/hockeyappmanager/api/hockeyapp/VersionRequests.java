package com.matthewpatience.hockeyappmanager.api.hockeyapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.matthewpatience.hockeyappmanager.app.Env;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Version;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Versions;
import com.matthewpatience.hockeyappmanager.app.UserState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpatience on 10/26/13.
 */
public class VersionRequests {

    public static final String ENDPOINT_VERSIONS = "/apps/$APP_ID$/app_versions";

    public static final String ACTION_STATISTICS = "/statistics";
    public static final String ACTION_UPLOAD = "/upload";

    public static HockeyRequest<Versions> createGetVersionsRequest(Context context, App app,
            Response.Listener<Versions> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<Versions>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_VERSIONS.replace("$APP_ID$", app.getPublicIdentifier()) +
                        Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<Versions>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<Versions> createGetStatisticsRequest(Context context, App app,
            Response.Listener<Versions> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<Versions>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_VERSIONS.replace("$APP_ID$", app.getPublicIdentifier()) +
                        ACTION_STATISTICS + Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<Versions>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<String> createUpdateVersionRequest(Context context, Version version,
            Response.Listener<String> listener, Response.ErrorListener errorListener) {

        HockeyRequest<String> request = new HockeyRequest<String>(Request.Method.PUT,
                Env.BASE_URL + ENDPOINT_VERSIONS.replace("$APP_ID$",
                        version.getApp().getPublicIdentifier()) + "/" + version.getVersion() +
                        Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<String>(){}.getType(),
                listener, errorListener);

        Map<String, String> params = new HashMap<String, String>();
        params.put("notes", version.getNotes());
        params.put("notes_type", "0");
        request.setParams(params);

        return request;
    }

    public static HockeyRequest<String> createDeleteVersionRequest(Context context, Version version,
            Response.Listener<String> listener, Response.ErrorListener errorListener) {

        HockeyRequest<String> request = new HockeyRequest<String>(Request.Method.DELETE,
                Env.BASE_URL + ENDPOINT_VERSIONS.replace("$APP_ID$",
                        version.getApp().getPublicIdentifier()) + "/" + version.getVersion() +
                        Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<String>(){}.getType(),
                listener, errorListener);

        return request;
    }

}
