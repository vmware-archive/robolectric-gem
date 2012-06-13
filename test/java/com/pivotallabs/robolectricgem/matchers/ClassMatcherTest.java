package com.pivotallabs.robolectricgem.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class ClassMatcherTest {

    private ClassMatcher<Class, ?> matcher;

    @Before
    public void setUp() throws Exception {
        Class aClass = MiddleClass.class;
        matcher = newClassMatcher(aClass);
    }

    @Test
    public void shouldSupportIsAssignableToAndFrom() throws Exception {
        expect(matcher.toBeAssignableFrom(SuperClass.class)).toBeTrue();
        expect(matcher.toBeAssignableTo(BaseClass.class)).toBeTrue();
        expect(matcher.toBeAssignableFrom(BaseClass.class)).toBeFalse();
        expect(matcher.toBeAssignableTo(SuperClass.class)).toBeFalse();
    }

    private <T extends Class> ClassMatcher<T, ?> newClassMatcher(T value) {
        ClassMatcher matcher = new ClassMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

    private static class BaseClass {
    }

    private static class MiddleClass extends BaseClass {
    }

    private static class SuperClass extends MiddleClass {
    }
}
