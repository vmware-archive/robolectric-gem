package com.pivotallabs.robolectricgem.matchers.view;

import android.view.View;
import com.pivotallabs.greatexpectations.MatcherOf;
import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;

@MatcherOf(View.class)
public class ViewMatcher<T extends View, M extends ViewMatcher<T, M>> extends ObjectMatcher<T, M> {
    public boolean toBeVisible() {
        return actual.getVisibility() == View.VISIBLE;
    }
}
