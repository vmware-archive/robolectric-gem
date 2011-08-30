package com.pivotallabs.robolectricgem.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.tester.org.apache.http.FakeHttpLayer;
import com.xtremelabs.robolectric.tester.org.apache.http.HttpRequestInfo;
import com.xtremelabs.robolectric.tester.org.apache.http.RequestMatcher;

import java.util.List;

@MatcherOf(FakeHttpLayer.class)
public class FakeHttpLayerMatcher<T extends FakeHttpLayer, M extends FakeHttpLayerMatcher<T, M>> extends AndroidMatcher<T, M> {
    public boolean toHaveMadeAnyRequest() {
        int numberOfRequests = Robolectric.getFakeHttpLayer().getSentHttpRequestInfos().size();
        setDescriptionOfActual("numRequests",  numberOfRequests);
        return actual.hasRequestInfos();
    }

    public boolean toHaveMadeRequestMatching(RequestMatcher requestMatcher) {
        List<HttpRequestInfo> requestInfos = Robolectric.getFakeHttpLayer().getSentHttpRequestInfos();
        setDescriptionOfActual("numRequests",  requestInfos.size());
        for (HttpRequestInfo info : requestInfos) {
            if (requestMatcher.matches(info.getHttpRequest())) {
                return true;
            }
        }
        return false;
    }
}
