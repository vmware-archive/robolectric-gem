package com.pivotallabs.robolectricgem.matchers;

import android.view.View;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class ViewMatcherTest {

    private View view;
    private ViewMatcher<View, ?> matcher;

    @Before
    public void setup() throws Exception {
        view = new View(null);
        matcher = newViewMatcher(view);
    }

    @Test
    public void toBeVisible_shouldReturnTrueOnlyForVisibleViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        expect(matcher.toBeVisible()).toBeTrue();

        view.setVisibility(View.INVISIBLE);
        expect(matcher.toBeVisible()).toBeFalse();

        view.setVisibility(View.GONE);
        expect(matcher.toBeVisible()).toBeFalse();
    }

    @Test
    public void toBeInvisible_shouldReturnTrueOnlyForInvisibleViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        expect(matcher.toBeInvisible()).toBeFalse();

        view.setVisibility(View.INVISIBLE);
        expect(matcher.toBeInvisible()).toBeTrue();

        view.setVisibility(View.GONE);
        expect(matcher.toBeInvisible()).toBeFalse();
    }

    @Test
    public void toBeGone_shouldReturnTrueOnlyForGoneViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        expect(matcher.toBeGone()).toBeFalse();

        view.setVisibility(View.INVISIBLE);
        expect(matcher.toBeGone()).toBeFalse();

        view.setVisibility(View.GONE);
        expect(matcher.toBeGone()).toBeTrue();
    }

    @Test
    public void test_toHaveVisibility() throws Exception {
        view.setVisibility(View.VISIBLE);
        expect(matcher.toHaveVisibility(View.VISIBLE)).toBeTrue();
        expect(matcher.toHaveVisibility(View.INVISIBLE)).toBeFalse();
        expect(matcher.toHaveVisibility(View.GONE)).toBeFalse();

        view.setVisibility(View.INVISIBLE);
        expect(matcher.toHaveVisibility(View.VISIBLE)).toBeFalse();
        expect(matcher.toHaveVisibility(View.INVISIBLE)).toBeTrue();
        expect(matcher.toHaveVisibility(View.GONE)).toBeFalse();

        view.setVisibility(View.GONE);
        expect(matcher.toHaveVisibility(View.VISIBLE)).toBeFalse();
        expect(matcher.toHaveVisibility(View.INVISIBLE)).toBeFalse();
        expect(matcher.toHaveVisibility(View.GONE)).toBeTrue();
    }

    @Test
    public void test_toHaveVisibility_failureMessages() throws Exception {
        view.setVisibility(View.VISIBLE);
        matcher.toHaveVisibility(View.VISIBLE);
        expect(matcher.getDescriptionOfExpected()).toEqual("View.VISIBLE");
        expect(matcher.getDescriptionOfActual()).toEqual("View[visibility=View.VISIBLE]");

        view.setVisibility(View.INVISIBLE);
        matcher.toHaveVisibility(View.INVISIBLE);
        expect(matcher.getDescriptionOfExpected()).toEqual("View.INVISIBLE");
        expect(matcher.getDescriptionOfActual()).toEqual("View[visibility=View.INVISIBLE]");

        view.setVisibility(View.GONE);
        matcher.toHaveVisibility(View.GONE);
        expect(matcher.getDescriptionOfExpected()).toEqual("View.GONE");
        expect(matcher.getDescriptionOfActual()).toEqual("View[visibility=View.GONE]");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void toHaveVisibility_throwsException() throws Exception {
        // tests that if visibility is not a valid int value, the code throws an exception
        matcher.toHaveVisibility(View.VISIBLE + View.INVISIBLE + View.GONE);
    }

    private <T extends View> ViewMatcher<T, ?> newViewMatcher(T value) {
        ViewMatcher matcher = new ViewMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
