package com.matthewpatience.hockeyappmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Matthew Patience on 10/26/13.
 */
public abstract class CustomBaseAdapter<T> extends BaseAdapter {

    private List<T> data;
    private LayoutInflater inflater;

    public CustomBaseAdapter(Context context) {

        inflater = LayoutInflater.from(context);

    }

    public CustomBaseAdapter(Context context, List<T> data) {
        this(context);

        this.data = data;

    }

    public void setItems(List<T> data) {

        this.data = data;

    }

    public List<T> getItems() {

        return data;
    }

    protected View inflate(int layout, ViewGroup container) {

        return inflater.inflate(layout, container, false);
    }

    @Override
    public int getCount() {

        if (data == null) {
            return 0;
        }

        return data.size();
    }

    @Override
    public T getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflate(getRowLayout(), parent);
        }

        setUpView(convertView, getItem(position));

        return convertView;
    }

    protected abstract int getRowLayout();
    protected abstract void setUpView(View view, T item);

}
