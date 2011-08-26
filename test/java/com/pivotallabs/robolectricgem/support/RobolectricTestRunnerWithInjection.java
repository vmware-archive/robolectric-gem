package com.pivotallabs.robolectricgem.support;

import com.pivotallabs.robolectricgem.sampleapp.app.SampleApplication;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.runners.model.InitializationError;
import roboguice.config.AbstractAndroidModule;

public class RobolectricTestRunnerWithInjection extends RobolectricTestRunner {

    public RobolectricTestRunnerWithInjection(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    public void prepareTest(Object test) {
        SampleApplication application = (SampleApplication) Robolectric.application;
        application.setModule(new TestApplicationModule());
        application.getInjector().injectMembers(test);
    }

    public static class TestApplicationModule extends AbstractAndroidModule {
        @Override
        protected void configure() {
            //bind(CurrentTime.class).toInstance(new FakeCurrentTime());
        }
    }

}
