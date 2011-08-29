package com.pivotallabs.robolectricgem.matchers;

import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;

public class AndroidMatcher<T, M extends AndroidMatcher<T,M>>  extends ObjectMatcher<T,M> {
    protected String getDescriptionOfExpected() {
        return descriptionOfExpected;
    }

    protected String getDescriptionOfActual() {
        return descriptionOfActual;
    }

    protected <T> boolean equalsAllowingNull(T actual, T expected) {
        if (actual == null) {
            return expected == null;
        }
        return actual.equals(expected);
    }
}
