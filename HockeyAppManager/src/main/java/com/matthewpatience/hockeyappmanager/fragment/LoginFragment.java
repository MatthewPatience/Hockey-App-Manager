package com.matthewpatience.hockeyappmanager.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.matthewpatience.hockeyappmanager.MainActivity;
import com.matthewpatience.hockeyappmanager.R;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.auth.LoginTask;
import com.matthewpatience.hockeyappmanager.api.hockeyapp.auth.OnlineHelper;
import com.matthewpatience.hockeyappmanager.app.UserState;

/**
 * Created by mpatience on 10/23/13.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, LoginTask.OnLoginListener, DialogInterface.OnCancelListener {

    private View rootView;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    private LoginTask loginTask;
    private ProgressDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        etPassword = (EditText) rootView.findViewById(R.id.etPassword);
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case (R.id.btnLogin):
                try {
                    if (validateLoginForm()) {
                        login();
                    }
                } catch (IllegalStateException e) {}
                break;
        }

    }

    private final boolean validateLoginForm() {

        boolean status = true;
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!isValidEmail(email)) {
            status = false;
            etEmail.setError(getActivity().getString(R.string.login_error_invalid_email));
        }
        if (email.isEmpty()) {
            status = false;
            etEmail.setError(getActivity().getString(R.string.login_error_no_email));
        }
        if (password.isEmpty()) {
            status = false;
            etPassword.setError(getActivity().getString(R.string.login_error_no_password));
        }

        return status;
    }

    private final boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

    }

    private void login() throws IllegalStateException {

        if (getActivity() != null || !getActivity().isFinishing()) {
            loadingDialog = ProgressDialog.show(getActivity(),
                    getActivity().getString(R.string.login_loading_title),
                    getActivity().getString(R.string.login_loading_message),
                    true, true);
            loadingDialog.setOnCancelListener(this);
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            loginTask = new LoginTask(getActivity(), email, password);
            loginTask.setOnLoginListener(this);
            loginTask.execute();
        } else {
            throw new IllegalStateException();
        }

    }

    @Override
    public void onSuccessfulLogin(String token) {

        if (getActivity() == null || getActivity().isFinishing()) return;

        dismissLoadingDialog();
        Toast.makeText(getActivity(), R.string.login_successful_auth, Toast.LENGTH_SHORT).show();

        saveUserInfo(getActivity(), token, etEmail.getText().toString());
        startMainActivity();

    }

    private void saveUserInfo(Context context, String token, String email) {

        UserState userState = UserState.getInstance(context);
        userState.saveToken(context, token);
        userState.saveEmail(context, email);

    }

    private void startMainActivity() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    @Override
    public void onFailedLogin(int status) {

        dismissLoadingDialog();

        switch (status) {
            case OnlineHelper.STATUS_LOGIN_ERROR:
                Toast.makeText(getActivity(), R.string.login_invalid_credentials, Toast.LENGTH_SHORT).show();
                break;
            case OnlineHelper.STATUS_NETWORK_ERROR:
                Toast.makeText(getActivity(), R.string.login_network_error, Toast.LENGTH_SHORT).show();
                break;
            case OnlineHelper.STATUS_UNKNOWN_ERROR:
                Toast.makeText(getActivity(), R.string.login_unknown_error, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void dismissLoadingDialog() {

        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

    }

    @Override
    public void onCancel(DialogInterface dialog) {

        if (loginTask != null && !loginTask.isCancelled()) {
            loginTask.cancel(true);
        }

    }

}
