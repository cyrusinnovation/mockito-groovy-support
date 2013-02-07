package com.cyrusinnovation.mockitogroovysupport;

import org.mockito.internal.util.ObjectMethodsGuru;

import java.lang.reflect.Method;

public class ObjectMethodsGroovyGuru extends ObjectMethodsGuru {

    public boolean isGetMetaClass(Method method) {
        boolean nameMatches = method.getName().equals("getMetaClass");
        boolean parameterCountMatches = method.getParameterTypes().length == 0;
        return nameMatches && parameterCountMatches;
    }
}
