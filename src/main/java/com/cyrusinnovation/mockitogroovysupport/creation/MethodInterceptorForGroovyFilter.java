package com.cyrusinnovation.mockitogroovysupport.creation;

import com.cyrusinnovation.mockitogroovysupport.util.ObjectMethodsGroovyGuru;
import groovy.lang.GroovySystem;
import org.mockito.cglib.proxy.MethodProxy;
import org.mockito.internal.MockitoInvocationHandler;
import org.mockito.internal.creation.MethodInterceptorFilter;
import org.mockito.internal.creation.MockSettingsImpl;

import java.lang.reflect.Method;

public class MethodInterceptorForGroovyFilter extends MethodInterceptorFilter {

    private ObjectMethodsGroovyGuru objectMethodsGuru = new ObjectMethodsGroovyGuru();

    public MethodInterceptorForGroovyFilter(MockitoInvocationHandler handler, MockSettingsImpl mockSettings) {
        super(handler, mockSettings);
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (objectMethodsGuru.isGetMetaClass(method)) {
            return GroovySystem.getMetaClassRegistry().getMetaClass(method.getDeclaringClass());
        }

        return super.intercept(proxy, method, args, methodProxy);
    }
}
