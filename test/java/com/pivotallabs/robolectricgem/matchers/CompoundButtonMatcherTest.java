package com.pivotallabs.robolectricgem.matchers;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class CompoundButtonMatcherTest {
    private CheckBox checkbox;
    private CompoundButtonMatcher<? extends CompoundButton, ?> matcher;

    @Before
    public void setup() throws Exception {
        checkbox = new CheckBox(null);
        matcher = newCompoundButtonMatcher(checkbox);
    }

    @Test
    public void toBeChecked_shouldReturnTrueOnlyForCheckedViews() throws Exception {
        checkbox.setChecked(true);
        expect(matcher.toBeChecked()).toBeTrue();

        checkbox.setChecked(false);
        expect(matcher.toBeChecked()).toBeFalse();
    }

    private <T extends CompoundButton> CompoundButtonMatcher<T, ?> newCompoundButtonMatcher(T value) {
        CompoundButtonMatcher matcher = new CompoundButtonMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
