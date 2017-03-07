package org.camunda.bpm.extension.mockito.delegate;


import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.variable.Variables.stringValue;

public class VariableScopeFakeTest {

  private final VariableScopeFake variableScope = CamundaMockito.variableScopeFake();

  @Test
  public void create_withVariable() throws Exception {
    variableScope.withVariable("foo", 1).withVariables(Variables.putValue("bar", 2));

    assertThat(variableScope.getVariableNames()).containsOnly("foo", "bar");
  }

  @Test
  public void variablesTyped() throws Exception {
    VariableMap variables = Variables.putValueTyped("foo", stringValue("bar"));

    variableScope.setVariablesLocal(variables);

    StringValue foo = variableScope.getVariableLocalTyped("foo");

    assertThat(foo.getValue()).isEqualTo("bar");
  }

  @Test
  public void variablesLocal() throws Exception {
    VariableMap variables = Variables.putValue("foo", "bar");
    variableScope.setVariablesLocal(variables);

    String foo = (String) variableScope.getVariableLocal("foo");

    assertThat(foo).isEqualTo("bar");

  }
}
