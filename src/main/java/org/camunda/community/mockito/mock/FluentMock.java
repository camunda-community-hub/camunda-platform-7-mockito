package org.camunda.community.mockito.mock;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.mockito.Mockito;

import java.util.ArrayDeque;
import java.util.Arrays;
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
public abstract class FluentMock<T, P extends VariableScope> {

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

  protected static VariableMap[] combineVariableMaps(VariableMap variableMap, VariableMap... values) {
    ArrayDeque<VariableMap> combinedVariableMaps = new ArrayDeque<>(Arrays.asList(values));
    combinedVariableMaps.addFirst(variableMap);
    return combinedVariableMaps.toArray(VariableMap[]::new);
  }


  protected void setVariablesForMultipleInvocations(
    VariableMap[] variables,
    String invocationCountVariableName,
    VariableScope variableScope
  ) {
    Object invocationCount = calculateInvocationCount(invocationCountVariableName, variableScope);
    if (invocationCount instanceof Integer) {
      int count = (Integer) invocationCount;
      variableScope.setVariable(invocationCountVariableName, count + 1);
      setVariablesForGivenInvocationCount(variables, variableScope, count);
    }
  }

  private void setVariablesForGivenInvocationCount(VariableMap[] variables, VariableScope variableScope, int count) {
    if (count - 1 < variables.length) {
      setVariables(variableScope, variables[count - 1]);
    } else {
      setVariables(variableScope, variables[variables.length - 1]);
    }
  }

  private Object calculateInvocationCount(String countVariable, VariableScope variableScope) {
    Object executionCount = variableScope.getVariable(countVariable);
    if (executionCount == null) {
      variableScope.setVariable(countVariable, 1);
      executionCount = 1;
    }
    return executionCount;
  }

  /**
   * @param variableMap the process variables this delegate sets when executed
   */
  public abstract void onExecutionSetVariables(final VariableMap variableMap);

  /**
   * Sets consecutive return values to be returned when the method is called. E.g:
   * <pre class="code"><code class="java">
   * mock.onExecutionSetVariables(variableMap1, variableMap2, variableMap3);
   * </code></pre>
   *
   * Last return value in the sequence (in example: variableMap3) determines the behavior of further consecutive calls.
   * <p>
   *
   * @param variableMap first variables that will be returned
   * @param values next variables that will be return
   *
   */
  public abstract void onExecutionSetVariables(final VariableMap variableMap, final VariableMap... values);

  /**
   * @param key of the process variables this delegate sets when executed
   * @param value of the process variables this delegate sets when executed
   */
  public void onExecutionSetVariable(final String key, final Object value) {
    onExecutionSetVariables(Variables.putValue(key, value));
  }

  /**
   *
   * @see #onExecutionSetVariable(String, Object)
   * @param variable the VariableFactory declartion (name and type of variable)
   * @param value the value to set. Type safe.
   * @param <V> the payload type of the variable
   */
  public <V> void onExecutionSetVariable(final VariableFactory<V> variable, final V value) {
    onExecutionSetVariable(variable.getName(), value);
  }

  /**
   * Sets consecutive return values to be returned when the method is called. E.g:
   * <pre class="code"><code class="java">
   * mock.onExecutionSetVariables(Map.of("foo", "bar"), Map.of("bar", "foo"));
   * </code></pre>
   *
   * Last return value in the sequence (in example: Map.of("bar", "foo")) determines the behavior of further consecutive calls.
   * <p>
   *
   * @param variables first variables that will be returned
   * @param values next variables that will be return
   *
   */
  @SafeVarargs
  public final void onExecutionSetVariables(final Map<String, Object> variables, final Map<String, Object>... values) {
    onExecutionSetVariables(Variables.fromMap(variables), Arrays.stream(values).map(Variables::fromMap).toArray(VariableMap[]::new));
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
