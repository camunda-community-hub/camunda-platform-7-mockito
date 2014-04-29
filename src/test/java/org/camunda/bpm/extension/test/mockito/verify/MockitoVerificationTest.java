package org.camunda.bpm.extension.test.mockito.verify;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.extension.test.mockito.DelegateExpressions;
import org.camunda.bpm.extension.test.mockito.Expressions;
import org.camunda.bpm.extension.test.mockito.mock.FluentJavaDelegateMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

public class MockitoVerificationTest {

  private static final String JAVA_DELEGATE = "javaDelegate";

  private final FluentJavaDelegateMock javaDelegate = DelegateExpressions.registerJavaDelegateMock(JAVA_DELEGATE);

  @Mock
  private DelegateExecution delegateExecution;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void shouldVerifyExecuteCalled() throws Exception {
    javaDelegate.execute(delegateExecution);

    DelegateExpressions.verifyJavaDelegateMock(JAVA_DELEGATE).executed();
  }

  @Test
  public void shouldVerifyExecuteCalledTwice() throws Exception {
    javaDelegate.execute(delegateExecution);
    javaDelegate.execute(delegateExecution);

    DelegateExpressions.verifyJavaDelegateMock(JAVA_DELEGATE).executed(times(2));
  }

  @Test
  public void shouldVerifyExecuteNotCalled() throws Exception {
    DelegateExpressions.verifyJavaDelegateMock(JAVA_DELEGATE).executedNever();
  }
}
