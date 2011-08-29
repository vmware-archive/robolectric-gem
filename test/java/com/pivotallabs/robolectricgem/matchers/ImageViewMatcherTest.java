package com.pivotallabs.robolectricgem.matchers;

import android.app.Activity;
import android.widget.ImageView;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.pivotallabs.robolectricgem.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunner.class)
public class ImageViewMatcherTest {

    private ImageView imageView;
    private ImageViewMatcher<ImageView, ?> matcher;

    @Before
    public void setup() throws Exception {
        imageView = new ImageView(new Activity());
        matcher = newImageViewMatcher(imageView);
    }

    @Test
    public void test_toBeLoadedFromResource() throws Exception {
        expect(matcher.toBeLoadedFromResource(R.drawable.icon)).toBeFalse();

        imageView.setImageResource(R.drawable.icon);
        expect(matcher.toBeLoadedFromResource(R.drawable.icon)).toBeTrue();
        expect(matcher.toBeLoadedFromResource(android.R.drawable.btn_minus)).toBeFalse();
    }

    private <T extends ImageView> ImageViewMatcher<T, ?> newImageViewMatcher(T value) {
        ImageViewMatcher matcher = new ImageViewMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }

}
