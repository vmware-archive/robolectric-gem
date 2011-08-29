package com.pivotallabs.robolectricgem.matchers;

import android.widget.CompoundButton;
import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(CompoundButton.class)
public class CompoundButtonMatcher<T extends CompoundButton, M extends CompoundButtonMatcher<T, M>> extends TextViewMatcher<T, M> {
    public boolean toBeChecked() {
        return actual.isChecked();
    }
}
