package com.pivotallabs.robolectricgem.expect;

import com.pivotallabs.greatexpectations.BaseMatcher;
import com.pivotallabs.greatexpectations.ExpectGenerator;
import com.pivotallabs.robolectricgem.matchers.AlertDialogMatcher;
import com.pivotallabs.robolectricgem.matchers.CompoundButtonMatcher;
import com.pivotallabs.robolectricgem.matchers.DialogMatcher;
import com.pivotallabs.robolectricgem.matchers.ImageViewMatcher;
import com.pivotallabs.robolectricgem.matchers.TextViewMatcher;
import com.pivotallabs.robolectricgem.matchers.ViewMatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * See https://github.com/xian/great-expectations
 */
public class RunnableExpectGenerator extends ExpectGenerator {

    @SuppressWarnings({"unchecked"})
    public static final Class<? extends BaseMatcher>[] CUSTOM_MATCHER_CLASSES = new Class[] {
            AlertDialogMatcher.class,
            CompoundButtonMatcher.class,
            DialogMatcher.class,
            ImageViewMatcher.class,
            TextViewMatcher.class,
            ViewMatcher.class
    };

    public RunnableExpectGenerator(String packageName) {
        super(packageName);
    }

    public static void main(String args[]) throws java.io.IOException {
        generateCustomExpect();
    }

    public static void generateCustomExpect() throws FileNotFoundException {
        String packageName = Expect.class.getPackage().getName();
        String path = "src/robolectricgem/" + packageName.replace(".", "/") + "/Expect.java";
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
}
