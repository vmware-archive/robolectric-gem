package com.pivotallabs.robolectricgem.expect;

import com.pivotallabs.greatexpectations.BaseMatcher;
import com.pivotallabs.greatexpectations.ExpectGenerator;
import com.pivotallabs.robolectricgem.matchers.ActivityMatcher;
import com.pivotallabs.robolectricgem.matchers.AlertDialogMatcher;
import com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher;
import com.pivotallabs.robolectricgem.matchers.DialogMatcher;
import com.pivotallabs.robolectricgem.matchers.FakeHttpLayerMatcher;
import com.pivotallabs.robolectricgem.matchers.ImageViewMatcher;
import com.pivotallabs.robolectricgem.matchers.TextViewMatcher;
import com.pivotallabs.robolectricgem.matchers.ViewMatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * See https://github.com/xian/great-expectations
 *
 * Run this class to regenerate Expect.java.
 *
 * This class is designed to be subclassed, in case you want to extend
 * the list of matcher classes with your own matcher classes. The
 * subclass can provide its own main() method, which can create an
 * instance of itself and then call generateCustomExpect().
 * Override methods to change file path or add matcher classes.
 */
public class RunnableExpectGenerator extends ExpectGenerator {

    private String packageName;

    @SuppressWarnings({"unchecked"})
    protected static final Class<? extends BaseMatcher>[] CUSTOM_MATCHER_CLASSES = new Class[]{
            ActivityMatcher.class,
            AlertDialogMatcher.class,
            CompoundButtonMatcher.class,
            DialogMatcher.class,
            FakeHttpLayerMatcher.class,
            ImageViewMatcher.class,
            TextViewMatcher.class,
            ViewMatcher.class
    };

    public RunnableExpectGenerator(String packageName) {
        super(packageName);
        this.packageName = packageName;
    }

    public static void main(String args[]) throws IOException {
        new RunnableExpectGenerator(getExpectClassPackageName()).generateCustomExpect();
    }

    protected static String getExpectClassPackageName() {
        return Expect.class.getPackage().getName();
    }

    protected String getPackageName() {
        return packageName;
    }

    public void generateCustomExpect() throws FileNotFoundException {
        String packageName = getPackageName();
        String path = getExpectClassFilePath();
        System.out.println("path = " + path);
        System.out.println("packagename = " + packageName);
        RunnableExpectGenerator expectGenerator = new RunnableExpectGenerator(packageName);
        expectGenerator.setOut(new PrintStream(new File(path)));
        expectGenerator.generate();
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public List<Class<? extends BaseMatcher>> matcherClasses() {
        List<Class<? extends BaseMatcher>> classes = super.matcherClasses();
        classes.addAll(Arrays.asList(CUSTOM_MATCHER_CLASSES));
        return classes;
    }

    protected String getExpectClassFilePrefix() {
        return "src" + File.separator + "robolectricgem" + File.separator;
    }

    protected String getExpectClassFilePath() {
        return getExpectClassFilePrefix() +
                getPackageName().replace(".", File.separator) +
                File.separator +
                "Expect.java";
    }
}
