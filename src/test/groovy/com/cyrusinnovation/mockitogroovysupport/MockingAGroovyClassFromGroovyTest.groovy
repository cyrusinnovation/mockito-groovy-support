package com.cyrusinnovation.mockitogroovysupport
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

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

    @Before
    void createMock() {
        mock = mock(SomeGroovyClass)
    }

    @Test
    void shouldSupportExtraInterfaces() {
        def mockWithExtraInterface = mock(SomeGroovyClass, withSettings().extraInterfaces(SomeOtherGroovyInterface))
        assert mockWithExtraInterface instanceof SomeOtherGroovyInterface
    }

    @Test
    void shouldBeAbleToStubAMethodOnAGroovyClass() {
        when(mock.greeting()).thenReturn("My Fancy Greeting")
        assertEquals("My Fancy Greeting", mock.greeting())
    }

    @Test
    @Ignore // WIP for integrate-using-mockmaker-extension-point branch
    void shouldBeAbleToVerifyAMethodOnAGroovyClass() {
        mock.greeting()
        verify(mock).greeting()
        verify(mock, never()).methodThatShouldntBeCalled()
    }

    @Test
    void shouldBeAbleToStubAMethodWithDifferentArguments() {
        when(mock.methodTakingArgument("foo")).thenReturn("FOO")
        when(mock.methodTakingArgument("bar")).thenReturn("BAR")

        assertEquals("FOO", mock.methodTakingArgument("foo"))
        assertEquals("BAR", mock.methodTakingArgument("bar"))
    }

    @Test
    @Ignore // WIP for integrate-using-mockmaker-extension-point branch
    void shouldBeAbleToStubAMethodUsingAnArgumentMatcher() {
        when(mock.methodTakingArgument(anyString())).thenReturn("ANY STRING")
        assertEquals("ANY STRING", mock.methodTakingArgument("whatever"))
    }
}
