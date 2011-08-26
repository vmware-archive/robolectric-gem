robolectric-gem
==================

## Robolectric Great-Expections Matchers (robolectric-gem)

This project aims to create a library of [great-expections](https://github.com/xian/great-expectations) matcher
classes for [Robolectric](https://github.com/pivotal/robolectric) Android unit tests.

To use this library in your project, download robolectric-gem.jar and add it to your classpath for your unit tests.
This allows you to write unit test assertions for Android classes in the style of great-expectations, like this:

    @Test
    public void shouldHaveATitle() {
        HelloWorldActivity activity = new HelloWorldActivity();
        activity.onCreate(null);
        TextView titleView = (TextView) activity.findViewById(R.id.title);

        // toHaveText() is a method provided by robolectric-gem
        expect(titleView).toHaveText("Hello World");
    }

For more examples, see the included
[sample Android app](https://github.com/pivotal/robolectric-gem/tree/master/test/java/com/pivotallabs/robolectricgem/sampleapp/app)
and
[its tests](https://github.com/pivotal/robolectric-gem/tree/master/test/java/com/pivotallabs/robolectricgem/sampleapp/test).

### Contributing

We welcome your contributions.

Before working on the source code for the project, install the Android SDK as required by Robolectric. Currently, that
is Android SDK level 10 (Android 2.3.3) with Google APIs (aka Google Maps APIs). Make sure the `tools` and
`platform-tools` SDK directories are on your PATH.

To get started, clone or fork+clone the repo, and then initialize and compile the Robolectric submodule with this
one-time setup:

    git clone git@github.com:pivotal/robolectric-gem.git
    cd robolectric-gem
    git submodule update --init
    (cd submodules/robolectric && ant clean findAndroidUnix && ant test) && ant clean test

We use IntelliJ IDEA.  In IDEA, `File -> Open Project` and choose the top directory of the repo to open the project.

We also use ant.  To compile, run tests, and build the jar file on the command-line, use `ant clean release-jar`.
After a successful ant build, you can find the built `robolectric-gem.jar` in the `ant_build` subdirectory.

To add a new matcher method to a pre-existing matcher class, simply enhance the class' test and the class itself.  To
add a new matcher class, you must also re-generate Expect.java.  This can be auto-generated in IDEA by clicking the
run button for the run configuration called "Add custom great-expectation Matchers by Regenerating Expect.java".

Please follow the existing conventions for customized failure messages.  If you customize your matcher's failure
message, be sure to do it whether the matcher is going to return true or false, because the calling test may have
inverted the matcher using `.not`, causing returning true to be considered a failure.  Watch out for null pointer
exceptions in your matchers: `actual` itself will never be null, but `actual.someMethod().equals(expected)` could
cause a null pointer exception when `someMethod()` returns null.  This is typically a situation to introduce a
customized failure message.

When pushing code or submitting pull requests from your fork, please follow the existing code style, naming conventions,
and project organization as already established for the project. Please always include unit tests for your new matchers
and, if possible, an example of using your matcher in the included sample app's tests.

### License

    Copyright 2011 Pivotal Labs

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
