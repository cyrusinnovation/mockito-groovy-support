package com.cyrusinnovation.mockitogroovysupport;

import org.codehaus.groovy.runtime.InvokerHelper;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * User: cyrus
 * Date: 4/5/12
 * Time: 2:18 PM
 */
public class GroovyAnswer implements Answer<Object> {
    private Answer<Object> delegate;

    GroovyAnswer(Answer<Object> delegate) {
        this.delegate = delegate;
    }

    public Object answer(InvocationOnMock invocation) throws Throwable {
        if (invocation.getMethod().getName().equals("getMetaClass")) {
            return InvokerHelper.getMetaClass(invocation.getMock().getClass());
        }
        return delegate.answer(invocation);
    }
}
