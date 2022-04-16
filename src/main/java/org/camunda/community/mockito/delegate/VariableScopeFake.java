package org.camunda.community.mockito.delegate;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.impl.core.variable.CoreVariableInstance;
import org.camunda.bpm.engine.impl.core.variable.scope.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class VariableScopeFake<T extends VariableScopeFake> extends AbstractVariableScope implements VariableScope {

  protected VariableStore<CoreVariableInstance> variableStore = new VariableStore<>();
  protected VariableInstanceFactory<CoreVariableInstance> variableInstanceFactory = (name, value, isTransient) -> new SimpleVariableInstance(name, value);

  @Override
  protected VariableStore<CoreVariableInstance> getVariableStore() {
    return variableStore;
  }

  @Override
  protected VariableInstanceFactory<CoreVariableInstance> getVariableInstanceFactory() {
    return variableInstanceFactory;
  }

  @Override
  public String getVariableScopeKey() {
    return "fake";
  }

  @Override
  protected List<VariableInstanceLifecycleListener<CoreVariableInstance>> getVariableInstanceLifecycleListeners() {
    return Collections.EMPTY_LIST;
  }

  @Override
  public AbstractVariableScope getParentVariableScope() {
    return null;
  }

  public T withVariable(String variableName, Object value) {
    setVariable(variableName, value);
    return (T) this;
  }

  public T withVariableLocal(String variableName, Object value) {
    setVariableLocal(variableName, value);
    return (T) this;
  }

  public <V> T withVariable(VariableFactory<V> variable, V value) {
    variable.on(this).set(value);
    return (T) this;
  }

  public <V> T withVariableLocal(VariableFactory<V> variable, V value) {
    variable.on(this).setLocal(value);
    return (T) this;
  }

  public T withVariables(Map<String, ?> variables) {
    setVariables(variables);
    return (T) this;
  }

  public T withVariablesLocal(Map<String, ?> variables) {
    setVariablesLocal(variables);
    return (T) this;
  }
}
