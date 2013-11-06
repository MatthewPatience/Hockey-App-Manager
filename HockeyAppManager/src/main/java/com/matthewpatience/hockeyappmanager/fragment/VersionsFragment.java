package com.matthewpatience.hockeyappmanager.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.matthewpatience.hockeyappmanager.R;
import com.matthewpatience.hockeyappmanager.adapter.VersionsAdapter;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.VersionRequests;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.auth.ApiRequestQueue;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.HockeyRequest;
import com.matthewpatience.hockeyappmanager.app.Env;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Version;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.Versions;
import com.matthewpatience.hockeyappmanager.util.OnSectionAttachedListener;

import java.util.ArrayList;

/**
 * Created by Matthew Patience on 10/26/13.
 */
public class VersionsFragment extends Fragment implements AdapterView.OnItemClickListener, Response.ErrorListener, Response.Listener<Versions> {

    private static final String TAG_VERSIONS_REQUEST = "VersionsFragment-VersionsRequest";
    private static final String KEY_VERSIONS = "VERSIONS";
    public static final String EXTRA_APP = "APP";

    private View rootView;
    private ListView lvVersions;

    private App app;
    private VersionsAdapter versionsAdapter;
    private HockeyRequest<Versions> versionsRequest;

    public VersionsFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((OnSectionAttachedListener) activity).onSectionAttached(activity.getString(R.string.title_versions));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {

        rootView = inflater.inflate(R.layout.fragment_versions, container, false);

        lvVersions = (ListView) rootView.findViewById(R.id.lvVersions);
        lvVersions.setOnItemClickListener(this);

        initUi(state);

        return rootView;
    }

    private void initUi(Bundle state) {

        app = getArguments().getParcelable(EXTRA_APP);
        if (app == null) {
            app = state.getParcelable(EXTRA_APP);
            if (app == null) return;
        }

        versionsAdapter = new VersionsAdapter(getActivity());
        lvVersions.setAdapter(versionsAdapter);
        if (state != null && state.containsKey(KEY_VERSIONS)) {
            ArrayList<Version> versions = state.getParcelableArrayList(KEY_VERSIONS);
            versionsAdapter.setItems(versions);
        } else {
            fetchVersions();
        }

    }

    private void fetchVersions() {

        versionsRequest = VersionRequests.createGetVersionsRequest(getActivity(), app, this, this);
        versionsRequest.setTag(TAG_VERSIONS_REQUEST);
        ApiRequestQueue.getInstance(getActivity()).getQueue().add(versionsRequest);

    }

    @Override
    public void onDestroy() {

        ApiRequestQueue.getInstance(getActivity()).getQueue().cancelAll(TAG_VERSIONS_REQUEST);

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(EXTRA_APP, app);
        if (versionsAdapter != null || versionsAdapter.getItems() != null) {
            outState.putParcelableArrayList(KEY_VERSIONS, (ArrayList<Version>) versionsAdapter.getItems());
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onResponse(Versions versions) {

        if (getActivity() != null || !getActivity().isFinishing()) {

            if (Env.DEBUG) logAppsResponse(versions);

            versionsAdapter.setItems(versions.getVersions());
            if (lvVersions != null) lvVersions.setAdapter(versionsAdapter);

        }

    }

    private void logAppsResponse(Versions versions) {

        for (Version version : versions.getVersions()) {
            Env.log(getClass().getSimpleName(), version.toString());
        }

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

        if (getActivity() != null || !getActivity().isFinishing()) {

            Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

}
