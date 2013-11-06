package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Matthew Patience on 10/27/13.
 */
public class Version implements Parcelable {

    @SerializedName("version")
    private String version;
    @SerializedName("mandatory")
    private boolean mandatory = false;
    @SerializedName("config_url")
    private String configUrl;
    @SerializedName("download_url")
    private String downloadUrl;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("appsize")
    private long appSize;
    @SerializedName("device_family")
    private String deviceFamily;
    @SerializedName("notes")
    private String notes;
    @SerializedName("status")
    private int status = -1;
    @SerializedName("shortversion")
    private String shortVersion;
    @SerializedName("minimum_os_version")
    private String minimumOsVersion;
    @SerializedName("title")
    private String title;
    @SerializedName("created_at")
    private String createdAt;
    private App app;
    @SerializedName("statistics")
    private Statistics statistics;

    public Version() {
        super();

    }

    public Version(Parcel in) {
        super();

        readFromParcel(in);

    }


    public String getHumanReadableTimestamp() {

        Date date = new Date(timestamp*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);

        return formattedDate;
    }



    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getConfigUrl() {
        return configUrl;
    }

    public void setConfigUrl(String configUrl) {
        this.configUrl = configUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getAppSize() {
        return appSize;
    }

    public void setAppSize(long appSize) {
        this.appSize = appSize;
    }

    public String getDeviceFamily() {
        return deviceFamily;
    }

    public void setDeviceFamily(String deviceFamily) {
        this.deviceFamily = deviceFamily;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShortVersion() {
        return shortVersion;
    }

    public void setShortVersion(String shortVersion) {
        this.shortVersion = shortVersion;
    }

    public String getMinimumOsVersion() {
        return minimumOsVersion;
    }

    public void setMinimumOsVersion(String minimumOsVersion) {
        this.minimumOsVersion = minimumOsVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(version);
        dest.writeInt(mandatory ? 1 : 0);
        dest.writeString(configUrl);
        dest.writeString(downloadUrl);
        dest.writeLong(timestamp);
        dest.writeLong(appSize);
        dest.writeString(deviceFamily);
        dest.writeString(notes);
        dest.writeInt(status);
        dest.writeString(shortVersion);
        dest.writeString(minimumOsVersion);
        dest.writeString(title);
        dest.writeParcelable(app, 0);
        dest.writeString(createdAt);
        dest.writeParcelable(statistics, 0);

    }

    protected void readFromParcel(Parcel in) {

        version = in.readString();
        mandatory = (in.readInt() != 0);
        configUrl = in.readString();
        downloadUrl = in.readString();
        timestamp = in.readLong();
        appSize = in.readLong();
        deviceFamily = in.readString();
        notes = in.readString();
        status = in.readInt();
        shortVersion = in.readString();
        minimumOsVersion = in.readString();
        title = in.readString();
        app = in.readParcelable(App.class.getClassLoader());
        createdAt = in.readString();
        statistics = in.readParcelable(Statistics.class.getClassLoader());

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Version createFromParcel(Parcel in) {
                    return new Version(in);
                }

                public Version[] newArray(int size) {
                    return new Version[size];
                }
            };

    @Override
    public String toString() {

        return "Version:" +
                "\nVersion: " + version +
                "\nMandatory: " + mandatory +
                "\nConfig URL: " + configUrl +
                "\nDownload URL: " + downloadUrl +
                "\nTimestamp: " + timestamp +
                "\nApp Size: " + appSize +
                "\nDevice Family: " + deviceFamily +
                "\nNotes: " + notes +
                "\nStatus: " + status +
                "\nShort Version: " + shortVersion +
                "\nMinimum OS Version: " + minimumOsVersion +
                "\nTitle: " + title;
    }

}
