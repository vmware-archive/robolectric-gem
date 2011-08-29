package com.pivotallabs.robolectricgem.sampleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import com.pivotallabs.robolectricgem.support.RobolectricTestRunnerWithInjection;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowDialog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.robolectricgem.expect.Expect.expect;

@RunWith(RobolectricTestRunnerWithInjection.class)
public class HelloWorldActivityTest {

    private TextView titleView;
    private EditText editText;
    private CheckBox checkBox;

    @Before
    public void setup() throws Exception {
        HelloWorldActivity activity = new HelloWorldActivity();
        activity.onCreate(null);

        titleView = (TextView) activity.findViewById(R.id.title);
        editText = (EditText) activity.findViewById(R.id.edit_text);
        checkBox = (CheckBox) activity.findViewById(R.id.check_box);
    }

    @Test
    public void shouldHaveATitle() {
        // Demonstrating different flavors of visibility checks
        expect(titleView).toBeVisible();
        expect(titleView).toHaveVisibility(View.VISIBLE);
        expect(titleView).not.toBeInvisible();
        expect(titleView).not.toBeGone();

        expect(titleView).toHaveText("Hello World");

        expect(titleView).toBeEnabled();
    }

    @Test
    public void shouldHaveHint() throws Exception {
        expect(editText).toHaveHint("Enter some text");
    }

    @Test
    public void shouldHaveCheckedCheckBox() throws Exception {
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
}
