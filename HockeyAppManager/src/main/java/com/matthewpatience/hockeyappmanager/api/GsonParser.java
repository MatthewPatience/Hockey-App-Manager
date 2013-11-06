package com.matthewpatience.hockeyappmanager.api;

import com.google.gson.Gson;

/**
 * Created by mpatience on 10/22/13.
 */
public class GsonParser {

    private static GsonParser instance;
    private Gson gson;

    private GsonParser() {

        gson = new Gson();

    }

    public static GsonParser getInstance() {

        if (instance == null) {
            instance = new GsonParser();
        }

        return instance;
    }

    public Gson getGson() {

        return gson;
    }

}
