package com.matthewpatience.hockeyappmanager.api.hockeyapp.auth;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;

import com.matthewpatience.hockeyappmanager.api.util.Base64;
import com.matthewpatience.hockeyappmanager.app.Env;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by mpatience on 10/23/13.
 */
public class LoginTask extends AsyncTask<String, String, String> {

    public interface OnLoginListener {
        public abstract void onSuccessfulLogin(String token);
        public abstract void onFailedLogin(int status);
    }

    public void setOnLoginListener(OnLoginListener listener) {
        this.listener = listener;
    }

    private boolean finished;
    private int status = OnlineHelper.STATUS_UNKNOWN_ERROR;
    private Activity activity;
    private String credentials;
    private String token;
    private String accessToken;
    private OnLoginListener listener;

    public LoginTask(Activity activity, String email, String password) {
        this.activity = activity;
        this.credentials = Base64.encodeBytes((email + ":" + password).getBytes()).trim();
        this.finished = false;
        this.token = null;
    }

    public LoginTask(Activity activity, String accessToken) {
        this.activity = activity;
        this.accessToken = accessToken;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            JSONArray tokens = getTokens();
            return findToken(tokens, true);
        } catch (IOException e) {
            status = OnlineHelper.STATUS_NETWORK_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String findToken(JSONArray tokens, boolean create) throws IOException, JSONException {
        if (tokens == null) {
            return null;
        } else if ((tokens.length() == 0) && (create)) {
            return findToken(createToken(), false);
        } else {
            for (int index = 0; index < tokens.length(); index++) {
                JSONObject token = tokens.getJSONObject(index);
                if ((token.has("rights")) && (token.getInt("rights") == 0)) {
                    return token.getString("token");
                }
            }

            return findToken(createToken(), false);
        }
    }

    private JSONArray createToken() throws IOException, JSONException {
        URL url = new URL(Env.BASE_URL + OnlineHelper.AUTH_ACTION);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        addCredentialsToConnection(connection);

        String parameters = "rights=0";
        connection.setFixedLengthStreamingMode(parameters.getBytes().length);

        PrintWriter out = new PrintWriter(connection.getOutputStream());
        out.print(parameters);
        out.close();

        if (connection.getResponseCode() == 201) {
            String jsonString = OnlineHelper.getStringFromConnection(connection);
            return parseJSONFromString(jsonString);
        } else {
            return null;
        }
    }

    private JSONArray getTokens() throws IOException, JSONException {
        URL url = new URL(getURLString());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        addCredentialsToConnection(connection);
        connection.connect();

        if (connection.getResponseCode() == 200) {
            String jsonString = OnlineHelper.getStringFromConnection(connection);
            return parseJSONFromString(jsonString);
        } else {
            status = OnlineHelper.STATUS_LOGIN_ERROR;
            return null;
        }
    }

    private String getURLString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Env.BASE_URL);
        builder.append(OnlineHelper.AUTH_ACTION);

        String deviceIdentifier = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (deviceIdentifier != null) {
            builder.append("&udid=" + encodeParam(Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID)));
        }

        builder.append("&os=Android");
        builder.append("&os_version=" + encodeParam(String.valueOf(Build.VERSION.SDK_INT)));
        builder.append("&device=" + encodeParam(Build.MODEL));
        builder.append("&oem=" + encodeParam(Build.MANUFACTURER));

        return builder.toString();
    }

    private String encodeParam(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            // UTF-8 should be available, so just in case
            return "";
        }
    }

    private JSONArray parseJSONFromString(String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        if ((json.has("status")) && (json.get("status").equals("success"))) {
            return (JSONArray)json.get("tokens");
        }
        else {
            status = OnlineHelper.STATUS_LOGIN_ERROR;
            return null;
        }
    }

    private void addCredentialsToConnection(HttpURLConnection connection) {
        connection.addRequestProperty("User-Agent", "Hockey/Android");
        if (accessToken != null) {
            connection.setRequestProperty("X-FacebookAccessToken", this.accessToken);
        }
        else {
            connection.setRequestProperty("Authorization", "Basic " + this.credentials);
            connection.setRequestProperty("connection", "close");
        }
    }

    @Override
    protected void onPostExecute(String token) {
        this.token = token;
        if ((activity == null) || (activity.isFinishing())) {
            this.finished = true;
        } else {
            handleResult();
        }
    }

    private void handleResult() {

        if (listener == null) return;

        if (this.token == null) {
            listener.onFailedLogin(status);
        } else {
            listener.onSuccessfulLogin(token);
        }
    }
}
