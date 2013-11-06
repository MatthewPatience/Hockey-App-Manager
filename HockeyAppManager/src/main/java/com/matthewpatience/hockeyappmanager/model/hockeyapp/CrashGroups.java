package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mpatience on 11/4/13.
 */
public class CrashGroups {

    @SerializedName("crash_reasons")
    private ArrayList<CrashGroup> crashGroups;
    @SerializedName("total_entries")
    private int totalEntries;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_pages")
    private int perPage;
    @SerializedName("per_page")
    private String status;
    @SerializedName("current_page")
    private int currentPage;

    public CrashGroups() {

    }

    public ArrayList<CrashGroup> getCrashGroups() {
        return crashGroups;
    }

    public void setCrashGroups(ArrayList<CrashGroup> crashGroups) {
        this.crashGroups = crashGroups;
    }

    public int getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(int totalEntries) {
        this.totalEntries = totalEntries;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
