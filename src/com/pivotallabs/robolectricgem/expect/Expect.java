package com.pivotallabs.robolectricgem.expect;

import com.pivotallabs.greatexpectations.matchers.BooleanMatcher;
import com.pivotallabs.greatexpectations.matchers.ComparableMatcher;
import com.pivotallabs.greatexpectations.matchers.DateMatcher;
import com.pivotallabs.greatexpectations.matchers.IterableMatcher;
import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;
import com.pivotallabs.greatexpectations.matchers.StringMatcher;

import static com.pivotallabs.greatexpectations.GreatExpectations.wrapped;

public class Expect {
    public static <T extends Object, M extends ObjectMatcher<T, M>> ObjectMatcher<T, ?> expect(T actual) {
        return wrapped(ObjectMatcher.class, actual);
    }
    public static BooleanMatcher<Boolean, ?> expect(boolean actual) {
        return wrapped(BooleanMatcher.class, actual);
    }
    public static <T extends Boolean, M extends BooleanMatcher<T, M>> BooleanMatcher<T, ?> expect(T actual) {
        return wrapped(BooleanMatcher.class, actual);
    }
    public static <T extends Comparable, M extends ComparableMatcher<T, M>> ComparableMatcher<T, ?> expect(T actual) {
        return wrapped(ComparableMatcher.class, actual);
    }
    public static <T extends java.util.Date, M extends DateMatcher<T, M>> DateMatcher<T, ?> expect(T actual) {
        return wrapped(DateMatcher.class, actual);
    }
    public static <T extends Iterable<X>, X, M extends IterableMatcher<T, X, M>> IterableMatcher<T, X, ?> expect(T actual) {
        return wrapped(IterableMatcher.class, actual);
    }
    public static <T extends String, M extends StringMatcher<T, M>> StringMatcher<T, ?> expect(T actual) {
        return wrapped(StringMatcher.class, actual);
    }
    public static <T extends android.widget.CompoundButton, M extends com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher.class, actual);
    }
    public static <T extends android.widget.TextView, M extends com.pivotallabs.robolectricgem.matchers.TextViewMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.TextViewMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.TextViewMatcher.class, actual);
    }
    public static <T extends android.view.View, M extends com.pivotallabs.robolectricgem.matchers.ViewMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.ViewMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.ViewMatcher.class, actual);
    }
}
