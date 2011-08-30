package com.pivotallabs.robolectricgem.matchers;

import android.app.Activity;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class ActivityMatcherTest {

    private Activity activity;
    private ActivityMatcher<Activity, ?> matcher;

    @Before
    public void setup() throws Exception {
        activity = new Activity();
        matcher = newActivityMatcher(activity);
    }

    @Test
    public void test_toBeFinishing() throws Exception {
        expect(matcher.toBeFinishing()).toBeFalse();
        activity.finish();
        expect(matcher.toBeFinishing()).toBeTrue();
    }

    private <T extends Activity> ActivityMatcher<T, ?> newActivityMatcher(T value) {
        ActivityMatcher matcher = new ActivityMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
