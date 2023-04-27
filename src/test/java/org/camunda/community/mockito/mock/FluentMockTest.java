package org.camunda.community.mockito.mock;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.impl.VariableMapImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FluentMockTest {

  @Test
  public void put_in_front_of_nothing() {
    VariableMap variableMap = new VariableMapImpl();
    VariableMap[] variableMaps = FluentMock.combineVariableMaps(variableMap);
    assertThat(variableMaps)
      .hasSize(1)
      .contains(variableMap);
  }

  @Test
  public void put_in_front_of_one_element() {
    VariableMap variableMap1 = new VariableMapImpl();
    VariableMap variableMap2 = new VariableMapImpl();
    VariableMap[] variableMaps = FluentMock.combineVariableMaps(variableMap1, variableMap2);
    assertThat(variableMaps)
      .hasSize(2)
      .contains(variableMap1, variableMap2);
  }
}
