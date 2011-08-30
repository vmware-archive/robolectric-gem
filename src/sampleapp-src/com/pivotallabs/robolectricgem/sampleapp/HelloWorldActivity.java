package com.pivotallabs.robolectricgem.sampleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloWorldActivity extends RoboActivity {
    public static final String LOGO_URL = "http://pivotal.github.com/robolectric/images/robolectric.png";

    @InjectView(R.id.title)
    private TextView title;

    @InjectView(R.id.logo_image_view)
    private ImageView logoImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);
        title.setText("Hello World");

        loadLogoFromWeb();
        showWelcomeDialog();

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClient httpClient = new DefaultHttpClient();
                // TODO do something real here
                HttpGet httpGet = new HttpGet("http://localhost/foo.txt");
                try {
                    httpClient.execute(httpGet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadLogoFromWeb() {
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(LOGO_URL).openStream()); // TODO use apache here
            // TODO for example:
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                InputStream instream = entity.getContent();
//            }
            BitmapDrawable drawable = (BitmapDrawable) Drawable.createFromStream(in, LOGO_URL);
            logoImageView.setImageDrawable(drawable);
        } catch (MalformedURLException e) {
            // ignore
        } catch (IOException e) {
            // ignore
        }
    }

    private void showWelcomeDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.findViewById(R.id.dismiss_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showAlertDialog();
            }
        });
        dialog.setTitle("Hi!");
        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Thanks for running the sample app.")
                .setPositiveButton("OK", null)
                .setNeutralButton("Really?", null)
                .setNegativeButton("No, thanks", null)
                .show();
    }
}
