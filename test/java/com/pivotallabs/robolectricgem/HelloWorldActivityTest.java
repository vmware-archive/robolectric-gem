package com.pivotallabs.robolectricgem;

import com.pivotallabs.robolectricgem.app.HelloWorldActivity;
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

        expect(activity.findViewById(R.id.title)).toBeVisible();
    }
}
