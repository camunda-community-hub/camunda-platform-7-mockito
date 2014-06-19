package org.camunda.bpm.extension.mockito.mock;

import java.util.Map;
import java.util.Map.Entry;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.extension.util.ProcessVariableMaps;
import org.mockito.Mockito;

/**
 * Common super class for all fluent mocks.
 *
 * @param <T> type of mock (JavaDelegate, ExecutionListener, TaskListener)
 * @param <P> type of method argument (DelegateExecution, DelegateTask)
 */
abstract class FluentMock<T, P extends VariableScope> {

  protected final T mock;
  protected final Class<P> parameterType;

  /**
   * Creates a new instance for given mock.
   *
   * @param mock the mocked delegate or listener
   * @param parameterType the parameter type  (DelegateExecution, DelegateTask)
   */
  protected FluentMock(final T mock, final Class<P> parameterType) {
    this.mock = mock;
    this.parameterType = parameterType;
  }

  protected void setVariables(final VariableScope variableScope, final Map<String, Object> variables) {
    for (final Entry<String, Object> variable : variables.entrySet()) {
      variableScope.setVariable(variable.getKey(), variable.getValue());
    }
  }

  public final void onExecutionSetVariables(final Object... keyValuePairs) {
    onExecutionSetVariables(ProcessVariableMaps.parseMap(keyValuePairs));
  }

  public abstract void onExecutionSetVariables(Map<String, Object> variables);

  /**
   * The mock will throw a BpmnError with given errorCode.
   *
   * @param errorCode the error code
   */
  public void onExecutionThrowBpmnError(final String errorCode) {
    onExecutionThrowBpmnError(new BpmnError(errorCode));
  }

  /**
   * The mock will throw a BpmnError with given errorCode and message.
   *
   * @param errorCode the error code
   * @param message the error message
   */
  public void onExecutionThrowBpmnError(final String errorCode, final String message) {
    onExecutionThrowBpmnError(new BpmnError(errorCode, message));
  }

  /**
   * The implementation of throw-bpmn-error depends on the concrete type.
   * @param bpmnError the error instance
   */
  public abstract void onExecutionThrowBpmnError(final BpmnError bpmnError);

  /**
   * @return the internal mock
   */
  public T getMock() {
    return mock;
  }

  /**
   * @return any(P) for use in Mockito when/doAnswer
   */
  protected P any() {
    return Mockito.any(parameterType);
  }
}
