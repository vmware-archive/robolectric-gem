package com.pivotallabs.robolectricgem.matchers;

import android.app.Dialog;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class DialogMatcherTest {

    private Dialog dialog;
    private DialogMatcher<Dialog, ?> matcher;

    @Before
    public void setUp() throws Exception {
        dialog = new Dialog(null);
        matcher = newDialogMatcher(dialog);
    }

    @Test
    public void test_toHaveTitle() throws Exception {
        dialog.setTitle(null);
        expect(matcher.toHaveTitle(null)).toBeTrue();

        dialog.setTitle("the title");
        expect(matcher.toHaveTitle("the title")).toBeTrue();
        expect(matcher.toHaveTitle("other title")).toBeFalse();
    }

    @Test
    public void test_toHaveTitle_failureMessages() throws Exception {
        dialog.setTitle("the title");
        matcher.toHaveTitle("the title");
        expect(matcher.getDescriptionOfActual()).toEqual("Dialog[title=the title]");
        matcher.toHaveTitle("not the same title");
        expect(matcher.getDescriptionOfActual()).toEqual("Dialog[title=the title]");
    }

    private <T extends Dialog> DialogMatcher<T, ?> newDialogMatcher(T value) {
        DialogMatcher matcher = new DialogMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }
}
