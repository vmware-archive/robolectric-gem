package com.pivotallabs.robolectricgem.matchers;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.pivotallabs.greatexpectations.MatcherOf;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@MatcherOf(ImageView.class)
public class ImageViewMatcher<T extends ImageView, M extends ImageViewMatcher<T, M>> extends ViewMatcher<T, M> {
    public boolean toBeLoadedFromResource(int expectedResourceId) {
        Drawable actualDrawable = actual.getDrawable();
        if (actualDrawable == null) {
            setDescriptionOfActual("drawable", null);
            return false;
        }
        int actualResourceId = shadowOf(actualDrawable).getLoadedFromResourceId();
        setDescriptionOfActual("drawableResourceId", actualResourceId);
        return actualResourceId == expectedResourceId;
    }

    public boolean toBeLoadedFromSource(String expectedSource) {
        BitmapDrawable actualDrawable = (BitmapDrawable) actual.getDrawable();
        if (actualDrawable == null) {
            setDescriptionOfActual("drawable", null);
            return false;
        }
        String actualSource = shadowOf(actualDrawable).getSource();
        setDescriptionOfActual("source", actualSource);
        return equalsAllowingNull(actualSource, expectedSource);
    }
}
