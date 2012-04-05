Why?
====

You're using Mockito and Groovy. Maybe you've noticed that, when you try to mock a Groovy
class from a Groovy test, it fails horribly. Now it won't!

Specifically, this library provides a workaround for Mockito Issue 303:
http://code.google.com/p/mockito/issues/detail?id=303

How do you use it?
==================

There are two ways.

The less helpful way that requires less setup.
----------------------------------------------

Where you would have said:

```java
MyClass mockObject = Mockito.mock(MyClass.class)
```

now you say:

```java
MyClass mockObject = MockitoGroovy.gmock(MyClass.class)
```

The more helpful way that takes more setup.
-------------------------------------------

Override your Mockito configuration, by creating a new class called
`org.mockito.configuration.MockitoConfiguration`. It has to have that exact name, including the package,
and it should look like this:

```java
```

From then on, everything should just work. If it doesn't, let us know.

Who made this?
==============

Moss Collum and Laura Dean of [Cyrus Innovation](http://www.cyrusinnovation.com/).

Email us at: mockito-groovy-developers@cyruslists.com
