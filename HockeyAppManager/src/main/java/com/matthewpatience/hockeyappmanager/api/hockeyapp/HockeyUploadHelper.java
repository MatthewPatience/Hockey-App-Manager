package com.matthewpatience.hockeyappmanager.api.hockeyapp;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.matthewpatience.hockeyappmanager.api.GsonParser;
import com.matthewpatience.hockeyappmanager.app.Env;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Version;
import com.matthewpatience.hockeyappmanager.app.UserState;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Matthew Patience on 10/27/13.
 */
public class HockeyUploadHelper {

    public static App uploadNewApp(Context context, Version version,
            boolean notify, File ipa) throws IOException {

        //Create the request
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Env.BASE_URL + AppRequests.ENDPOINT_APPS +
                AppRequests.ACTION_UPLOAD + Env.RESPONSE_FORMAT);
        httpPost.addHeader(HockeyRequest.HEADER_APPTOKEN, UserState.getInstance(context).getToken());

        //Form the request body
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody("ipa", ipa)
                .addTextBody("notes", version.getNotes())
                .addTextBody("notes_type", "0")
                .addTextBody("notify", notify ? "1" : "0")
                .addTextBody("status", String.valueOf(version.getStatus()))
                .addTextBody("mandatory", String.valueOf(version.isMandatory()));
        httpPost.setEntity(builder.build());

        //Execute the request
        HttpResponse response = httpClient.execute(httpPost);

        //Parse the response
        InputStream is = response.getEntity().getContent();
        InputStreamReader reader = new InputStreamReader(is);
        App app = GsonParser.getInstance().getGson().fromJson(reader, new TypeToken<App>(){}.getType());

        return app;
    }

    public static Version uploadNewVersion(Context context, Version version,
                                   boolean notify, File ipa) throws IOException {

        //Create the request
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Env.BASE_URL +
                VersionRequests.ENDPOINT_VERSIONS.replace("$APP_ID$", version.getApp().getPublicIdentifier()) +
                VersionRequests.ACTION_UPLOAD + Env.RESPONSE_FORMAT);
        httpPost.addHeader(HockeyRequest.HEADER_APPTOKEN, UserState.getInstance(context).getToken());

        //Form the request body
        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody("ipa", ipa)
                .addTextBody("notes", version.getNotes())
                .addTextBody("notes_type", "0")
                .addTextBody("notify", notify ? "1" : "0")
                .addTextBody("status", String.valueOf(version.getStatus()))
                .addTextBody("mandatory", String.valueOf(version.isMandatory()));
        httpPost.setEntity(builder.build());

        //Execute the request
        HttpResponse response = httpClient.execute(httpPost);

        //Parse the response
        InputStream is = response.getEntity().getContent();
        InputStreamReader reader = new InputStreamReader(is);
        Version newVersion = GsonParser.getInstance().getGson().fromJson(reader, new TypeToken<Version>(){}.getType());

        return newVersion;
    }

}
