package org.camunda.bpm.extension.mockito.delegate;


import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DelegateExecutionFakeTest {

  private final DelegateExecutionFake delegate = CamundaMockito.delegateExecutionFake();

  @Test
  public void withVariableLocal() throws Exception {
    delegate.withVariableLocal("foo", 1)
      .withVariablesLocal(Variables.putValue("bar", 2))
      .withBusinessKey("123");

    assertThat(delegate.getVariableLocal("foo")).isEqualTo(1);
    assertThat(delegate.getVariableLocal("bar")).isEqualTo(2);
  }
}
