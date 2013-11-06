package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mpatience on 10/26/13.
 */
public class Versions {

    @SerializedName("app_versions")
    private ArrayList<Version> versions;

    @SerializedName("status")
    private String status;


    public Versions() {

    }


    public ArrayList<Version> getVersions() {
        return versions;
    }

    public void setVersions(ArrayList<Version> versions) {
        this.versions = versions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
