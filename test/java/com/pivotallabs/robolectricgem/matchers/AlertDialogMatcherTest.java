package com.pivotallabs.robolectricgem.matchers;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        expect(matcher.toHavePositiveButtonText("")).toBeFalse();

        builder.setPositiveButton(null, new NoOpClickListener());
        createDialog(builder);
        expect(matcher.toHavePositiveButtonText("")).toBeTrue();

        builder.setPositiveButton("button text", null);
        createDialog(builder);
        expect(matcher.toHavePositiveButtonText("button text")).toBeTrue();
        expect(matcher.toHavePositiveButtonText("other text")).toBeFalse();
    }

    @Test
    public void test_toHavePositiveButtonText_failureMessages() throws Exception {
        builder.setPositiveButton(null, null);
        createDialog(builder);

        matcher.toHavePositiveButtonText("anything");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[positiveButton=null]");

        builder.setPositiveButton("button text", null);
        createDialog(builder);

        matcher.toHavePositiveButtonText("button text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[positiveButtonText=button text]");
        matcher.toHavePositiveButtonText("not the same text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[positiveButtonText=button text]");
    }

    @Test
    public void test_toHaveNegativeButtonText() throws Exception {
        builder.setNegativeButton(null, new NoOpClickListener());
        createDialog(builder);
        expect(matcher.toHaveNegativeButtonText("")).toBeTrue();

        builder.setNegativeButton("button text", null);
        createDialog(builder);
        expect(matcher.toHaveNegativeButtonText("button text")).toBeTrue();
        expect(matcher.toHaveNegativeButtonText("other text")).toBeFalse();
    }

    @Test
    public void test_toHaveNegativeButtonText_failureMessages() throws Exception {
        builder.setNegativeButton("button text", null);
        createDialog(builder);

        matcher.toHaveNegativeButtonText("button text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[negativeButtonText=button text]");
        matcher.toHaveNegativeButtonText("not the same text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[negativeButtonText=button text]");
    }
    
    @Test
    public void test_toHaveNeutralButtonText() throws Exception {
        builder.setNeutralButton(null, new NoOpClickListener());
        createDialog(builder);
        expect(matcher.toHaveNeutralButtonText("")).toBeTrue();

        builder.setNeutralButton("button text", null);
        createDialog(builder);
        expect(matcher.toHaveNeutralButtonText("button text")).toBeTrue();
        expect(matcher.toHaveNeutralButtonText("other text")).toBeFalse();
    }

    @Test
    public void test_toHaveNeutralButtonText_failureMessages() throws Exception {
        builder.setNeutralButton("button text", null);
        createDialog(builder);

        matcher.toHaveNeutralButtonText("button text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[neutralButtonText=button text]");
        matcher.toHaveNeutralButtonText("not the same text");
        expect(matcher.getDescriptionOfActual()).toEqual("AlertDialog[neutralButtonText=button text]");
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

    private static class NoOpClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    }
}
