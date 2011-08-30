package com.pivotallabs.robolectricgem.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.tester.org.apache.http.FakeHttpLayer;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class FakeHttpLayerMatcherTest {

    private FakeHttpLayerMatcher<FakeHttpLayer, ?> matcher;
    private HttpClient httpClient = new DefaultHttpClient();

    @Before
    public void setup() throws Exception {
        matcher = newFakeHttpLayerMatcher(Robolectric.getFakeHttpLayer());
    }

    @Test
    public void test_toHaveMadeAnyRequest() throws Exception {
        expect(matcher.toHaveMadeAnyRequest()).toBeFalse();
        makeHttpGetRequest("http://localhost/foo.txt");
        expect(matcher.toHaveMadeAnyRequest()).toBeTrue();
    }

    @Test
    public void test_toHaveMadeAnyRequest_failureMessages() throws Exception {
        matcher.toHaveMadeAnyRequest();
        expect(matcher.getDescriptionOfActual()).toEqual("FakeHttpLayer[numRequests=0]");

        makeHttpGetRequest("http://localhost/foo.txt");

        matcher.toHaveMadeAnyRequest();
        expect(matcher.getDescriptionOfActual()).toEqual("FakeHttpLayer[numRequests=1]");
    }

    @Test
    public void test_toHaveMadeRequestMatching() throws Exception {
        FakeHttpLayer.UriRequestMatcher requestMatcher = new FakeHttpLayer.UriRequestMatcher("http://example.com");
        expect(matcher.toHaveMadeRequestMatching(requestMatcher)).toBeFalse();

        makeHttpGetRequest("http://does-not-match.com");
        expect(matcher.toHaveMadeRequestMatching(requestMatcher)).toBeFalse();

        makeHttpGetRequest("http://example.com");
        expect(matcher.toHaveMadeRequestMatching(requestMatcher)).toBeTrue();
    }

    @Test
    public void test_toHaveMadeRequestMatching_failureMessages() throws Exception {
        FakeHttpLayer.UriRequestMatcher requestMatcher = new FakeHttpLayer.UriRequestMatcher("http://example.com");

        matcher.toHaveMadeRequestMatching(requestMatcher);
        expect(matcher.getDescriptionOfActual()).toEqual("FakeHttpLayer[numRequests=0]");

        makeHttpGetRequest("http://localhost/foo.txt");

        matcher.toHaveMadeRequestMatching(requestMatcher);
        expect(matcher.getDescriptionOfActual()).toEqual("FakeHttpLayer[numRequests=1]");
    }

    private void makeHttpGetRequest(String uri) throws IOException {
        allowOneRequest();
        httpClient.execute(new HttpGet(uri));
    }

    private void allowOneRequest() {
        Robolectric.addPendingHttpResponse(200, "response body");
    }

    private <T extends FakeHttpLayer> FakeHttpLayerMatcher<T, ?> newFakeHttpLayerMatcher(T value) {
        FakeHttpLayerMatcher matcher = new FakeHttpLayerMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
