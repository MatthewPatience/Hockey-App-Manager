package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mpatience on 10/26/13.
 */
public class App implements Parcelable {

    private static String APP_ICON_URL = "https://rink.hockeyapp.net/api/2/apps/$ID$?format=png";

    @SerializedName("title")
    private String title;
    @SerializedName("bundle_identifier")
    private String bundleIdentifier;
    @SerializedName("public_identifier")
    private String publicIdentifier;
    @SerializedName("device_family")
    private String deviceFamily;
    @SerializedName("minimum_os_version")
    private String minimumOsVersion;
    @SerializedName("release_type")
    private int releaseType;
    @SerializedName("status")
    private int status = -1;
    @SerializedName("platform")
    private String platform;
    @SerializedName("config_url")
    private String configUrl;
    @SerializedName("public_url")
    private String publicUrl;

    public App() {
        super();

    }

    public App(Parcel in) {
        super();

        readFromParcel(in);

    }


    public String getAppIconUrl() {

        return APP_ICON_URL.replace("$ID$", publicIdentifier);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBundleIdentifier() {
        return bundleIdentifier;
    }

    public void setBundleIdentifier(String bundleIdentifier) {
        this.bundleIdentifier = bundleIdentifier;
    }

    public String getPublicIdentifier() {
        return publicIdentifier;
    }

    public void setPublicIdentifier(String publicIdentifier) {
        this.publicIdentifier = publicIdentifier;
    }

    public String getDeviceFamily() {
        return deviceFamily;
    }

    public void setDeviceFamily(String deviceFamily) {
        this.deviceFamily = deviceFamily;
    }

    public String getMinimumOsVersion() {
        return minimumOsVersion;
    }

    public void setMinimumOsVersion(String minimumOsVersion) {
        this.minimumOsVersion = minimumOsVersion;
    }

    public int getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(int releaseType) {
        this.releaseType = releaseType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getConfigUrl() {
        return configUrl;
    }

    public void setConfigUrl(String configUrl) {
        this.configUrl = configUrl;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(bundleIdentifier);
        dest.writeString(publicIdentifier);
        dest.writeString(deviceFamily);
        dest.writeString(minimumOsVersion);
        dest.writeInt(releaseType);
        dest.writeInt(status);
        dest.writeString(platform);

    }

    protected void readFromParcel(Parcel in) {

        title = in.readString();
        bundleIdentifier = in.readString();
        publicIdentifier = in.readString();
        deviceFamily = in.readString();
        minimumOsVersion = in.readString();
        releaseType = in.readInt();
        status = in.readInt();
        platform = in.readString();

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public App createFromParcel(Parcel in) {
                    return new App(in);
                }

                public App[] newArray(int size) {
                    return new App[size];
                }
            };

    @Override
    public String toString() {

        return "App:" +
                "\nTitle: " + title +
                "\nBundle Identifier: " + bundleIdentifier +
                "\nPublic Identifier: " + publicIdentifier +
                "\nDevice Family: " + deviceFamily +
                "\nMinimum OS Version: " + minimumOsVersion +
                "\nRelease Type: " + releaseType +
                "\nStatus: " + status +
                "\nPlatform: " + platform;
    }

}
