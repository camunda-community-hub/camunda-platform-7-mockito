package org.camunda.bpm.extension.mockito.delegate;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.Test;

import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.assertj.core.api.Assertions.assertThat;

public class DelegateExecutionFakeTest {

  private final DelegateExecutionFake delegate = CamundaMockito.delegateExecutionFake();

  @Test
  public void with_variable_local() {
    delegate.withVariableLocal("foo", 1)
      .withVariablesLocal(Variables.putValue("bar", 2))
      .withBusinessKey("123");

    assertThat(delegate.getVariableLocal("foo")).isEqualTo(1);
    assertThat(delegate.getVariableLocal("bar")).isEqualTo(2);
  }

  @Test
  public void with_variable_local_via_factory() {
    VariableFactory<String> fooVariable = stringVariable("foo");

    delegate.withVariableLocal(fooVariable, "1");

    assertThat(fooVariable.from(delegate).getLocal()).isEqualTo("1");
  }

  @Test
  public void create_and_resolve_incident() {
    assertThat(delegate.getIncidents()).isEmpty();
    Incident incident = delegate.createIncident("type", "config", "message");

    assertThat(delegate.getIncidents().get(incident.getId())).isNotNull();

    delegate.resolveIncident(incident.getId());
    assertThat(delegate.getIncidents()).isEmpty();
  }

  @Test
  public void model_element_type() {
    delegate.withBpmnModelElementInstanceType("userTask");
    assertThat(delegate.getBpmnModelElementInstance().getElementType().getTypeName()).isEqualTo("userTask");
  }
}
