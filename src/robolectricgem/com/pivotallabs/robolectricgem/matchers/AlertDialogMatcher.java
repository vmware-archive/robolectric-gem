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
        return hasButtonText(expectedButtonText, AlertDialog.BUTTON_POSITIVE, "positiveButtonText");
    }

    public boolean toHaveNegativeButtonText(CharSequence expectedButtonText) {
        return hasButtonText(expectedButtonText, AlertDialog.BUTTON_NEGATIVE, "negativeButtonText");
    }

    public boolean toHaveNeutralButtonText(CharSequence expectedButtonText) {
        return hasButtonText(expectedButtonText, AlertDialog.BUTTON_NEUTRAL, "neutralButtonText");
    }

    private boolean hasButtonText(CharSequence expectedButtonText, int button, String propertyName) {
        CharSequence actualButtonText = actual.getButton(button).getText();
        setDescriptionOfActual(propertyName, actualButtonText);
        return actualButtonText.equals(expectedButtonText);
    }
}
