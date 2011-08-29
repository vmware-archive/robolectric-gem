package com.pivotallabs.robolectricgem.matchers;

import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.pivotallabs.greatexpectations.MatcherOf;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@MatcherOf(ImageView.class)
public class ImageViewMatcher<T extends ImageView, M extends ImageViewMatcher<T, M>> extends ViewMatcher<T, M> {
    public boolean toBeLoadedFromResource(int expectedResourceId) {
        BitmapDrawable actualDrawable = (BitmapDrawable) actual.getDrawable();
        return actualDrawable != null &&
                shadowOf(actualDrawable.getBitmap()).getLoadedFromResourceId() == expectedResourceId;
    }
}
