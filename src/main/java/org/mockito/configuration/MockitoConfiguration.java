package org.mockito.configuration;

import com.cyrusinnovation.mockitogroovysupport.*;
import org.mockito.stubbing.*;

public class MockitoConfiguration extends DefaultMockitoConfiguration {
    @Override
    public Answer<Object> getDefaultAnswer() {
        return new GroovyAnswer(super.getDefaultAnswer());
    }
}
