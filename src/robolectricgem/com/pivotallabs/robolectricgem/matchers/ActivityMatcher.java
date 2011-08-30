package com.pivotallabs.robolectricgem.matchers;

import android.app.Activity;
import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Activity.class)
public class ActivityMatcher<T extends Activity, M extends ActivityMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toBeFinishing() {
        return actual.isFinishing();
    }
}
