package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Matthew Patience on 11/4/13.
 */
public class Crash implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("app_id")
    private long appId;
    @SerializedName("app_version_id")
    private long appVersionId;
    @SerializedName("crash_reason_id")
    private long crashReasonId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("oem")
    private String oem;
    @SerializedName("model")
    private String model;
    @SerializedName("bundle_version")
    private String bundleVersion;
    @SerializedName("bundle_short_version")
    private String bundleShortVersion;
    @SerializedName("contact_string")
    private String contactString;
    @SerializedName("user_string")
    private String userString;
    @SerializedName("os_version")
    private String osVersion;
    @SerializedName("jail_break")
    private boolean jailBreak;
    private CrashGroup crashGroup;

    public Crash() {

    }

    public Crash(Parcel in) {
        super();

        readFromParcel(in);

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(long appVersionId) {
        this.appVersionId = appVersionId;
    }

    public long getCrashReasonId() {
        return crashReasonId;
    }

    public void setCrashReasonId(long crashReasonId) {
        this.crashReasonId = crashReasonId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBundleVersion() {
        return bundleVersion;
    }

    public void setBundleVersion(String bundleVersion) {
        this.bundleVersion = bundleVersion;
    }

    public String getBundleShortVersion() {
        return bundleShortVersion;
    }

    public void setBundleShortVersion(String bundleShortVersion) {
        this.bundleShortVersion = bundleShortVersion;
    }

    public String getContactString() {
        return contactString;
    }

    public void setContactString(String contactString) {
        this.contactString = contactString;
    }

    public String getUserString() {
        return userString;
    }

    public void setUserString(String userString) {
        this.userString = userString;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        osVersion = osVersion;
    }

    public boolean isJailBreak() {
        return jailBreak;
    }

    public void setJailBreak(boolean jailBreak) {
        this.jailBreak = jailBreak;
    }

    public CrashGroup getCrashGroup() {
        return crashGroup;
    }

    public void setCrashGroup(CrashGroup crashGroup) {
        this.crashGroup = crashGroup;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeLong(appId);
        dest.writeLong(appVersionId);
        dest.writeLong(crashReasonId);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(oem);
        dest.writeString(model);
        dest.writeString(bundleVersion);
        dest.writeString(bundleShortVersion);
        dest.writeString(contactString);
        dest.writeString(userString);
        dest.writeString(osVersion);
        dest.writeInt(jailBreak ? 1 : 0);
        dest.writeParcelable(crashGroup, 0);

    }

    protected void readFromParcel(Parcel in) {

        id = in.readLong();
        appId = in.readLong();
        appVersionId = in.readLong();
        crashReasonId = in.readLong();
        createdAt = in.readString();
        updatedAt = in.readString();
        oem = in.readString();
        model = in.readString();
        bundleVersion = in.readString();
        bundleShortVersion = in.readString();
        contactString = in.readString();
        userString = in.readString();
        osVersion = in.readString();
        jailBreak = (in.readInt() != 0);
        crashGroup = in.readParcelable(CrashGroup.class.getClassLoader());

    }

    public static final Creator CREATOR =
            new Creator() {
                public Crash createFromParcel(Parcel in) {
                    return new Crash(in);
                }

                public Crash[] newArray(int size) {
                    return new Crash[size];
                }
            };

    @Override
    public String toString() {

        return "Crash:" +
                "\nID: " + id +
                "\nApp ID: " + appId +
                "\nApp Version ID: " + appVersionId +
                "\nCrash Reason ID: " + crashReasonId +
                "\nCreated At: " + createdAt +
                "\nUpdated At: " + updatedAt +
                "\nOEM: " + oem +
                "\nModel: " + model +
                "\nBundle Version: " + bundleVersion +
                "\nBundle Short Version: " + bundleShortVersion +
                "\nContact String: " + contactString +
                "\nUser String: " + userString +
                "\nOS Version: " + osVersion +
                "\nJail Break: " + jailBreak;
    }

}
