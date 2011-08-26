package com.pivotallabs.robolectricgem.matchers.view;

import android.view.View;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class ViewMatcherTest {

    private View view;

    @Before
    public void setup() throws Exception {
        view = new View(null);
    }

    @Test
    public void toBeVisible_shouldReturnTrueOnlyForVisibleViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        assertTrue(newViewMatcher(view).toBeVisible());

        view.setVisibility(View.INVISIBLE);
        assertFalse(newViewMatcher(view).toBeVisible());

        view.setVisibility(View.GONE);
        assertFalse(newViewMatcher(view).toBeVisible());
    }

    @Test
    public void toBeInvisible_shouldReturnTrueOnlyForInvisibleViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        assertFalse(newViewMatcher(view).toBeInvisible());

        view.setVisibility(View.INVISIBLE);
        assertTrue(newViewMatcher(view).toBeInvisible());

        view.setVisibility(View.GONE);
        assertFalse(newViewMatcher(view).toBeInvisible());
    }

    @Test
    public void toBeGone_shouldReturnTrueOnlyForGoneViews() throws Exception {
        view.setVisibility(View.VISIBLE);
        assertFalse(newViewMatcher(view).toBeGone());

        view.setVisibility(View.INVISIBLE);
        assertFalse(newViewMatcher(view).toBeGone());

        view.setVisibility(View.GONE);
        assertTrue(newViewMatcher(view).toBeGone());
    }

    @Test
    public void test_toHaveVisibility() throws Exception {
        view.setVisibility(View.VISIBLE);
        assertTrue(newViewMatcher(view).toHaveVisibility(View.VISIBLE));
        assertFalse(newViewMatcher(view).toHaveVisibility(View.INVISIBLE));
        assertFalse(newViewMatcher(view).toHaveVisibility(View.GONE));

        view.setVisibility(View.INVISIBLE);
        assertFalse(newViewMatcher(view).toHaveVisibility(View.VISIBLE));
        assertTrue(newViewMatcher(view).toHaveVisibility(View.INVISIBLE));
        assertFalse(newViewMatcher(view).toHaveVisibility(View.GONE));

        view.setVisibility(View.GONE);
        assertFalse(newViewMatcher(view).toHaveVisibility(View.VISIBLE));
        assertFalse(newViewMatcher(view).toHaveVisibility(View.INVISIBLE));
        assertTrue(newViewMatcher(view).toHaveVisibility(View.GONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void toHaveVisibility_throwsException() throws Exception {
        newViewMatcher(view).toHaveVisibility(View.VISIBLE + View.INVISIBLE + View.GONE);
    }

    private <T extends View> ViewMatcher<T, ?> newViewMatcher(T value) {
        ViewMatcher matcher = new ViewMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
