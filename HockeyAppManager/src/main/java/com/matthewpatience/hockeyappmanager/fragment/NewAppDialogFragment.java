package com.matthewpatience.hockeyappmanager.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.matthewpatience.hockeyappmanager.R;

/**
 * Created by mpatience on 10/27/13.
 */
public class NewAppDialogFragment extends DialogFragment {

    public NewAppDialogFragment() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



    }

    protected class NewAppDialog extends Dialog {

        public NewAppDialog(Context context) {
            super(context);
        }

        public NewAppDialog(Context context, int theme) {
            super(context, theme);
        }

        protected NewAppDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setTitle(R.string.apps_menu_new_app);
            setContentView(R.layout.dialog_new_app);

        }

    }

}
