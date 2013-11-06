package com.matthewpatience.hockeyappmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.matthewpatience.hockeyappmanager.fragment.LoginFragment;

/**
 * Created by mpatience on 10/23/13.
 */
public class LoginActivity extends FragmentActivity {

    private LoginFragment fragment;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_login);

        fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentLogin);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
