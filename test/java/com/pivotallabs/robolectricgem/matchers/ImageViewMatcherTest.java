package com.pivotallabs.robolectricgem.matchers;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.pivotallabs.greatexpectations.GreatExpectations;
import com.pivotallabs.robolectricgem.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

    @Test
    public void test_toBeLoadedFromResource_failureMessages() throws Exception {
        matcher.toBeLoadedFromResource(R.drawable.icon);
        expect(matcher.getDescriptionOfActual()).toEqual("ImageView[drawable=null]");

        imageView.setImageResource(R.drawable.icon);

        matcher.toBeLoadedFromResource(R.drawable.icon);
        expect(matcher.getDescriptionOfActual()).toMatch("ImageView\\[drawableResourceId=[0-9]+\\]");
        matcher.toBeLoadedFromResource(android.R.drawable.btn_minus);
        expect(matcher.getDescriptionOfActual()).toMatch("ImageView\\[drawableResourceId=[0-9]+\\]");
    }

    @Test
    public void test_toBeLoadedFromSource() throws Exception {
        expect(matcher.toBeLoadedFromSource("source sting")).toBeFalse();

        setSourceOnImageView("source string", imageView);

        expect(matcher.toBeLoadedFromSource("source string")).toBeTrue();
        expect(matcher.toBeLoadedFromSource("not the source string")).toBeFalse();

        setSourceOnImageView(null, imageView);
        expect(matcher.toBeLoadedFromSource(null)).toBeTrue();
        expect(matcher.toBeLoadedFromSource("source string")).toBeFalse();
    }

    @Test
    public void test_toBeLoadedFromSource_failureMessages() throws Exception {
        matcher.toBeLoadedFromSource("a source");
        expect(matcher.getDescriptionOfActual()).toEqual("ImageView[drawable=null]");

        setSourceOnImageView("source string", imageView);

        matcher.toBeLoadedFromSource("source string");
        expect(matcher.getDescriptionOfActual()).toEqual("ImageView[source=source string]");
        matcher.toBeLoadedFromSource("not the same source string");
        expect(matcher.getDescriptionOfActual()).toEqual("ImageView[source=source string]");
    }

    private void setSourceOnImageView(String source, ImageView imageView) {
        InputStream emptyInputStream = new ByteArrayInputStream("".getBytes());
        BitmapDrawable drawable = (BitmapDrawable) Drawable.createFromStream(emptyInputStream, source);
        imageView.setImageDrawable(drawable);
    }

    private <T extends ImageView> ImageViewMatcher<T, ?> newImageViewMatcher(T value) {
        ImageViewMatcher matcher = new ImageViewMatcher();
        GreatExpectations.setActual(matcher, value);
        return matcher;
    }
}
