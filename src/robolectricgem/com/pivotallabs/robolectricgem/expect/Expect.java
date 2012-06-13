package com.pivotallabs.robolectricgem.expect;

import com.pivotallabs.greatexpectations.matchers.BooleanMatcher;
import com.pivotallabs.greatexpectations.matchers.ComparableMatcher;
import com.pivotallabs.greatexpectations.matchers.DateMatcher;
import com.pivotallabs.greatexpectations.matchers.DoubleMatcher;
import com.pivotallabs.greatexpectations.matchers.FloatMatcher;
import com.pivotallabs.greatexpectations.matchers.IntegerMatcher;
import com.pivotallabs.greatexpectations.matchers.IterableMatcher;
import com.pivotallabs.greatexpectations.matchers.LongMatcher;
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
    public static <T extends Integer, M extends IntegerMatcher<T, M>> IntegerMatcher<T, ?> expect(T actual) {
        return wrapped(IntegerMatcher.class, actual);
    }
    public static IntegerMatcher<Integer, ?> expect(int actual) {
        return wrapped(IntegerMatcher.class, actual);
    }
    public static <T extends Long, M extends LongMatcher<T, M>> LongMatcher<T, ?> expect(T actual) {
        return wrapped(LongMatcher.class, actual);
    }
    public static LongMatcher<Long, ?> expect(long actual) {
        return wrapped(LongMatcher.class, actual);
    }
    public static <T extends Float, M extends FloatMatcher<T, M>> FloatMatcher<T, ?> expect(T actual) {
        return wrapped(FloatMatcher.class, actual);
    }
    public static FloatMatcher<Float, ?> expect(float actual) {
        return wrapped(FloatMatcher.class, actual);
    }
    public static <T extends Double, M extends DoubleMatcher<T, M>> DoubleMatcher<T, ?> expect(T actual) {
        return wrapped(DoubleMatcher.class, actual);
    }
    public static DoubleMatcher<Double, ?> expect(double actual) {
        return wrapped(DoubleMatcher.class, actual);
    }
    public static <T extends android.app.Activity, M extends com.pivotallabs.robolectricgem.matchers.ActivityMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.ActivityMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.ActivityMatcher.class, actual);
    }
    public static <T extends android.view.animation.Animation, M extends com.pivotallabs.robolectricgem.matchers.AnimationMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.AnimationMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.AnimationMatcher.class, actual);
    }
    public static <T extends android.app.AlertDialog, M extends com.pivotallabs.robolectricgem.matchers.AlertDialogMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.AlertDialogMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.AlertDialogMatcher.class, actual);
    }
    public static <T extends android.widget.CompoundButton, M extends com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher.class, actual);
    }
    public static <T extends android.app.Dialog, M extends com.pivotallabs.robolectricgem.matchers.DialogMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.DialogMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.DialogMatcher.class, actual);
    }
    public static <T extends com.xtremelabs.robolectric.tester.org.apache.http.FakeHttpLayer, M extends com.pivotallabs.robolectricgem.matchers.FakeHttpLayerMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.FakeHttpLayerMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.FakeHttpLayerMatcher.class, actual);
    }
    public static <T extends android.widget.ImageView, M extends com.pivotallabs.robolectricgem.matchers.ImageViewMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.ImageViewMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.ImageViewMatcher.class, actual);
    }
    public static <T extends android.widget.TextView, M extends com.pivotallabs.robolectricgem.matchers.TextViewMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.TextViewMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.TextViewMatcher.class, actual);
    }
    public static <T extends android.view.View, M extends com.pivotallabs.robolectricgem.matchers.ViewMatcher<T, M>> com.pivotallabs.robolectricgem.matchers.ViewMatcher<T, ?> expect(T actual) {
        return wrapped(com.pivotallabs.robolectricgem.matchers.ViewMatcher.class, actual);
    }
}
