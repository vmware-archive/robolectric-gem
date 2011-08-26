package com.pivotallabs.robolectricgem.matchers.view;

import android.view.View;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class ViewMatcherTest {

    @Test
    public void toBeVisible_shouldReturnTrueForVisibleViews() throws Exception {
        View view = new View(null);

        view.setVisibility(View.VISIBLE);
        assertTrue(newViewMatcher(view).toBeVisible());

        view.setVisibility(View.INVISIBLE);
        assertFalse(newViewMatcher(view).toBeVisible());

        view.setVisibility(View.GONE);
        assertFalse(newViewMatcher(view).toBeVisible());
    }

    private <T extends View> ViewMatcher<T, ?> newViewMatcher(T value) {
        ViewMatcher matcher = new ViewMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
