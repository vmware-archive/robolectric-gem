package com.pivotallabs.robolectricgem.matchers;

import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;

public class AndroidMatcher<T, M extends AndroidMatcher<T,M>>  extends ObjectMatcher<T,M> {
    protected String getDescriptionOfExpected() {
        return descriptionOfExpected;
    }

    protected String getDescriptionOfActual() {
        return descriptionOfActual;
    }
}
