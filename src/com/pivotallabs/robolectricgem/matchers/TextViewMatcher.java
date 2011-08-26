package com.pivotallabs.robolectricgem.matchers;

import android.widget.TextView;
import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(TextView.class)
public class TextViewMatcher<T extends TextView, M extends TextViewMatcher<T, M>> extends ViewMatcher<T, M> {
    public boolean toHaveText(CharSequence expectedText) {
        CharSequence actualText = actual.getText();
        descriptionOfActual = actual.getClass().getSimpleName() + "[text=" + actualText + "]";
        return actualText.equals(expectedText);
    }

    public boolean toHaveHint(CharSequence expectedHint) {
        CharSequence actualHint = actual.getHint();
        descriptionOfActual = actual.getClass().getSimpleName() + "[hint=" + actualHint + "]";
        if (actualHint == null) {
            return expectedHint == null;
        }
        return actualHint.equals(expectedHint);
    }
}
