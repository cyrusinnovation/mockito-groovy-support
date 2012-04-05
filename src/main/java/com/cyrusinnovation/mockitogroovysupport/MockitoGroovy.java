package com.cyrusinnovation.mockitogroovysupport;

import org.mockito.Mockito;

public class MockitoGroovy {
    public static <T> T gmock(Class<T> classToMock) {
        return Mockito.mock(classToMock, new GroovyAnswer(Mockito.RETURNS_DEFAULTS));
    }
}
