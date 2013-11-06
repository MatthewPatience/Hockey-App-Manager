package com.matthewpatience.hockeyappmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.matthewpatience.hockeyappmanager.fragment.AppsFragment;
import com.matthewpatience.hockeyappmanager.fragment.NavigationDrawerFragment;
import com.matthewpatience.hockeyappmanager.util.OnSectionAttachedListener;
import com.matthewpatience.hockeyappmanager.app.UserState;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, OnSectionAttachedListener {

    private NavigationDrawerFragment navDrawerFragment;
    private Fragment currentFragment;
    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if user is logged in and prompt them if not
        if (!UserState.getInstance(this).isSignedIn()) {
            promptForLogin();
            return;
        }

        //Set up navigation

        navDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getTitle();
        navDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    private void promptForLogin() {

        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment = null;
        try {
            //Check if this was a config change or the user selected the same navigation item
            fragment = getSupportFragmentManager().getFragments().get(1);
        } catch (IndexOutOfBoundsException e) {}

        switch (position) {
            case 0:
                if (fragment == null || !(fragment instanceof AppsFragment)) {
                    fragment = new AppsFragment();
                } else {
                    return;
                }
                break;
            case 1:
                if (fragment == null || !(fragment instanceof AppsFragment)) {
                    fragment = new AppsFragment();
                } else {
                    return;
                }
                break;
            case 2:
                if (fragment == null || !(fragment instanceof AppsFragment)) {
                    fragment = new AppsFragment();
                } else {
                    return;
                }
                break;
        }

        if (fragment != null) {
            replaceCurrentFragment(fragment);
        }

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
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (!navDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
        }

        return super.onOptionsItemSelected(item);
    }

}
