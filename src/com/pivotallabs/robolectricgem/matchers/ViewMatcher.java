package com.pivotallabs.robolectricgem.matchers;

import android.view.View;
import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(View.class)
public class ViewMatcher<T extends View, M extends ViewMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toBeVisible() {
        return actual.getVisibility() == View.VISIBLE;
    }

    public boolean toBeInvisible() {
        return actual.getVisibility() == View.INVISIBLE;
    }

    public boolean toBeGone() {
        return actual.getVisibility() == View.GONE;
    }

    public boolean toHaveVisibility(int expectedVisibility) {
        switch (expectedVisibility) {
            case View.VISIBLE:
            case View.INVISIBLE:
            case View.GONE:
                descriptionOfActual = actual.getClass().getSimpleName() + "[visibility=" + visibilityToString(actual.getVisibility()) + "]";
                descriptionOfExpected = visibilityToString(expectedVisibility);
                return actual.getVisibility() == expectedVisibility;
            default:
                throw new IllegalArgumentException("expected argument View.VISIBLE, View.INVISIBLE, or View.GONE");
        }
    }

    public boolean toBeEnabled() {
        return actual.isEnabled();
    }

    private String visibilityToString(int visibility) {
        switch (visibility) {
            case View.VISIBLE:
                return "View.VISIBLE";
            case View.INVISIBLE:
                return "View.INVISIBLE";
            case View.GONE:
                return "View.GONE";
            default:
                return null;
        }
    }
}
