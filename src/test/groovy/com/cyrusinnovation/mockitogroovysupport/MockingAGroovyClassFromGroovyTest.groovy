package com.cyrusinnovation.mockitogroovysupport

import org.junit.Test
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues

import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class MockingAGroovyClassFromGroovyTest {
    static class SomeGroovyClass {
        String greeting() {
            return "Hello, world"
        }
    }

    @Test
    void shouldBeAbleToStubAMethodOnAGroovyClass() {
        def mock = mock(SomeGroovyClass, new GroovyAnswer(new ReturnsEmptyValues()))

        when(mock.greeting()).thenReturn("My Fancy Greeting")
        assertEquals("My Fancy Greeting", mock.greeting())
    }
}
