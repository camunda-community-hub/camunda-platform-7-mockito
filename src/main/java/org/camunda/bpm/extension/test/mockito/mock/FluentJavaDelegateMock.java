package org.camunda.bpm.extension.test.mockito.mock;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.extension.test.mockito.answer.JavaDelegateAnswer;
import org.mockito.Mockito;

import java.util.Map;

import static com.google.common.base.Throwables.propagate;
import static org.mockito.Mockito.mock;

public final class FluentJavaDelegateMock extends FluentMock<JavaDelegate, DelegateExecution> implements JavaDelegate {

  public FluentJavaDelegateMock() {
    super(mock(JavaDelegate.class), DelegateExecution.class);
  }

  @Override
  public void execute(final DelegateExecution execution) throws Exception {
    mock.execute(execution);
  }

  @Override
  public void onExecutionSetVariables(final Map<String, Object> variables) {
    doAnswer(new JavaDelegate() {

      @Override
      public void execute(final DelegateExecution execution) throws Exception {
        setVariables(execution, variables);
      }
    });
  }

  @Override
  public void onExecutionThrowBpmnError(final BpmnError bpmnError) {
    doAnswer(new JavaDelegate() {

      @Override
      public void execute(final DelegateExecution execution) throws Exception {
        throw bpmnError;
      }
    });
  }

  private void doAnswer(final JavaDelegate javaDelegate) {
    try {
      Mockito.doAnswer(new JavaDelegateAnswer(javaDelegate)).when(mock).execute(any());
    } catch (final Exception e) {
      propagate(e);
    }
  }
}
