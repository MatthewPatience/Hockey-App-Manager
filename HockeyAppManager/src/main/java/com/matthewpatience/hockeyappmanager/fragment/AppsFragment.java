package com.matthewpatience.hockeyappmanager.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.matthewpatience.hockeyappmanager.AppDetailsActivity;
import com.matthewpatience.hockeyappmanager.MainActivity;
import com.matthewpatience.hockeyappmanager.R;
import com.matthewpatience.hockeyappmanager.adapter.AppsAdapter;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.AppRequests;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.auth.ApiRequestQueue;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.HockeyRequest;
import com.matthewpatience.hockeyappmanager.app.Env;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Apps;

import java.util.ArrayList;

/**
 * Created by Matthew Patience on 10/26/13.
 */
public class AppsFragment extends Fragment implements AdapterView.OnItemClickListener, Response.ErrorListener, Response.Listener<Apps> {

    private static final String TAG_APPS_REQUEST = "AppsFragment-AppsRequest";
    private static final String KEY_APPS = "APPS";
    private static final int ID_NEW_APP = 87658;

    private View rootView;
    private ListView lvApps;

    private AppsAdapter appsAdapter;
    private HockeyRequest<Apps> appsRequest;

    public AppsFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(activity.getString(R.string.title_apps));
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {

        rootView = inflater.inflate(R.layout.fragment_apps, container, false);

        lvApps = (ListView) rootView.findViewById(R.id.lvApps);
        lvApps.setOnItemClickListener(this);

        initUi(state);

        return rootView;
    }

    private void initUi(Bundle state) {

        appsAdapter = new AppsAdapter(getActivity());
        lvApps.setAdapter(appsAdapter);
        if (state != null && state.containsKey(KEY_APPS)) {
            ArrayList<App> apps = state.getParcelableArrayList(KEY_APPS);
            appsAdapter.setItems(apps);
        } else {
            fetchApps();
        }

    }

    private void fetchApps() {

        appsRequest = AppRequests.createGetAppsRequest(getActivity(), this, this);
        appsRequest.setTag(TAG_APPS_REQUEST);
        ApiRequestQueue.getInstance(getActivity()).getQueue().add(appsRequest);

    }

    @Override
    public void onDestroy() {

        ApiRequestQueue.getInstance(getActivity()).getQueue().cancelAll(TAG_APPS_REQUEST);

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (appsAdapter != null || appsAdapter.getItems() != null) {
            outState.putParcelableArrayList(KEY_APPS, (ArrayList<App>) appsAdapter.getItems());
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (getActivity() != null && !getActivity().isFinishing()) {

            App app = ((AppsAdapter) parent.getAdapter()).getItem(position);
            Intent appDetailsIntent = new Intent(getActivity(), AppDetailsActivity.class);
            appDetailsIntent.putExtra(AppDetailsActivity.EXTRA_APP, app);
            startActivity(appDetailsIntent);

        }

    }

    @Override
    public void onResponse(Apps apps) {

        if (getActivity() != null || !getActivity().isFinishing()) {

            if (Env.DEBUG) logAppsResponse(apps);

            appsAdapter.setItems(apps.getApps());
            if (lvApps != null) lvApps.setAdapter(appsAdapter);

        }

    }

    private void logAppsResponse(Apps apps) {

        for (App app : apps.getApps()) {
            Env.log(getClass().getSimpleName(), app.toString());
        }

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

        if (getActivity() != null || !getActivity().isFinishing()) {

            Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem newApp = menu.add(Menu.NONE, ID_NEW_APP, Menu.NONE, R.string.apps_menu_new_app);
        newApp.setIcon(android.R.drawable.ic_menu_add);
        newApp.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case ID_NEW_APP:
                displayNewAppDialog(getActivity());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayNewAppDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.apps_menu_new_app);
        builder.setItems(R.array.new_app_options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:

                        break;
                    case 1:

                        break;
                }
            }
        });
        builder.setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

}
