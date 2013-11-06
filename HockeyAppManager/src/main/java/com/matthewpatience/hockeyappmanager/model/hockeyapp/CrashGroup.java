package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Matthew Patience on 11/4/13.
 */
public class CrashGroup implements Parcelable {

    public static final int STATUS_OPEN = 0;
    public static final int STATUS_RESOLVED = 1;
    public static final int STATUS_IGNORED = 2;

    @SerializedName("id")
    private long id;
    @SerializedName("app_id")
    private long appId;
    @SerializedName("app_version_id")
    private long appVersionId;
    @SerializedName("number_of_crashes")
    private int numberOfCrashes;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("last_crash_at")
    private String lastCrashAt;
    @SerializedName("bundle_short_version")
    private String bundleShortVersion;
    @SerializedName("bundle_version")
    private String bundleVersion;
    @SerializedName("status")
    private int status;
    @SerializedName("fixed")
    private boolean fixed;
    @SerializedName("file")
    private String file;
    @SerializedName("class")
    private String clazz;
    @SerializedName("method")
    private String method;
    @SerializedName("line")
    private String line;
    @SerializedName("reason")
    private String reason;
    private App app;

    public CrashGroup() {

    }

    public CrashGroup(Parcel in) {
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

    public int getNumberOfCrashes() {
        return numberOfCrashes;
    }

    public void setNumberOfCrashes(int numberOfCrashes) {
        this.numberOfCrashes = numberOfCrashes;
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

    public String getLastCrashAt() {
        return lastCrashAt;
    }

    public void setLastCrashAt(String lastCrashAt) {
        this.lastCrashAt = lastCrashAt;
    }

    public String getBundleShortVersion() {
        return bundleShortVersion;
    }

    public void setBundleShortVersion(String bundleShortVersion) {
        this.bundleShortVersion = bundleShortVersion;
    }

    public String getBundleVersion() {
        return bundleVersion;
    }

    public void setBundleVersion(String bundleVersion) {
        this.bundleVersion = bundleVersion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
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
        dest.writeInt(numberOfCrashes);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(lastCrashAt);
        dest.writeString(bundleShortVersion);
        dest.writeString(bundleVersion);
        dest.writeInt(status);
        dest.writeInt(fixed ? 1 : 0);
        dest.writeString(file);
        dest.writeString(clazz);
        dest.writeString(method);
        dest.writeString(line);
        dest.writeString(reason);
        dest.writeParcelable(app, 0);

    }

    protected void readFromParcel(Parcel in) {

        id = in.readLong();
        appId = in.readLong();
        appVersionId = in.readLong();
        numberOfCrashes = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
        lastCrashAt = in.readString();
        bundleShortVersion = in.readString();
        bundleVersion = in.readString();
        status = in.readInt();
        fixed = (in.readInt() != 0);
        file = in.readString();
        clazz = in.readString();
        method = in.readString();
        line = in.readString();
        reason = in.readString();
        app = in.readParcelable(App.class.getClassLoader());

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public CrashGroup createFromParcel(Parcel in) {
                    return new CrashGroup(in);
                }

                public CrashGroup[] newArray(int size) {
                    return new CrashGroup[size];
                }
            };

    @Override
    public String toString() {

        return "CrashGroup:" +
                "\nID: " + id +
                "\nApp ID: " + appId +
                "\nApp Version ID: " + appVersionId +
                "\nNumber Of Crashes: " + numberOfCrashes +
                "\nCreated At: " + createdAt +
                "\nUpdated At: " + updatedAt +
                "\nLast Crash At: " + lastCrashAt +
                "\nBundle Short Version: " + bundleShortVersion +
                "\nBundle Version: " + bundleVersion +
                "\nStatus: " + status +
                "\nFixed: " + fixed +
                "\nFile: " + file +
                "\nClass: " + clazz +
                "\nMethod: " + method +
                "\nLine: " + line +
                "\nReason: " + reason;
    }

}
