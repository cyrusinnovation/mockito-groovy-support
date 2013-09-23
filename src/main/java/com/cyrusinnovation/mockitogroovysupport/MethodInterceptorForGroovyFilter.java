package com.cyrusinnovation.mockitogroovysupport;

import groovy.lang.*;
import org.mockito.cglib.proxy.*;
import org.mockito.internal.*;
import org.mockito.internal.creation.*;
import org.mockito.mock.*;

import java.lang.reflect.*;

public class MethodInterceptorForGroovyFilter extends MethodInterceptorFilter {

    private ObjectMethodsGroovyGuru objectMethodsGuru = new ObjectMethodsGroovyGuru();
    private Class typeToMock;

    public MethodInterceptorForGroovyFilter(InternalMockHandler handler, MockCreationSettings mockSettings) {
        super(handler, mockSettings);
        typeToMock = mockSettings.getTypeToMock();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (objectMethodsGuru.isGetMetaClass(method)) {
            return GroovySystem.getMetaClassRegistry().getMetaClass(typeToMock);
        }

        return super.intercept(proxy, method, args, methodProxy);
    }
}
