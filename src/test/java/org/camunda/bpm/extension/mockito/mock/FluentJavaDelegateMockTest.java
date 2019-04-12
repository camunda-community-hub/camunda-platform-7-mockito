package org.camunda.bpm.extension.mockito.mock;

import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.extension.mockito.DelegateExpressions;
import org.camunda.bpm.extension.mockito.delegate.DelegateExecutionFake;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.mock;

public class FluentJavaDelegateMockTest {

  private static final String BEAN_NAME = "foo";
  private static final String MESSAGE = "message";

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Test
  public void throws_bpmnError() throws Exception {

    // expect exception
    thrown.expect(BpmnError.class);
    thrown.expectMessage(MESSAGE);

    DelegateExpressions.registerJavaDelegateMock(BEAN_NAME).onExecutionThrowBpmnError("code", MESSAGE);

    final JavaDelegate registeredDelegate = DelegateExpressions.getJavaDelegateMock(BEAN_NAME);

    // test succeeds when exception is thrown
    registeredDelegate.execute(mock(DelegateExecution.class));

  }

  @Test
  public void throws_exception() throws Exception {
    thrown.expect(NullPointerException.class);

    DelegateExpressions.registerJavaDelegateMock(BEAN_NAME).onExecutionThrowException(new NullPointerException());

    DelegateExpressions.getJavaDelegateMock(BEAN_NAME).execute(mock(DelegateExecution.class));
  }

  @Test
  public void set_single_variable() throws Exception {
    DelegateExpressions.registerJavaDelegateMock(BEAN_NAME).onExecutionSetVariable("foo", "bar");

    final JavaDelegate registeredDelegate = DelegateExpressions.getJavaDelegateMock(BEAN_NAME);

    final DelegateExecutionFake fake = DelegateExecutionFake.of();
    registeredDelegate.execute(fake);

    Assertions.assertThat(fake.hasVariable("foo")).isTrue();
    Assertions.assertThat((String)fake.getVariable("foo")).isEqualTo("bar");
  }
}
