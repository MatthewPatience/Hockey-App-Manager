package com.matthewpatience.hockeyappmanager.model.hockeyapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Matthew Patience on 11/5/13.
 */
public class Histogram implements Parcelable {

    public class Entry implements Parcelable {

        public String date;
        public int count;


        public Entry() {

        }

        public Entry(Parcel in) {
            super();

            readFromParcel(in);

        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

            dest.writeString(date);
            dest.writeInt(count);

        }

        protected void readFromParcel(Parcel in) {

            date = in.readString();
            count = in.readInt();

        }

    }

    private ArrayList<Entry> entries;

    public Histogram() {

    }

    public Histogram(Parcel in) {
        super();

        readFromParcel(in);

    }


    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeTypedList(entries);

    }

    protected void readFromParcel(Parcel in) {

        entries = new ArrayList<Entry>();
        in.readTypedList(entries, ENTRY_CREATOR);

    }

    public static final Creator CREATOR =
            new Creator() {
                public Histogram createFromParcel(Parcel in) {
                    return new Histogram(in);
                }

                public Histogram[] newArray(int size) {
                    return new Histogram[size];
                }
            };

    public final Creator ENTRY_CREATOR =
            new Creator() {
                public Entry createFromParcel(Parcel in) {
                    return new Entry(in);
                }

                public Entry[] newArray(int size) {
                    return new Entry[size];
                }
            };

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("Histogram:");
        for (Entry entry : entries) {
            builder.append("\nEntry: " +
                entry.date + " | " + entry.count);
        }

        return builder.toString();
    }

}
