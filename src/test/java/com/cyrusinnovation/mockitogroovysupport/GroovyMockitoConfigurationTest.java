package com.cyrusinnovation.mockitogroovysupport;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class GroovyMockitoConfigurationTest {
    @Test
    public void shouldUseGroovyAnswerByDefault() {
        assertThat(new GroovyMockitoConfiguration().getDefaultAnswer(), instanceOf(GroovyAnswer.class));
    }
}
