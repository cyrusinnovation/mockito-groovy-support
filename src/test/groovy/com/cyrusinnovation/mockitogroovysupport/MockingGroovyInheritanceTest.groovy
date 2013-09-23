package com.cyrusinnovation.mockitogroovysupport

import com.cyrusinnovation.mockitogroovysupport.support.SubClass
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MockingGroovyInheritanceTest {

    private SubClass mock

    @Before void createMock() {
        mock = Mockito.mock(SubClass)
    }

    @Test
    void testMethodDefinedOnSubClass() {
        mock.subMethod()

        Mockito.verify(mock).subMethod()
    }

    @Test
    void testMethodDefinedOnSuperClass() {
        mock.superMethod()

        Mockito.verify(mock).superMethod()
    }

}
