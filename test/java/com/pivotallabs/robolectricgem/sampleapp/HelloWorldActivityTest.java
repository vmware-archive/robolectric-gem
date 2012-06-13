package com.pivotallabs.robolectricgem.sampleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import com.pivotallabs.robolectricgem.support.RobolectricTestRunnerWithInjection;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowDialog;
import com.xtremelabs.robolectric.tester.org.apache.http.FakeHttpLayer;
import com.xtremelabs.robolectric.tester.org.apache.http.TestHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunnerWithInjection.class)
public class HelloWorldActivityTest {

    private HelloWorldActivity activity;

    @Before
    public void setup() throws Exception {
        Robolectric.addHttpResponseRule(new FakeHttpLayer.UriRegexMatcher("GET", ".*/robolectric.png"),
                new TestHttpResponse());

        activity = new HelloWorldActivity();
        activity.onCreate(null);
    }

    @Test
    public void shouldHaveATitle() {
        TextView titleView = (TextView) activity.findViewById(R.id.title);

        // Demonstrating different flavors of visibility checks
        expect(titleView).toBeVisible();
        expect(titleView).toHaveVisibility(View.VISIBLE);
        expect(titleView).not.toBeInvisible();
        expect(titleView).not.toBeGone();

        expect(titleView).toHaveText("Hello World");

        expect(titleView).toBeEnabled();
        expect(titleView).not.toBeClickable();
        expect(titleView).not.toBeFocusable();
    }

    @Test
    public void shouldShowAnIconLoadedFromResources() throws Exception {
        ImageView iconImageView = (ImageView) activity.findViewById(R.id.icon_image_view);
        expect(iconImageView).toBeLoadedFromResource(R.drawable.icon);
    }

    @Test
    public void shouldShowAnIconLoadedFromTheWeb() throws Exception {
        ImageView logoImageView = (ImageView) activity.findViewById(R.id.logo_image_view);
        expect(logoImageView).toBeLoadedFromSource(HelloWorldActivity.ROBOLECTRIC_LOGO_URL);
    }

    @Test
    public void shouldHaveHintOnEditText() throws Exception {
        EditText editText = (EditText) activity.findViewById(R.id.edit_text);
        expect(editText).toHaveHint("Enter some text");
    }

    @Test
    public void shouldHaveCheckedCheckBox() throws Exception {
        CheckBox checkBox = (CheckBox) activity.findViewById(R.id.check_box);
        expect(checkBox).toBeChecked();
    }

    @Test
    public void shouldShowADialog_whenTheActivityIsCreated() throws Exception {
        Dialog dialog = ShadowDialog.getLatestDialog();
        expect(dialog).toHaveTitle("Hi!");
        expect(dialog).toBeShowing();

        Robolectric.clickOn(dialog.findViewById(R.id.dismiss_button));

        expect(dialog).toBeDismissed();
    }

    @Test
    public void shouldShowAnAlertDialog_whenTheFirstDialogIsDismissed() throws Exception {
        Robolectric.clickOn(ShadowDialog.getLatestDialog().findViewById(R.id.dismiss_button));
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();

        expect(alertDialog).toBeShowing();
        expect(alertDialog).toHaveMessage("Thanks for running the sample app.");
        expect(alertDialog).toHavePositiveButtonText("OK");
        expect(alertDialog).toHaveNeutralButtonText("Really?");
        expect(alertDialog).toHaveNegativeButtonText("No, thanks");
    }

    @Test
    public void shouldReplaceTheLogoWithAnotherImage_whenTheLogoIsClicked() throws Exception {
        FakeHttpLayer.DefaultRequestMatcher logoRequestMatcher =
                new FakeHttpLayer.DefaultRequestMatcher("GET", HelloWorldActivity.PIVOTAL_LOGO_URL);
        Robolectric.addHttpResponseRule(logoRequestMatcher, new TestHttpResponse());

        Robolectric.getFakeHttpLayer().clearRequestInfos();
        expect(Robolectric.getFakeHttpLayer()).not.toHaveMadeAnyRequest();

        ImageView logoImageView = (ImageView) activity.findViewById(R.id.logo_image_view);
        Robolectric.clickOn(logoImageView);

        expect(Robolectric.getFakeHttpLayer()).toHaveMadeRequestMatching(logoRequestMatcher);
        expect(logoImageView).toBeLoadedFromSource(HelloWorldActivity.PIVOTAL_LOGO_URL);
    }

    @Test
    public void shouldFinishTheActivity_whenTheQuitButtonIsClicked() throws Exception {
        Button quitButton = (Button) activity.findViewById(R.id.quit_button);
        expect(activity).not.toBeFinishing();
        Robolectric.clickOn(quitButton);
        expect(activity).toBeFinishing();
    }
}
