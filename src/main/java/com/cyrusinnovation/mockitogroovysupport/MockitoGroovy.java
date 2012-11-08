package com.cyrusinnovation.mockitogroovysupport;

import com.cyrusinnovation.mockitogroovysupport.creation.MethodInterceptorForGroovyFilter;
import org.mockito.Mockito;
import org.mockito.cglib.proxy.Factory;
import org.mockito.internal.creation.MethodInterceptorFilter;
import org.mockito.internal.creation.MockSettingsImpl;

public class MockitoGroovy {
    public static <T> T gmock(Class<T> classToMock) {
        MockSettingsImpl mockSettings = (MockSettingsImpl) Mockito.withSettings().defaultAnswer(new GroovyAnswer(Mockito.RETURNS_DEFAULTS));
        T mock = Mockito.mock(classToMock, mockSettings);

        injectGroovyInterceptor((Factory)mock, mockSettings);

        return mock;
    }

    private static void injectGroovyInterceptor(Factory mock, MockSettingsImpl mockSettings) {
        MethodInterceptorFilter callback = (MethodInterceptorFilter) mock.getCallback(0);

        // Index of the method interceptor is always 0, see MockUtil.resetMock()
        mock.setCallback(0, new MethodInterceptorForGroovyFilter(callback.getHandler(), mockSettings));
    }


}
