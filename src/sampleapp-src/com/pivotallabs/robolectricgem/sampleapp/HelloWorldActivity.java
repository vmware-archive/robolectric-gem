package com.pivotallabs.robolectricgem.sampleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class HelloWorldActivity extends RoboActivity {
    @InjectView(R.id.title)
    private TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);
        title.setText("Hello World");

        showWelcomeDialog();
    }

    private void showWelcomeDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.findViewById(R.id.dismiss_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                new AlertDialog.Builder(HelloWorldActivity.this)
                        .setMessage("Thanks for running the sample app.")
                        .setPositiveButton("OK, enough already!", null)
                        .show();
            }
        });
        dialog.setTitle("Hi!");
        dialog.show();
    }
}
