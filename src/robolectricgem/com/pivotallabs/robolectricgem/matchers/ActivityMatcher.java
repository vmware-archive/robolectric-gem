package com.pivotallabs.robolectricgem.matchers;

import android.app.Activity;
import android.content.Intent;
import com.pivotallabs.greatexpectations.MatcherOf;
import com.xtremelabs.robolectric.shadows.ShadowIntent;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@MatcherOf(Activity.class)
public class ActivityMatcher<T extends Activity, M extends ActivityMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toBeFinishing() {
        return actual.isFinishing();
    }

    public boolean toHaveStarted(Class<? extends Activity> expected) {
        Intent nextStartedActivity = shadowOf(actual).getNextStartedActivity();
        if (nextStartedActivity == null) {
            return false;
        }
        ShadowIntent shadowIntent = shadowOf(nextStartedActivity);
        return shadowIntent.getComponent().getClassName().equals(expected.getName());
    }
}
