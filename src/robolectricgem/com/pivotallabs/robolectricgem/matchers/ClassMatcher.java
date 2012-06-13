package com.pivotallabs.robolectricgem.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Class.class)
public class ClassMatcher<T extends Class, M extends ClassMatcher<T, M>> extends AndroidMatcher<T, M>  {
    public boolean toBeAssignableFrom(Class aClass) {
        return actual.isAssignableFrom(aClass);
    }
    public boolean toBeAssignableTo(Class aClass) {
        return aClass.isAssignableFrom(actual);
    }
}

