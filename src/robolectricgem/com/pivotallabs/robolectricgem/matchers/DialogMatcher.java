package com.pivotallabs.robolectricgem.matchers;

import android.app.Dialog;
import com.pivotallabs.greatexpectations.MatcherOf;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@MatcherOf(Dialog.class)
public class DialogMatcher<T extends Dialog, M extends DialogMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toHaveTitle(CharSequence expectedTitle) {
        CharSequence actualTitle = shadowOf(actual).getTitle();
        setDescriptionOfActual("title", actualTitle);
        return equalsAllowingNull(actualTitle, expectedTitle);
    }
}
