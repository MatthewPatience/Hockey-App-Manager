package com.matthewpatience.hockeyappmanager.api.hockeyapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.matthewpatience.hockeyappmanager.api.util.HistogramParser;
import com.matthewpatience.hockeyappmanager.app.Env;
import com.matthewpatience.hockeyappmanager.app.UserState;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.CrashGroup;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.CrashGroups;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Crashes;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Histogram;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Version;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Matthew Patience on 10/26/13.
 */
public class CrashRequests {

    public static final String ENDPOINT_APPS = "/apps/$APP_ID$";
    public static final String ENDPOINT_VERSIONS = "/apps/$APP_ID$/app_versions/$VERSION_ID$";

    public static final String ACTION_CRASH_REASONS = "/crash_reasons";
    public static final String ACTION_CRASHES = "/crashes";
    public static final String ACTION_HISTOGRAM = "/histogram";

    public static final String FORMAT_LOG = "?format=log";
    public static final String FORMAT_TEXT = "?format=text";

    public static HockeyRequest<CrashGroups> createGetAppCrashGroupsRequest(Context context, App app,
            Response.Listener<CrashGroups> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<CrashGroups>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_APPS.replace("$APP_ID$", app.getPublicIdentifier()) +
                        ACTION_CRASH_REASONS + Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<CrashGroups>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<CrashGroups> createGetAppVersionCrashGroupsRequest(Context context, Version version,
            Response.Listener<CrashGroups> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<CrashGroups>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_VERSIONS.replace("$APP_ID$", version.getApp().getPublicIdentifier())
                        .replace("$VERSION_ID$", version.getVersion()) +
                        ACTION_CRASH_REASONS + Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<CrashGroups>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<Crashes> createGetCrashGroupCrashesRequest(Context context, CrashGroup group,
            Response.Listener<Crashes> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<Crashes>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_APPS.replace("$APP_ID$", group.getApp().getPublicIdentifier()) +
                        ACTION_CRASH_REASONS + "/" + group.getId() + Env.RESPONSE_FORMAT,
                UserState.getInstance(context).getToken(),
                new TypeToken<Crashes>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<String> createDownloadLogRequest(Context context, CrashGroup group,
            Response.Listener<String> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<String>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_APPS.replace("$APP_ID$", group.getApp().getPublicIdentifier()) +
                        ACTION_CRASHES + "/" + group.getId() + FORMAT_LOG,
                UserState.getInstance(context).getToken(),
                new TypeToken<String>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<String> createDownloadDescriptionRequest(Context context, CrashGroup group,
            Response.Listener<String> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<String>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_APPS.replace("$APP_ID$", group.getApp().getPublicIdentifier()) +
                        ACTION_CRASHES + "/" + group.getId() + FORMAT_TEXT,
                UserState.getInstance(context).getToken(),
                new TypeToken<String>(){}.getType(),
                listener, errorListener);
    }

    public static HockeyRequest<Histogram> createGetAppHistogramRequest(Context context, App app,
            Date startDate, Date endDate,
            Response.Listener<Histogram> listener, Response.ErrorListener errorListener) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateFormatted = sdf.format(startDate);
        String endDateFormatted = sdf.format(endDate);

        HockeyRequest<Histogram> request = new HockeyRequest<Histogram>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_APPS.replace("$APP_ID$", app.getPublicIdentifier()) +
                        ACTION_CRASHES + ACTION_HISTOGRAM + Env.RESPONSE_FORMAT +
                        "&start_date=" + startDateFormatted + "&end_date=" + endDateFormatted,
                UserState.getInstance(context).getToken(),
                new TypeToken<Histogram>(){}.getType(),
                listener, errorListener);
        request.setCustomParser(new HistogramParser());

        return request;
    }

    public static HockeyRequest<Histogram> createGetVersionHistogramRequest(Context context, Version version,
            Date startDate, Date endDate,
            Response.Listener<Histogram> listener, Response.ErrorListener errorListener) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateFormatted = sdf.format(startDate);
        String endDateFormatted = sdf.format(endDate);

        HockeyRequest<Histogram> request = new HockeyRequest<Histogram>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_VERSIONS.replace("$APP_ID$", version.getApp().getPublicIdentifier())
                        .replace("$VERSION_ID$", version.getVersion()) +
                        ACTION_CRASHES + ACTION_HISTOGRAM + Env.RESPONSE_FORMAT +
                        "&start_date=" + startDateFormatted + "&end_date=" + endDateFormatted,
                UserState.getInstance(context).getToken(),
                new TypeToken<Histogram>(){}.getType(),
                listener, errorListener);
        request.setCustomParser(new HistogramParser());

        return request;
    }

    public static HockeyRequest<Histogram> createGetCrashGroupHistogramRequest(Context context, CrashGroup group,
            Date startDate, Date endDate,
            Response.Listener<Histogram> listener, Response.ErrorListener errorListener) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateFormatted = sdf.format(startDate);
        String endDateFormatted = sdf.format(endDate);

        HockeyRequest<Histogram> request = new HockeyRequest<Histogram>(Request.Method.GET,
                Env.BASE_URL + ENDPOINT_APPS.replace("$APP_ID$", group.getApp().getPublicIdentifier()) +
                        ACTION_CRASH_REASONS + "/" + group.getId() +
                        ACTION_HISTOGRAM + Env.RESPONSE_FORMAT +
                        "&start_date=" + startDateFormatted + "&end_date=" + endDateFormatted,
                UserState.getInstance(context).getToken(),
                new TypeToken<Histogram>(){}.getType(),
                listener, errorListener);
        request.setCustomParser(new HistogramParser());

        return request;
    }

    public static HockeyRequest<Crashes> createUpdateCrashGroupStatusRequest(Context context, CrashGroup group,
            Response.Listener<Crashes> listener, Response.ErrorListener errorListener) {

        return new HockeyRequest<Crashes>(Request.Method.POST,
                Env.BASE_URL + ENDPOINT_APPS.replace("$APP_ID$", group.getApp().getPublicIdentifier()) +
                        ACTION_CRASH_REASONS + "/" + group.getId() + Env.RESPONSE_FORMAT +
                        "&status=" + group.getStatus(),
                UserState.getInstance(context).getToken(),
                new TypeToken<Crashes>(){}.getType(),
                listener, errorListener);
    }

}
