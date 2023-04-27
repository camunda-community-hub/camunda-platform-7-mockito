package org.camunda.community.mockito.mock;

import static org.mockito.Mockito.mock;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.community.mockito.answer.JavaDelegateAnswer;
import org.mockito.Mockito;

import java.util.UUID;

public class FluentJavaDelegateMock extends FluentMock<JavaDelegate, DelegateExecution> implements JavaDelegate {

  public FluentJavaDelegateMock() {
    super(mock(JavaDelegate.class), DelegateExecution.class);
  }

  @Override
  public void execute(final DelegateExecution execution) throws Exception {
    mock.execute(execution);
  }

  @Override
  public void onExecutionSetVariables(final VariableMap variables) {
    doAnswer(new JavaDelegate() {

      @Override
      public void execute(final DelegateExecution execution) throws Exception {
        setVariables(execution, variables);
      }
    });
  }

  @Override
  public void onExecutionSetVariables(VariableMap variableMap, VariableMap... values) {
    try {
      final String countVariable = UUID.randomUUID().toString();
      Mockito.doAnswer(
        new JavaDelegateAnswer(execution -> setVariablesForMultipleInvocations(combineVariableMaps(variableMap, values),
          countVariable,
          execution))
      ).when(mock).execute(any());
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
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

  @Override
  public void onExecutionThrowException(final Exception exception) {
    doAnswer(new JavaDelegate() {
      @Override
      public void execute(DelegateExecution execution) throws Exception {
        throw exception;
      }
    });
  }

  private void doAnswer(final JavaDelegate javaDelegate) {
    try {
      Mockito.doAnswer(new JavaDelegateAnswer(javaDelegate)).when(mock).execute(any());
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }
}
