package com.pivotallabs.robolectricgem.matchers;

import android.app.AlertDialog;
import com.pivotallabs.greatexpectations.MatcherOf;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@MatcherOf(AlertDialog.class)
public class AlertDialogMatcher<T extends AlertDialog, M extends AlertDialogMatcher<T, M>> extends DialogMatcher<T, M> {
    public boolean toHaveMessage(CharSequence expectedMessage) {
        String actualMessage = shadowOf(actual).getMessage();
        setDescriptionOfActual("message", actualMessage);
        return equalsAllowingNull(actualMessage, expectedMessage);
    }

    public boolean toHavePositiveButtonText(CharSequence expectedButtonText) {
        CharSequence actualButtonText = actual.getButton(AlertDialog.BUTTON_POSITIVE).getText();
        setDescriptionOfActual("positiveButtonText", actualButtonText);
        return actualButtonText.equals(expectedButtonText);
    }
}
