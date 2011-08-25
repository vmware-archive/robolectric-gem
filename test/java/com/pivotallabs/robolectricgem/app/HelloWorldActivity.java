package com.pivotallabs.robolectricgem.app;

import android.os.Bundle;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class HelloWorldActivity extends RoboActivity {
    @InjectView(R.id.title)
    TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);
        title.setText("Hello World");
    }
}
