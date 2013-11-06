package com.matthewpatience.hockeyappmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.matthewpatience.hockeyappmanager.R;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Version;

/**
 * Created by mpatience on 10/26/13.
 */
public class VersionsAdapter extends CustomBaseAdapter<Version> {

    public VersionsAdapter(Context context) {
        super(context);

    }

    @Override
    protected int getRowLayout() {

        return R.layout.item_row_version;
    }

    @Override
    protected void setUpView(View view, Version item) {

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvTime = (TextView) view.findViewById(R.id.tvTime);

        tvTitle.setText(item.getShortVersion() + " (" + item.getVersion() + ")");
        tvTime.setText(item.getHumanReadableTimestamp());

    }

}
