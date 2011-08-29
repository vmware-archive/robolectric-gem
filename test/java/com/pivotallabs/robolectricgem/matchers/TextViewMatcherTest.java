package com.pivotallabs.robolectricgem.matchers;

import android.widget.TextView;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class TextViewMatcherTest {

    private TextView textView;
    private TextViewMatcher<TextView,?> matcher;

    @Before
    public void setUp() throws Exception {
        textView = new TextView(null);
        matcher = newTextViewMatcher(textView);
    }

    @Test
    public void test_toHaveText() throws Exception {
        textView.setText("Hello World");
        expect(matcher.toHaveText("Hello World")).toBeTrue();
        expect(matcher.toHaveText("Good Day")).toBeFalse();
    }

    @Test
    public void test_toHaveText_failureMessages() throws Exception {
        textView.setText("Hello World");
        matcher.toHaveText("hi");
        expect(matcher.getDescriptionOfActual()).toEqual("TextView[text=Hello World]");
    }

    @Test
    public void test_toHaveHint() throws Exception {
        textView.setHint("hint");
        expect(matcher.toHaveHint("hint")).toBeTrue();
        expect(matcher.toHaveHint("other hint")).toBeFalse();

        textView.setHint(null);
        expect(matcher.toHaveHint(null)).toBeTrue();
        expect(matcher.toHaveHint("")).toBeFalse();
        expect(matcher.toHaveHint("hint")).toBeFalse();
    }

    @Test
    public void test_toHaveHint_failureMessages() throws Exception {
        textView.setHint("actual hint");
        matcher.toHaveHint("expected hint");
        expect(matcher.getDescriptionOfActual()).toEqual("TextView[hint=actual hint]");

        matcher.toHaveHint("actual hint");
        expect(matcher.getDescriptionOfActual()).toEqual("TextView[hint=actual hint]");
    }

    private <T extends TextView> TextViewMatcher<T, ?> newTextViewMatcher(T value) {
        TextViewMatcher matcher = new TextViewMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }
}
