package com.cyrusinnovation.mockitogroovysupport

import org.junit.Test

import static com.cyrusinnovation.mockitogroovysupport.MockitoGroovy.gmock
import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.when

class MockingAGroovyClassFromGroovyTest {
    static class SomeGroovyClass {
        String greeting() {
            return "Hello, world"
        }
    }

    @Test
    void shouldBeAbleToStubAMethodOnAGroovyClass() {
        def mock = gmock(SomeGroovyClass)

        when(mock.greeting()).thenReturn("My Fancy Greeting")
        assertEquals("My Fancy Greeting", mock.greeting())
    }
}
