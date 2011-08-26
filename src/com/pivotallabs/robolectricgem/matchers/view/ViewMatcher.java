package com.pivotallabs.robolectricgem.matchers.view;

import android.view.View;
import com.pivotallabs.greatexpectations.MatcherOf;
import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;

@MatcherOf(View.class)
public class ViewMatcher<T extends View, M extends ViewMatcher<T, M>> extends ObjectMatcher<T, M> {
    public boolean toBeVisible() {
        return actual.getVisibility() == View.VISIBLE;
    }

    public boolean toBeInvisible() {
        return actual.getVisibility() == View.INVISIBLE;
    }

    public boolean toBeGone() {
        return actual.getVisibility() == View.GONE;
    }

    public boolean toHaveVisibility(int visibility) {
        switch (visibility) {
            case View.VISIBLE:
            case View.INVISIBLE:
            case View.GONE:
                return actual.getVisibility() == visibility;
            default:
                throw new IllegalArgumentException();
        }
    }
}
