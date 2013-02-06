Why?
====

You're using Mockito and Groovy. Maybe you've noticed that, when you try to mock a Groovy
class from a Groovy test, it fails horribly. Now it won't!

Specifically, this library provides a workaround for Mockito Issue 303:
http://code.google.com/p/mockito/issues/detail?id=303

How do you use it?
==================

There are two ways.

The preferred way.
------------------

Where you would have said:

```java
MyClass mockObject = Mockito.mock(MyClass.class)
```

now you say:

```java
MyClass mockObject = MockitoGroovy.gmock(MyClass.class)
```

The more seamless way that doesn't work for everything.
-------------------------------------------------------

Override your Mockito configuration, by creating a new class called
`org.mockito.configuration.MockitoConfiguration`. It has to have that exact name, including the package,
and it should look like this:

```java
package org.mockito.configuration;

import com.cyrusinnovation.mockitogroovysupport.GroovyMockitoConfiguration;

public class MockitoConfiguration extends GroovyMockitoConfiguration {
}
```

This will make Mockito transparently handle Groovy for basic stubbing. However, it doesn't (yet)
work for verifying method calls, or for stubs that use matchers rather than expected argument
values. We're working on making it work more completely.

Where can I get it?
===================

[Download the jar](http://m14m.net/mockito-groovy-support-1.1.jar). The new version will be up on Maven when I figure out how.

Who made this?
==============

Moss Collum and Laura Dean of [Cyrus Innovation](http://www.cyrusinnovation.com/).

Email us at: mockito-groovy-developers@cyruslists.com
