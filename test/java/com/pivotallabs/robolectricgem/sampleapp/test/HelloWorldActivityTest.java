package com.pivotallabs.robolectricgem.sampleapp.test;

import android.view.View;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import com.pivotallabs.robolectricgem.sampleapp.app.HelloWorldActivity;
import com.pivotallabs.robolectricgem.support.RobolectricTestRunnerWithInjection;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunnerWithInjection.class)
public class HelloWorldActivityTest {
    @Test
    public void shouldHaveATitle() {
        final HelloWorldActivity activity = new HelloWorldActivity();
        activity.onCreate(null);

        TextView titleView = (TextView) activity.findViewById(R.id.title);

        // Demonstrating different flavors of visibility checks
        expect(titleView).toBeVisible();
        expect(titleView).toHaveVisibility(View.VISIBLE);
        expect(titleView).not.toBeInvisible();
        expect(titleView).not.toBeGone();

        expect(titleView).toHaveText("Hello World");
    }
}
