package com.matthewpatience.hockeyappmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.matthewpatience.hockeyappmanager.R;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.squareup.picasso.Picasso;

/**
 * Created by mpatience on 10/26/13.
 */
public class AppsAdapter extends CustomBaseAdapter<App> {

    public AppsAdapter(Context context) {
        super(context);

    }

    @Override
    protected int getRowLayout() {

        return R.layout.item_row_app;
    }

    @Override
    protected void setUpView(View view, App item) {

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvBundle = (TextView) view.findViewById(R.id.tvBundle);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
        ImageView ivPlatform = (ImageView) view.findViewById(R.id.ivPlatform);

        tvTitle.setText(item.getTitle());
        tvBundle.setText(item.getBundleIdentifier());

        Picasso.with(view.getContext())
                .load(item.getAppIconUrl()).error(android.R.drawable.ic_menu_report_image).into(ivIcon);

        ivPlatform.setImageResource(getPlatformIconResource(item.getPlatform()));

    }

    private int getPlatformIconResource(String platform) {

        if (platform.equalsIgnoreCase("Android")) {
            return R.drawable.ic_android;
        } else if (platform.equalsIgnoreCase("iOS")) {
            return R.drawable.ic_ios;
        } else if (platform.equalsIgnoreCase("Windows Phone")) {
            return R.drawable.ic_windows;
        }

        return 0;
    }

}
