package com.pivotallabs.robolectricgem.matchers;

import android.widget.Checkable;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class CheckableMatcherTest {
    private Checkable checkable;
    private CheckableMatcher<Checkable, ?> matcher;

    @Before
    public void setUp() throws Exception {
        checkable = new TrivialCheckable();
        matcher = newCheckableMatcher(checkable);
    }

    @Test
    public void test_toBeChecked() throws Exception {
        checkable.setChecked(true);
        expect(matcher.toBeChecked()).toBeTrue();

        checkable.setChecked(false);
        expect(matcher.toBeChecked()).toBeFalse();
    }

    private <T extends Checkable> CheckableMatcher<T, ?> newCheckableMatcher(T value) {
        CheckableMatcher matcher = new CheckableMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

    private static class TrivialCheckable implements Checkable {
        boolean isChecked;

        @Override
        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public boolean isChecked() {
            return isChecked;
        }

        @Override
        public void toggle() {
            isChecked = !isChecked;
        }
    }
}
