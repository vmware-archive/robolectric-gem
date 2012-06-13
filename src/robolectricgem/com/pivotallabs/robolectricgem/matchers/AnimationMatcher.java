package com.pivotallabs.robolectricgem.matchers;

import android.view.animation.Animation;
import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Animation.class)
public class AnimationMatcher <T extends Animation, M extends AnimationMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toHaveStarted() {
        return actual.hasStarted();
    }
}
