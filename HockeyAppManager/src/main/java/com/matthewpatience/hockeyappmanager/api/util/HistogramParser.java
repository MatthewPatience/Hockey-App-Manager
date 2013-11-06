package com.matthewpatience.hockeyappmanager.api.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.matthewpatience.hockeyappmanager.api.GsonParser;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Histogram;

import java.util.ArrayList;

/**
 * Created by Matthew Patience on 11/5/13.
 */
public class HistogramParser implements CustomParser {

    @Override
    public Object parse(String json) {

        Histogram histogram = new Histogram();
        ArrayList<Histogram.Entry> entries = new ArrayList<Histogram.Entry>();

        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();
        JsonArray histogramArray = object.getAsJsonArray("histogram");
        for (int i = 0; i < histogramArray.size(); i++) {
            JsonArray entryArray = histogramArray.getAsJsonArray();
            Histogram.Entry entry = histogram.new Entry();
            entry.date = GsonParser.getInstance().getGson().fromJson(entryArray.get(0), String.class);
            entry.count = GsonParser.getInstance().getGson().fromJson(entryArray.get(1), int.class);
            entries.add(entry);
        }

        histogram.setEntries(entries);

        return histogram;
    }

}
