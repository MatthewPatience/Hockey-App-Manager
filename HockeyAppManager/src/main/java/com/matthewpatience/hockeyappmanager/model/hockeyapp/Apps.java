package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mpatience on 10/26/13.
 */
public class Apps {

    @SerializedName("apps")
    private ArrayList<App> apps;
    @SerializedName("status")
    private String status;


    public Apps() {

    }


    public ArrayList<App> getApps() {
        return apps;
    }

    public void setApps(ArrayList<App> apps) {
        this.apps = apps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
