package com.pivotallabs.robolectricgem.matchers;

import android.app.AlertDialog;
import android.widget.Button;
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
        return hasButtonText(expectedButtonText, AlertDialog.BUTTON_POSITIVE, "positiveButton");
    }

    public boolean toHaveNegativeButtonText(CharSequence expectedButtonText) {
        return hasButtonText(expectedButtonText, AlertDialog.BUTTON_NEGATIVE, "negativeButton");
    }

    public boolean toHaveNeutralButtonText(CharSequence expectedButtonText) {
        return hasButtonText(expectedButtonText, AlertDialog.BUTTON_NEUTRAL, "neutralButton");
    }

    private boolean hasButtonText(CharSequence expectedButtonText, int button, String propertyName) {
        Button actualButton = actual.getButton(button);
        if (actualButton == null) {
            setDescriptionOfActual(propertyName, null);
            return false;
        }
        CharSequence actualButtonText = actualButton.getText();
        setDescriptionOfActual(propertyName + "Text", actualButtonText);
        return actualButtonText.equals(expectedButtonText);
    }
}
