package com.cyrusinnovation.mockitogroovysupport

import org.junit.*

import static org.junit.Assert.assertEquals
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.*

class MockingAGroovyClassFromGroovyTest {
    static class SomeGroovyClass {
        String greeting() {
            return "Hello, world"
        }

        String methodTakingArgument(String argument) {
            return "You said: $argument"
        }

        String methodThatShouldntBeCalled() {
            return "Oh no!"
        }
    }

    static interface SomeOtherGroovyInterface {
        void foo()
    }

    private SomeGroovyClass mock

    @Before void createMock() {
        mock = mock(SomeGroovyClass)
    }

    @Test void "should stub methods on Groovy classes"() {
        when(mock.greeting()).thenReturn("My Fancy Greeting")
        assertEquals("My Fancy Greeting", mock.greeting())
    }

    @Test void "should verify methods on Groovy classes"() {
        mock.greeting()
        verify(mock).greeting()
        verify(mock, never()).methodThatShouldntBeCalled()
    }

    @Test void "should support extra interfaces"() {
        def mockWithExtraInterface = mock(SomeGroovyClass, withSettings().extraInterfaces(SomeOtherGroovyInterface))
        assert mockWithExtraInterface instanceof SomeOtherGroovyInterface
    }

    @Test void "should distinguish stubs with different arguments"() {
        when(mock.methodTakingArgument("foo")).thenReturn("FOO")
        when(mock.methodTakingArgument("bar")).thenReturn("BAR")

        assertEquals("FOO", mock.methodTakingArgument("foo"))
        assertEquals("BAR", mock.methodTakingArgument("bar"))
    }

    @Test void "should handle stubs that use argument matchers"() {
        when(mock.methodTakingArgument(anyString())).thenReturn("ANY STRING")
        assertEquals("ANY STRING", mock.methodTakingArgument("whatever"))
    }

    @Test void "should reset mocks correctly"() {
        mock.methodThatShouldntBeCalled()

        reset(mock)
        mock.greeting()

        verify(mock).greeting()
        verify(mock, never()).methodThatShouldntBeCalled()
    }
}
