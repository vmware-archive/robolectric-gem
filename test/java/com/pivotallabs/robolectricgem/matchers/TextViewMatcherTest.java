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

    private <T extends TextView> TextViewMatcher<T, ?> newTextViewMatcher(T value) {
        TextViewMatcher matcher = new TextViewMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }
}
