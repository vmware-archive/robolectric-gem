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

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class FakeHttpLayerMatcherTest {

    private FakeHttpLayerMatcher<FakeHttpLayer, ?> matcher;

    @Before
    public void setup() throws Exception {
        matcher = newFakeHttpLayerMatcher(Robolectric.getFakeHttpLayer());
    }

    @Test
    public void test_toHaveMadeAnyRequest() throws Exception {
        expect(matcher.toHaveMadeAnyRequest()).toBeFalse();

        Robolectric.addPendingHttpResponse(200, "response body");

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://localhost/foo.txt");
        httpClient.execute(httpGet);

        expect(matcher.toHaveMadeAnyRequest()).toBeTrue();
    }

    private <T extends FakeHttpLayer> FakeHttpLayerMatcher<T, ?> newFakeHttpLayerMatcher(T value) {
        FakeHttpLayerMatcher matcher = new FakeHttpLayerMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
