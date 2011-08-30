package com.pivotallabs.robolectricgem.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;
import com.xtremelabs.robolectric.tester.org.apache.http.FakeHttpLayer;

@MatcherOf(FakeHttpLayer.class)
public class FakeHttpLayerMatcher<T extends FakeHttpLayer, M extends FakeHttpLayerMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toHaveMadeAnyRequest() {
        return actual.hasRequestInfos();
    }
}
