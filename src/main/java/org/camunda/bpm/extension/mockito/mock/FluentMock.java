package org.camunda.bpm.extension.mockito.mock;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Common super class for all fluent mocks.
 *
 * @param <T>
 *          type of mock (JavaDelegate, ExecutionListener, TaskListener)
 * @param <P>
 *          type of method argument (DelegateExecution, DelegateTask)
 */
abstract class FluentMock<T, P extends VariableScope> {

  protected final T mock;
  protected final Class<P> parameterType;

  /**
   * Creates a new instance for given mock.
   *
   * @param mock
   *          the mocked delegate or listener
   * @param parameterType
   *          the parameter type (DelegateExecution, DelegateTask)
   */
  protected FluentMock(final T mock, final Class<P> parameterType) {
    this.mock = mock;
    this.parameterType = parameterType;
  }

  protected void setVariables(final VariableScope variableScope, final Map<String, Object> variables) {
    if (variables == null || variables.isEmpty()) {
      return;
    }
    for (final Entry<String, Object> variable : variables.entrySet()) {
      variableScope.setVariable(variable.getKey(), variable.getValue());
    }
  }

  /**
   * @param variableMap the process variables this delegate sets when executed
   */
  public abstract void onExecutionSetVariables(final VariableMap variableMap);

  /**
   * @param key of the process variables this delegate sets when executed
   * @param value of the process variables this delegate sets when executed
   */
  public void onExecutionSetVariable(final String key, final Object value) {
    onExecutionSetVariables(Variables.putValue(key, value));
  }

  /**
   * @param variables the process variables this delegate sets when executed
   */
  public void onExecutionSetVariables(final Map<String, Object> variables) {
    onExecutionSetVariables(Variables.fromMap(variables));
  }

  /**
   * The mock will throw a BpmnError with given errorCode.
   *
   * @param errorCode
   *          the error code
   */
  public void onExecutionThrowBpmnError(final String errorCode) {
    onExecutionThrowBpmnError(new BpmnError(errorCode));
  }

  /**
   * The mock will throw a BpmnError with given errorCode and message.
   *
   * @param errorCode
   *          the error code
   * @param message
   *          the error message
   */
  public void onExecutionThrowBpmnError(final String errorCode, final String message) {
    onExecutionThrowBpmnError(new BpmnError(errorCode, message));
  }

  /**
   * The implementation of throw-bpmn-error depends on the concrete type.
   *
   * @param bpmnError
   *          the error instance
   */
  public abstract void onExecutionThrowBpmnError(final BpmnError bpmnError);


  /**
   * The mock will throw the given exception on execution.
   *
   * @param exception the exception instance
   */
  public abstract void onExecutionThrowException(final Exception exception);

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
