package com.matthewpatience.hockeyappmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.matthewpatience.hockeyappmanager.fragment.VersionsFragment;
import com.matthewpatience.hockeyappmanager.model.hockeyapp.App;
import com.matthewpatience.hockeyappmanager.util.OnSectionAttachedListener;

public class AppDetailsActivity extends ActionBarActivity implements OnSectionAttachedListener {

    public static final String EXTRA_APP = "APP";

    private Fragment currentFragment;
    private CharSequence title;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        app = getIntent().getExtras().getParcelable(EXTRA_APP);

        if (savedInstanceState != null) {
            try {
                currentFragment = getSupportFragmentManager().getFragments().get(0);
            } catch (IndexOutOfBoundsException e) {}
        } else {
            displayVersionsFragment(app);
        }

        title = getTitle();

    }

    private void displayVersionsFragment(App app) {

        Fragment versionsFragment = new VersionsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(VersionsFragment.EXTRA_APP, app);
        versionsFragment.setArguments(bundle);
        replaceCurrentFragment(versionsFragment);

    }

    private void replaceCurrentFragment(Fragment fragment) {

        currentFragment = fragment;

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, currentFragment)
                .commit();

    }

    public void onSectionAttached(String title) {

        this.title = title;

    }

    public void restoreActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        restoreActionBar();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
