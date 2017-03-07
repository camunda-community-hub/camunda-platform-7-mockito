package org.camunda.bpm.extension.mockito.delegate;


import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class VariableScopeFake<T extends VariableScopeFake> implements VariableScope {

  private final VariableMap global = Variables.createVariables();
  private final VariableMap local = Variables.createVariables();
  private final String variableScopeKey;

  public VariableScopeFake() {
    this("fake");
  }

  public VariableScopeFake(String variableScopeKey) {
    this.variableScopeKey = variableScopeKey;
  }

  @Override
  public String getVariableScopeKey() {
    return variableScopeKey;
  }

  @Override
  public Map<String, Object> getVariables() {
    return global;
  }

  @Override
  public VariableMap getVariablesTyped() {
    return global;
  }

  @Override
  public VariableMap getVariablesTyped(boolean deserializeValues) {
    throw new UnsupportedOperationException("variables-typed not implemented");
  }

  @Override
  public Map<String, Object> getVariablesLocal() {
    return local;
  }

  @Override
  public VariableMap getVariablesLocalTyped() {
    return local;
  }

  @Override
  public VariableMap getVariablesLocalTyped(boolean deserializeValues) {
    throw new UnsupportedOperationException("variables-typed not implemented");
  }

  @Override
  public Object getVariable(String variableName) {
    return global.get(variableName);
  }

  @Override
  public Object getVariableLocal(String variableName) {
    return local.get(variableName);
  }

  @Override
  public <T extends TypedValue> T getVariableTyped(String variableName) {
    return global.getValueTyped(variableName);
  }

  @Override
  public <T extends TypedValue> T getVariableTyped(String variableName, boolean deserializeValue) {
    throw new UnsupportedOperationException("variables-typed not implemented");
  }

  @Override
  public <T extends TypedValue> T getVariableLocalTyped(String variableName) {
    return local.getValueTyped(variableName);
  }

  @Override
  public <T extends TypedValue> T getVariableLocalTyped(String variableName, boolean deserializeValue) {
    throw new UnsupportedOperationException("variables-typed not implemented");
  }

  @Override
  public Set<String> getVariableNames() {
    return global.keySet();
  }

  @Override
  public Set<String> getVariableNamesLocal() {
    return local.keySet();
  }

  @Override
  public void setVariable(String variableName, Object value) {
    global.putValue(variableName, value);
  }

  public T withVariable(String variableName, Object value) {
    setVariable(variableName, value);
    return (T) this;
  }


  @Override
  public void setVariableLocal(String variableName, Object value) {
    local.putValue(variableName, value);
  }

  public T withVariableLocal(String variableName, Object value) {
    setVariableLocal(variableName, value);
    return (T) this;
  }

  @Override
  public void setVariables(Map<String, ? extends Object> variables) {
    global.putAll(variables);
  }

  public T withVariables(Map<String, ? extends Object> variables) {
    setVariables(variables);
    return (T) this;
  }

  @Override
  public void setVariablesLocal(Map<String, ? extends Object> variables) {
    local.putAll(variables);
  }

  public T withVariablesLocal(Map<String, ? extends Object> variables) {
    setVariablesLocal(variables);
    return (T) this;
  }

  @Override
  public boolean hasVariables() {
    return !global.isEmpty();
  }

  @Override
  public boolean hasVariablesLocal() {
    return !local.isEmpty();
  }

  @Override
  public boolean hasVariable(String variableName) {
    return global.containsKey(variableName);
  }

  @Override
  public boolean hasVariableLocal(String variableName) {
    return local.containsKey(variableName);
  }

  @Override
  public void removeVariable(String variableName) {
    global.remove(variableName);
  }

  @Override
  public void removeVariableLocal(String variableName) {
    local.remove(variableName);
  }

  @Override
  public void removeVariables(Collection<String> variableNames) {
    variableNames.forEach(this::removeVariable);
  }

  @Override
  public void removeVariablesLocal(Collection<String> variableNames) {
    variableNames.forEach(this::removeVariableLocal);
  }

  @Override
  public void removeVariables() {
    global.clear();
  }

  @Override
  public void removeVariablesLocal() {
    local.clear();
  }
}
