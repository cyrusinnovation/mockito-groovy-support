Why?
====

You're using Mockito and Groovy. Maybe you've noticed that, when you try to mock a Groovy
class from a Groovy test, it fails horribly. Now it won't!

Specifically, this library provides a workaround for Mockito Issue 303:
http://code.google.com/p/mockito/issues/detail?id=303

How do you use it?
==================

1) Include mockito-groovy-support on your classpath.
2) Use Mockito from Groovy as you normally would.
3) Everything should just work. If it doesn't, let us know.

Where can I get it?
===================

Download the jar from the "releases" tab here in github,
or use the Maven central repository.  Enjoy!

Who made this?
==============

Moss Collum and Laura Dean of [Cyrus Innovation](http://www.cyrusinnovation.com/) wrote the original
version. [Jostein Gogstad](https://github.com/gogstad) contributed the code to support verification
and argument matchers.

Email us at: mockito-groovy-developers@cyruslists.com

Changelog
=========

* 1.3: Fix a bug in working with groovy subclasses; thanks to Shawn Garner and [Tim Andersen](http://timandersen.net/) for this fix.
* 1.2: Work seamlessly with Mockito using MockMaker extension point.
* 1.1: Support verification and argument matchers.
* 1.0.1: Made project available through Maven.
* 1.0: Support basic stubbing of Groovy classes.
