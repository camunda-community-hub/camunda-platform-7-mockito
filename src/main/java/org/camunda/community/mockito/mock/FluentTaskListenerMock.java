package org.camunda.community.mockito.mock;

import static org.mockito.Mockito.mock;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.community.mockito.answer.TaskListenerAnswer;
import org.mockito.Mockito;

import java.util.UUID;

public class FluentTaskListenerMock extends FluentMock<TaskListener, DelegateTask> implements TaskListener {

  public FluentTaskListenerMock() {
    super(mock(TaskListener.class), DelegateTask.class);
  }

  @Override
  public final void notify(final DelegateTask delegateTask) {
    mock.notify(delegateTask);
  }

  @Override
  public void onExecutionSetVariables(final VariableMap variables) {
    doAnswer(new TaskListener() {

      @Override
      public void notify(final DelegateTask delegateTask) {
        setVariables(delegateTask, variables);
      }
    });
  }

  @Override
  public void onExecutionSetVariables(VariableMap variableMap, VariableMap... values) {
    try {
      final String countVariable = UUID.randomUUID().toString();
      Mockito.doAnswer(
        new TaskListenerAnswer(task -> setVariablesForMultipleInvocations(combineVariableMaps(variableMap, values), countVariable, task))
      ).when(mock).notify(any());
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onExecutionThrowBpmnError(final BpmnError bpmnError) {
    doAnswer(new TaskListener() {

      @Override
      public void notify(final DelegateTask delegateTask) {
        throw bpmnError;
      }
    });
  }

  @Override
  public void onExecutionThrowException(final Exception exception) {
    doAnswer(new TaskListener() {
      @Override
      public void notify(DelegateTask delegateTask) {
        throw new RuntimeException(exception);
      }
    });
  }

  private void doAnswer(final TaskListener taskListener) {
    try {
      Mockito.doAnswer(new TaskListenerAnswer(taskListener)).when(mock).notify(any());
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }
}
