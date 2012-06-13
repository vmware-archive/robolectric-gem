package com.pivotallabs.robolectricgem.matchers;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class AnimationMatcherTest {

    private Animation animation;
    private AnimationMatcher<Animation, ?> matcher;

    @Before
    public void setUp() throws Exception {
        animation = new RotateAnimation(null, null);
        matcher = newAnimationMatcher(animation);
    }

    @Test
    public void shouldKnowWhenAnimationIsStarted() throws Exception {
        animation.start();
        expect(matcher.toHaveStarted()).toBeTrue();

        animation.cancel();
        expect(matcher.toHaveStarted()).toBeFalse();
    }

    private <T extends Animation> AnimationMatcher<T, ?> newAnimationMatcher(T value) {
        AnimationMatcher matcher = new AnimationMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }
}
