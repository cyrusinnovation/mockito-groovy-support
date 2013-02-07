package com.cyrusinnovation.mockitogroovysupport;

import org.mockito.cglib.proxy.*;
import org.mockito.exceptions.base.*;
import org.mockito.internal.*;
import org.mockito.internal.creation.*;
import org.mockito.internal.creation.jmock.*;
import org.mockito.invocation.*;
import org.mockito.mock.*;

/**
 * Variant of Mockito's built-in CglibMockMaker, except that it uses a Groovy-friendly
 * version of MethodInterceptorFilter.
 */
public class GroovyCglibMockMaker {

    public <T> T createMock(MockCreationSettings<T> settings, MockHandler handler) {
        InternalMockHandler mockitoHandler = cast(handler);
        return ClassImposterizer.INSTANCE.imposterise(
                new MethodInterceptorForGroovyFilter(mockitoHandler, settings), settings.getTypeToMock(), settings.getExtraInterfaces());
    }

    private InternalMockHandler cast(MockHandler handler) {
        if (!(handler instanceof InternalMockHandler)) {
            throw new MockitoException("At the moment you cannot provide own implementations of MockHandler." +
                    "\nPlease see the javadocs for the MockMaker interface.");
        }
        return (InternalMockHandler) handler;
    }

    public void resetMock(Object mock, MockHandler newHandler, MockCreationSettings settings) {
        ((Factory) mock).setCallback(0, new MethodInterceptorFilter(cast(newHandler), settings));
    }

    public MockHandler getHandler(Object mock) {
        if (!(mock instanceof Factory)) {
            return null;
        }
        Factory factory = (Factory) mock;
        Callback callback = factory.getCallback(0);
        if (!(callback instanceof MethodInterceptorFilter)) {
            return null;
        }
        return ((MethodInterceptorFilter) callback).getHandler();
    }
}
