package com.cyrusinnovation.mockitogroovysupport

import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import static com.cyrusinnovation.mockitogroovysupport.MockitoGroovy.gmock
import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class MockingAGroovyClassFromGroovyTest {
    static class SomeGroovyClass {
        String greeting() {
            return "Hello, world"
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
    @Ignore // WIP
    void shouldBeAbleToVerifyAMethodOnAGroovyClass() {
        mock.greeting()
        verify(mock).greeting()
    }
}
