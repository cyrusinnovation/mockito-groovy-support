package com.cyrusinnovation.mockitogroovysupport;

import org.mockito.configuration.DefaultMockitoConfiguration;
import org.mockito.stubbing.Answer;

public class GroovyMockitoConfiguration extends DefaultMockitoConfiguration {
    @Override
    public Answer<Object> getDefaultAnswer() {
        return new GroovyAnswer(super.getDefaultAnswer());
    }
}
