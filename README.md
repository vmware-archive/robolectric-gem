## Robolectric Great-Expections Matchers

This project aims to create a library of [great-expections](https://github.com/xian/great-expectations) matcher
classes for [Robolectric](https://github.com/pivotal/robolectric) unit tests.

To use this library in your project, download robolectric-gem.jar and add it to your classpath for your unit tests.
This allows you to write unit test assertions for Android classes in the style of great-expectations, like this:

    @Test
    public void shouldHaveATitle() {
        HelloWorldActivity activity = new HelloWorldActivity();
        activity.onCreate(null);
        TextView titleView = (TextView) activity.findViewById(R.id.title);

        # toHaveText() is a method provided by this library
        expect(titleView).toHaveText("Hello World");
    }


## License

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
