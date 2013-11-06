package com.matthewpatience.hockeyappmanager.api.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.matthewpatience.hockeyappmanager.api.GsonParser;
import com.matthewpatience.hockeyappmanager.api.util.CustomParser;
import com.matthewpatience.hockeyappmanager.app.Env;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Matthew Patience on 10/24/13.
 *
 * A Base Request that defines the creation of a basic HTTP Request with
 * JSON parsing capabilities based on the Type passed in. For requests that
 * are not JSON and should be parsed as a String, pass in the type String.
 */
public abstract class BaseRequest<T> extends Request<T> {

    private final Response.Listener<T> listener;
    private final Type reference;
    private String url;
    private CustomParser parser;

    public BaseRequest(int method,
                       String url,
                       Type typeReference,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        this.listener = listener;
        reference = typeReference;
        this.url = url;

        Env.log(Env.TAG_HTTP_REQUEST, "Network Request: " + url);

    }

    public void setCustomParser(CustomParser parser) {

        this.parser = parser;

    }

    @Override
    public abstract Map<String, String> getHeaders();

    @Override
    public abstract Map<String, String> getParams();

    @Override
    public abstract byte[] getBody();

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            Env.log(Env.TAG_HTTP_REQUEST, "Received Response: \n--" + url + "--\n" + json);

            if (reference.getClass().equals(String.class)) {
                return (Response<T>) Response.success(json, HttpHeaderParser.parseCacheHeaders(response));
            }

            T parsedResponse = null;
            if (parser == null) {
                parsedResponse = GsonParser.getInstance().getGson().fromJson(json, reference);
            } else {
                parsedResponse = (T) parser.parse(json);
            }

            return Response.success(parsedResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {

        Env.log(Env.TAG_HTTP_REQUEST, "Received Error: \n--" + url + "--\n" + volleyError.getMessage());

        return volleyError;
    }

    @Override
    protected void deliverResponse(T response) {

        if (listener != null) listener.onResponse(response);

    }

}
