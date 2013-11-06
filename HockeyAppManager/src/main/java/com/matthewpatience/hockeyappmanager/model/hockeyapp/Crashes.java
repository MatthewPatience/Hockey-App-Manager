package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mpatience on 11/4/13.
 */
public class Crashes {

    @SerializedName("crashes")
    private ArrayList<Crash> crashes;
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

    public Crashes() {

    }

    public ArrayList<Crash> getCrashes() {
        return crashes;
    }

    public void setCrashes(ArrayList<Crash> crashes) {
        this.crashes = crashes;
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
