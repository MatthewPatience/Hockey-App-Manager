package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Matthew Patience on 10/27/13.
 */
public class Statistics implements Parcelable {

    @SerializedName("crashes")
    private int crashes;
    @SerializedName("devices")
    private int devices;
    @SerializedName("downloads")
    private int downloads;
    @SerializedName("installs")
    private int installs;
    @SerializedName("last_request_at")
    private String lastRequestAt;
    @SerializedName("usage_time")
    private String usageTime;

    public Statistics() {
        super();

    }

    public Statistics(Parcel in) {
        super();

        readFromParcel(in);

    }



    public int getCrashes() {
        return crashes;
    }

    public void setCrashes(int crashes) {
        this.crashes = crashes;
    }

    public int getDevices() {
        return devices;
    }

    public void setDevices(int devices) {
        this.devices = devices;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getInstalls() {
        return installs;
    }

    public void setInstalls(int installs) {
        this.installs = installs;
    }

    public String getLastRequestAt() {
        return lastRequestAt;
    }

    public void setLastRequestAt(String lastRequestAt) {
        this.lastRequestAt = lastRequestAt;
    }

    public String getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(String usageTime) {
        this.usageTime = usageTime;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(crashes);
        dest.writeInt(devices);
        dest.writeInt(downloads);
        dest.writeInt(installs);
        dest.writeString(lastRequestAt);
        dest.writeString(usageTime);

    }

    protected void readFromParcel(Parcel in) {

        crashes = in.readInt();
        devices = in.readInt();
        downloads = in.readInt();
        installs = in.readInt();
        lastRequestAt = in.readString();
        usageTime = in.readString();

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Statistics createFromParcel(Parcel in) {
                    return new Statistics(in);
                }

                public Statistics[] newArray(int size) {
                    return new Statistics[size];
                }
            };

    @Override
    public String toString() {

        return "Statistics:" +
                "\nCrashes: " + crashes +
                "\nDevices: " + devices +
                "\nDownloads: " + downloads +
                "\nInstalls: " + installs +
                "\nLast Request At: " + lastRequestAt +
                "\nUsage Time: " + usageTime;
    }

}
