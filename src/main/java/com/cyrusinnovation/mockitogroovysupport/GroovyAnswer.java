package com.cyrusinnovation.mockitogroovysupport;

import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;

public class GroovyAnswer implements Answer<Object> {
    private Answer<Object> delegate;

    public GroovyAnswer(Answer<Object> delegate) {
        this.delegate = delegate;
    }

    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (isForGroovyGetMetaClass(invocation.getMethod())) {
            return InvokerHelper.getMetaClass(invocation.getMock().getClass());
        }
        return delegate.answer(invocation);
    }

    private boolean isForGroovyGetMetaClass(Method method) {
        return GroovyObject.class.isAssignableFrom(method.getDeclaringClass())
                && method.getName().equals("getMetaClass")
                && method.getParameterTypes().length == 0;
    }
}
