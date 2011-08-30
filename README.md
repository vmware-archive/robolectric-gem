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
[sample Android app](https://github.com/pivotal/robolectric-gem/tree/master/src/sampleapp-src/com/pivotallabs/robolectricgem/sampleapp)
and
[its tests](https://github.com/pivotal/robolectric-gem/tree/master/test/java/com/pivotallabs/robolectricgem/sampleapp).

For all of the included matchers, see [the matcher classes](https://github.com/pivotal/robolectric-gem/tree/master/src/robolectricgem/com/pivotallabs/robolectricgem/matchers).

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
add a new matcher class, you must also re-generate
[Expect.java](https://github.com/pivotal/robolectric-gem/tree/master/src/robolectricgem/com/pivotallabs/robolectricgem/expect/Expect.java).
Add your new matcher class to the list of matchers
at the top of [RunnableExpectGenerator](https://github.com/pivotal/robolectric-gem/tree/master/src/robolectricgem/com/pivotallabs/robolectricgem/expect/RunnableExpectGenerator.java),
then auto-generate Expect.java in IDEA by clicking the
run button for the run configuration called "Add custom great-expectation Matchers by Regenerating Expect.java".

Please follow the existing conventions for customized failure messages.  If you customize your matcher's failure
message, be sure to do it whether the matcher is going to return true or false, because the calling test may have
inverted the matcher using `.not`, causing returning true to be considered a failure.  Watch out for null pointer
exceptions in your matchers: `actual` itself will never be null, but `actual.someMethod().equals(expected)` could
cause a null pointer exception when `someMethod()` returns null.  This is typically a situation to introduce a
customized failure message.

When pushing code or submitting pull requests from your fork, please follow the existing code style, naming conventions,
and project organization as already established for the project. Please always include unit tests for your new matchers.
Please also don't forget to add an example of using your new matcher to the included sample app's tests. These act as
an integeration test and also ensure that you did not forget to regenerate Expect.java.

### License

    Copyright (c) 2011 Pivotal Labs, http://www.pivotallabs.com

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
