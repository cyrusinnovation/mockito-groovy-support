package com.cyrusinnovation.mockitogroovysupport;

import groovy.lang.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.internal.*;
import org.mockito.invocation.*;
import org.mockito.runners.*;
import org.mockito.stubbing.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GroovyAnswerTest {
    @Mock
    private Object returnValueFromDelegate;
    @Mock
    private Answer<Object> delegate;
    private Answer groovyAnswer;

    @Before
    public void setUp() throws Throwable {
        groovyAnswer = new GroovyAnswer(delegate);
        when(delegate.answer(any(InvocationOnMock.class))).thenReturn(returnValueFromDelegate);
    }

    interface SomeGroovyClass extends GroovyObject {
    }

    @Test
    public void shouldReturnMetaclassForGroovyObjects() throws Throwable {
        //given
        GroovyObject g = mock(SomeGroovyClass.class);
        g.getMetaClass();
        Invocation getMetaClass = lastInvocation();

        //when
        Object result = groovyAnswer.answer(getMetaClass);

        //then
        assertThat(result, instanceOf(MetaClass.class));
        assertEquals("Should be MetaClass of the right class", g.getClass(), ((MetaClass) result).getTheClass());
    }

    interface NonGroovyClass {
        String someMethod();
    }

    @Test
    public void shouldDelegateAnythingOtherThanAGetMetaClassCall() throws Throwable {
        NonGroovyClass m = mock(NonGroovyClass.class);
        m.someMethod();

        assertThat(groovyAnswer.answer(lastInvocation()), is(returnValueFromDelegate));
    }

    interface HasGetMetaClass extends GroovyObject {
        MetaClass getMetaClass(String redHerring);
    }

    interface HasGetMetaClassButIsNotAGroovyObject {
        MetaClass getMetaClass();
    }

    @Test
    public void shouldNotAnswerGetMetaClassIfItDoesNotHaveTheExpectedSignature() throws Throwable {
        HasGetMetaClass m = mock(HasGetMetaClass.class);
        m.getMetaClass("but not the regular Groovy getMetaClass");

        assertThat(groovyAnswer.answer(lastInvocation()), is(returnValueFromDelegate));
    }

    @Test
    public void shouldNotAnswerGetMetaClassIfTheObjectIsNotAGroovyObject() throws Throwable {
        HasGetMetaClassButIsNotAGroovyObject m = mock(HasGetMetaClassButIsNotAGroovyObject.class);
        m.getMetaClass();

        assertThat(groovyAnswer.answer(lastInvocation()), is(returnValueFromDelegate));
    }

    private Invocation lastInvocation() {
        return new MockitoCore().getLastInvocation();
    }
}
