package com.pivotallabs.robolectricgem.matchers;

import android.app.AlertDialog;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class AlertDialogMatcherTest {

    private AlertDialogMatcher<AlertDialog, ?> matcher;
    private AlertDialog.Builder builder;

    @Before
    public void setup() throws Exception {
        builder = new AlertDialog.Builder(null);
    }

    @Test
    public void test_toHaveMessage() throws Exception {
        createDialog(builder);
        expect(matcher.toHaveMessage(null)).toBeTrue();

        builder.setMessage("the message");
        createDialog(builder);
        expect(matcher.toHaveMessage("the message")).toBeTrue();
        expect(matcher.toHaveMessage("other message")).toBeFalse();
    }

    @Test
    public void test_toHaveMessage_failureMessages() throws Exception {
        builder.setMessage("the message");
        createDialog(builder);

        matcher.toHaveMessage("the message");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[message=the message]");
        matcher.toHaveMessage("not the same message");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[message=the message]");
    }

    @Test
    public void test_toHavePositiveButtonText() throws Exception {
        builder.setPositiveButton(null, null);
        createDialog(builder);
        expect(matcher.toHavePositiveButtonText("")).toBeTrue();

        builder.setPositiveButton("button text", null);
        createDialog(builder);
        expect(matcher.toHavePositiveButtonText("button text")).toBeTrue();
        expect(matcher.toHavePositiveButtonText("other text")).toBeFalse();
    }

    @Test
    public void test_toHavePositiveButtonText_failureMessages() throws Exception {
        builder.setPositiveButton("button text", null);
        createDialog(builder);

        matcher.toHavePositiveButtonText("button text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[positiveButtonText=button text]");
        matcher.toHavePositiveButtonText("not the same text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[positiveButtonText=button text]");
    }

    private void createDialog(AlertDialog.Builder builder) {
        AlertDialog dialog = builder.create();
        matcher = newAlertDialogMatcher(dialog);
    }

    private <T extends AlertDialog> AlertDialogMatcher<T, ?> newAlertDialogMatcher(T value) {
        AlertDialogMatcher matcher = new AlertDialogMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }
}
