package com.cyrusinnovation.mockitogroovysupport;

import com.cyrusinnovation.mockitogroovysupport.creation.MethodInterceptorForGroovyFilter;
import org.mockito.Mockito;
import org.mockito.cglib.proxy.Factory;
import org.mockito.internal.creation.MethodInterceptorFilter;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.stubbing.Answer;

public class MockitoGroovy {
    public static <T> T gmock(Class<T> classToMock) {
        return gmock(classToMock, (MockSettingsImpl) Mockito.withSettings());
    }

    public static <T> T gmock(Class<T> classToMock, Answer defaultAnswer) {
        return gmock(classToMock, (MockSettingsImpl) Mockito.withSettings().defaultAnswer(defaultAnswer));
    }

    public static <T> T gmock(Class<T> classToMock, MockSettingsImpl mockSettings) {
        T mock = Mockito.mock(classToMock, mockSettings);

        injectGroovyInterceptor((Factory) mock, mockSettings);

        return mock;
    }

    private static void injectGroovyInterceptor(Factory mock, MockSettingsImpl mockSettings) {
        MethodInterceptorFilter callback = (MethodInterceptorFilter) mock.getCallback(0);

        // Index of the method interceptor is always 0, see MockUtil.resetMock()
        mock.setCallback(0, new MethodInterceptorForGroovyFilter(callback.getHandler(), mockSettings));
    }


}
