package com.matthewpatience.hockeyappmanager.api.hockeyapp;

import com.android.volley.Response;
import com.matthewpatience.hockeyappmanager.api.request.BaseRequest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew Patience on 10/24/13.
 *
 * A Request class that includes all of the parsing benefits of BaseRequest
 * but also inserts the appropriate HockeyApp API requirements such as App
 * Token.
 */
public class HockeyRequest<T> extends BaseRequest<T> {

    public static final String HEADER_APPTOKEN = "X-HockeyAppToken";

    private String appToken;
    private Map<String, String> params;
    private Map<String, String> headers;

    /**
     *
     * @param method
     * @param url
     * @param appToken HockeyApp app token, may be null
     * @param typeReference
     * @param listener
     * @param errorListener
     */
    public HockeyRequest(int method, String url, String appToken, Type typeReference, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, typeReference, listener, errorListener);

        this.appToken = appToken;

    }

    public void setParams(Map<String, String> params) {

        this.params = params;

    }

    public void setHeaders(Map<String, String> headers) {

        this.headers = headers;

    }

    @Override
    public Map<String, String> getHeaders() {

        if (appToken != null) {
            if (headers == null) {
                headers = new HashMap<String, String>(1);
            }
            headers.put(HEADER_APPTOKEN, appToken);
            return headers;
        }

        return headers;
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }

    @Override
    public byte[] getBody() {

        return null;
    }
}
