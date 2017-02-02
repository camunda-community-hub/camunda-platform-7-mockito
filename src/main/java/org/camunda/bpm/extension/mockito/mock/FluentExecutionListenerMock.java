package org.camunda.bpm.extension.mockito.mock;

import static org.mockito.Mockito.mock;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.extension.mockito.answer.ExecutionListenerAnswer;
import org.mockito.Mockito;

public class FluentExecutionListenerMock extends FluentMock<ExecutionListener, DelegateExecution> implements ExecutionListener {

  public FluentExecutionListenerMock() {
    super(mock(ExecutionListener.class), DelegateExecution.class);
  }

  @Override
  public void onExecutionSetVariables(final VariableMap variables) {
    doAnswer(new ExecutionListener() {

      @Override
      public void notify(final DelegateExecution execution) throws Exception {
        setVariables(execution, variables);
      }
    });
  }

  @Override
  public void onExecutionThrowBpmnError(final BpmnError bpmnError) {
    doAnswer(new ExecutionListener() {

      @Override
      public void notify(final DelegateExecution execution) throws Exception {
        throw bpmnError;
      }
    });
  }

  @Override
  public void onExecutionThrowException(final Exception exception) {
    doAnswer(new ExecutionListener() {
      @Override
      public void notify(DelegateExecution execution) throws Exception {
        throw exception;
      }
    });
  }

  @Override
  public void notify(final DelegateExecution execution) throws Exception {
    mock.notify(execution);
  }

  private void doAnswer(final ExecutionListener executionListener) {
    try {
      Mockito.doAnswer(new ExecutionListenerAnswer(executionListener)).when(mock).notify(any());
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

}
