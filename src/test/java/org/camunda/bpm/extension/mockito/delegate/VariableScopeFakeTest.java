package org.camunda.bpm.extension.mockito.delegate;


import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableScopeFakeTest {

  private final VariableScopeFake variableScope = CamundaMockito.variableScopeFake();

  @Test
  public void create_withVariable() throws Exception {
    variableScope.withVariable("foo", 1).withVariables(Variables.putValue("bar", 2));

    assertThat(variableScope.getVariableNames()).containsOnly("foo", "bar");
  }
}
