package com.cyrusinnovation.mockitogroovysupport;

import org.junit.Test;
import org.mockito.configuration.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class GroovyMockitoConfigurationTest {
    @Test
    public void shouldUseGroovyAnswerByDefault() {
        assertThat(new MockitoConfiguration().getDefaultAnswer(), instanceOf(GroovyAnswer.class));
    }
}
