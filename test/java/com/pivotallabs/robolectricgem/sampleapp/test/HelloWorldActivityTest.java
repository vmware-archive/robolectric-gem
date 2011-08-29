package com.pivotallabs.robolectricgem.sampleapp.test;

import android.app.Dialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.pivotallabs.robolectricgem.R;
import com.pivotallabs.robolectricgem.sampleapp.app.HelloWorldActivity;
import com.pivotallabs.robolectricgem.support.RobolectricTestRunnerWithInjection;
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
    public void shouldShowADialog() throws Exception {
        Dialog dialog = ShadowDialog.getLatestDialog();
        expect(dialog).toHaveTitle("Hi!");
    }
}
