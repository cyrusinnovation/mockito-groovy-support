package com.cyrusinnovation.mockitogroovysupport

import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import static com.cyrusinnovation.mockitogroovysupport.MockitoGroovy.gmock
import static org.junit.Assert.assertEquals
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class MockingAGroovyClassFromGroovyTest {
    static class SomeGroovyClass {
        String greeting() {
            return "Hello, world"
        }

        String methodTakingArgument(String argument) {
            return "You said: $argument"
        }
    }

    private SomeGroovyClass mock

    @Before
    void createMock() {
        mock = gmock(SomeGroovyClass)
    }

    @Test
    void shouldBeAbleToStubAMethodOnAGroovyClass() {
        when(mock.greeting()).thenReturn("My Fancy Greeting")
        assertEquals("My Fancy Greeting", mock.greeting())
    }

    @Test
    @Ignore // See Issue #2
    void shouldBeAbleToVerifyAMethodOnAGroovyClass() {
        mock.greeting()
        verify(mock).greeting()
    }

    @Test
    void shouldBeAbleToStubAMethodWithDifferentArguments() {
        when(mock.methodTakingArgument("foo")).thenReturn("FOO")
        when(mock.methodTakingArgument("bar")).thenReturn("BAR")

        assertEquals("FOO", mock.methodTakingArgument("foo"))
        assertEquals("BAR", mock.methodTakingArgument("bar"))
    }

    @Test
    @Ignore // See Issue #1
    void shouldBeAbleToStubAMethodUsingAnArgumentMatcher() {
        when(mock.methodTakingArgument(anyString())).thenReturn("ANY STRING")
        assertEquals("ANY STRING", mock.methodTakingArgument("whatever"))
    }
}
