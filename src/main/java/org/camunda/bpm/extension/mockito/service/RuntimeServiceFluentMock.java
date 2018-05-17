package org.camunda.bpm.extension.mockito.service;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.Collection;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unused")
public class RuntimeServiceFluentMock {

  private final RuntimeService runtimeService;

  /**
   * Create fluent mock instance and constructs the runtime service mock.
   */
  public RuntimeServiceFluentMock() {
    this.runtimeService = mock(RuntimeService.class);
  }

  public RuntimeServiceFluentMock(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }

  public RuntimeServiceFluentMock getVariable(final String variableName, final Object value, final Object... values) {
    when(runtimeService.getVariable(anyString(), eq(variableName))).thenReturn(value, values);
    return this;
  }

  public RuntimeServiceFluentMock getVariables(final Map<String, Object> values) {
    when(runtimeService.getVariables(anyString())).thenReturn(values);
    return this;
  }

  public RuntimeServiceFluentMock getVariables(final Collection<String> variableNames, final Map<String, Object> values) {
    when(runtimeService.getVariables(anyString(), eq(variableNames))).thenReturn(values);
    return this;
  }

  public RuntimeServiceFluentMock getVariableLocal(final String variableName, final Object value, final Object... values) {
    when(runtimeService.getVariableLocal(anyString(), eq(variableName))).thenReturn(value, values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesLocal(final Map<String, Object> values) {
    when(runtimeService.getVariablesLocal(anyString())).thenReturn(values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesLocal(final Collection<String> variableNames, final Map<String, Object> values) {
    when(runtimeService.getVariablesLocal(anyString(), eq(variableNames))).thenReturn(values);
    return this;
  }

  public <T extends TypedValue> RuntimeServiceFluentMock getVariableLocalTyped(final String variableName, boolean deserializeValues, final T value, final T... values) {
    when(runtimeService.getVariableLocalTyped(anyString(), eq(variableName), eq(deserializeValues))).thenReturn(value, values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesLocalTyped(boolean deserializeValues, final VariableMap values) {
    when(runtimeService.getVariablesLocalTyped(anyString(), eq(deserializeValues))).thenReturn(values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesLocalTyped(final Collection<String> variableNames, boolean deserializeValues, final VariableMap values) {
    when(runtimeService.getVariablesLocalTyped(anyString(), eq(variableNames), eq(deserializeValues))).thenReturn(values);
    return this;
  }

  public <T extends TypedValue> RuntimeServiceFluentMock getVariableLocalTyped(final String variableName, final T value, final T... values) {
    when(runtimeService.getVariableLocalTyped(anyString(), eq(variableName))).thenReturn(value, values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesLocalTyped(final VariableMap values) {
    when(runtimeService.getVariablesLocalTyped(anyString())).thenReturn(values);
    return this;
  }

  public <T extends TypedValue> RuntimeServiceFluentMock getVariableTyped(final String variableName, boolean deserializeValues, final T value, final T... values) {
    when(runtimeService.getVariableTyped(anyString(), eq(variableName), eq(deserializeValues))).thenReturn(value, values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesTyped(boolean deserializeValues, final VariableMap values) {
    when(runtimeService.getVariablesTyped(anyString(), eq(deserializeValues))).thenReturn(values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesTyped(final Collection<String> variableNames, boolean deserializeValues, final VariableMap values) {
    when(runtimeService.getVariablesTyped(anyString(), eq(variableNames), eq(deserializeValues))).thenReturn(values);
    return this;
  }

  public <T extends TypedValue> RuntimeServiceFluentMock getVariableTyped(final String variableName, final T value, final T... values) {
    when(runtimeService.getVariableTyped(anyString(), eq(variableName))).thenReturn(value, values);
    return this;
  }

  public RuntimeServiceFluentMock getVariablesTyped(final VariableMap values) {
    when(runtimeService.getVariablesTyped(anyString())).thenReturn(values);
    return this;
  }

  public RuntimeService getRuntimeService() {
    return runtimeService;
  }
}
