package com.pivotallabs.robolectricgem.matchers;

import android.widget.Checkable;
import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Checkable.class)
public class CheckableMatcher<T extends Checkable, M extends CheckableMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toBeChecked() {
        return actual.isChecked();
    }
}
