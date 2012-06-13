package com.pivotallabs.robolectricgem.sampleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class HelloWorldActivity extends RoboActivity {
    
    public static final String ROBOLECTRIC_LOGO_URL = "http://pivotal.github.com/robolectric/images/robolectric.png";
    public static final String PIVOTAL_LOGO_URL = "http://pivotallabs.com/images/pivotallabs-logo.png";

    @InjectView(R.id.title)
    private TextView title;

    @InjectView(R.id.logo_image_view)
    private ImageView logoImageView;

    @InjectView(R.id.quit_button)
    private Button quitButton;

    private HttpClient httpClient = new DefaultHttpClient();

    private Animation animation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);

        title.setText("Hello World");

        logoImageView.setImageDrawable(loadImageFromWeb(ROBOLECTRIC_LOGO_URL));

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoImageView.setImageDrawable(loadImageFromWeb(PIVOTAL_LOGO_URL));
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        animation = new RotateAnimation(this, null);
        animation.start();

        showWelcomeDialog();
    }

    public Animation getAnimation() {
        return animation;
    }

    private Drawable loadImageFromWeb(String imageUrl) {
        // Note: In a real application, this IO should be done in another thread.
        try {
            HttpResponse response = httpClient.execute(new HttpGet(imageUrl));
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream in = entity.getContent();
                return Drawable.createFromStream(in, imageUrl);
            }
        } catch (MalformedURLException e) {
            // ignore
        } catch (IOException e) {
            // ignore
        }
        return null;
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
