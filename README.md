## spray-test-directive

Simple SBT project hopefully attempting to solve a problem with how Spray routes are utilized in my project.

## Issue

I'm very new to Spray, so I'm guessing this is just me not correctly understanding how the framework works. However, I'm encountering what seems to be weird behavior when trying to handle. This issue references [this documentation on directives](http://spray.io/documentation/1.2.3/spray-routing/security-directives/authorize/) specifically, but it might apply to other custom directives as well.

When I attempt to utilize the `authorize` directive that Spray ships with, the HTTP response appears correctly according to the returned value. That is, I get a 403 Forbidden when the returned value of `authorize` is `false`, and I get 200 OK when it is instead `true`. However, it seems as though the function passed to it still executes even though it seems like it shouldn't.

Please see my attached SBT project. It demonstrates my issue exactly, so, hopefully, it's helpful in addressing my concern. Run with:

```sh
sbt test
```

The output you should see by default should be very similar to the following:

```
[info] Test:
[info] route
[info] - should succeed and have side effects
[info] - should fail and not have side effects *** FAILED ***
[info]   1 did not equal 0 (Test.scala:28)
[info] Run completed in 779 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 1, canceled 0, ignored 0, pending 0
[info] *** 1 TEST FAILED ***
```

Again, my concern is this: why is the body of the function passed into the `Directive0` of `authorized` executed even when the request is getting rejected?