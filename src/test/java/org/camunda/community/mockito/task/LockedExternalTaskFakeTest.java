package org.camunda.community.mockito.task;

import io.holunda.camunda.bpm.data.CamundaBpmData;
import io.holunda.camunda.bpm.data.factory.VariableFactory;
import io.holunda.camunda.bpm.data.reader.VariableReader;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LockedExternalTaskFakeTest {

  private final VariableFactory<String> var1 = CamundaBpmData.stringVariable("var1");
  private final VariableFactory<String> var2 = CamundaBpmData.stringVariable("var2");

  @Test
  public void create_locked_task() {
    LockedExternalTaskFake fake = LockedExternalTaskFake.builder()
      .id("1")
      .activityId("foo")
      .variables(Variables.putValue("var2", "world"))
      .variable(var1, "hello")
      .build();

    assertThat(fake.getId()).isEqualTo("1");
    assertThat(fake.getActivityId()).isEqualTo("foo");

    final VariableReader reader = CamundaBpmData.reader(fake.getVariables());

    assertThat(reader.get(var1)).isEqualTo("hello");
    assertThat(reader.get(var2)).isEqualTo("world");

  }
}
