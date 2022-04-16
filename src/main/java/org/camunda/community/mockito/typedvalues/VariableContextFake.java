package org.camunda.community.mockito.typedvalues;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.context.VariableContext;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.Set;
import java.util.function.Supplier;

/**
 * Implementation of {@link VariableContext} that internally uses a {@link VariableMap} to keep key/value pairs.
 * <p>
 * Use {@link VariableContextFake#add(String, TypedValue)} for fluent building.
 */
public class VariableContextFake implements VariableContext, Supplier<VariableMap> {

  private final VariableMap typedValues = Variables.createVariables();

  @Override
  public TypedValue resolve(final String key) {
    return typedValues.getValueTyped(key);
  }

  @Override
  public boolean containsVariable(final String key) {
    return typedValues.containsKey(key);
  }

  @Override
  public Set<String> keySet() {
    return typedValues.keySet();
  }

  public VariableContextFake add(String key, TypedValue value) {
    typedValues.put(key, value);
    return this;
  }

  @Override
  public VariableMap get() {
    return typedValues;
  }
}
