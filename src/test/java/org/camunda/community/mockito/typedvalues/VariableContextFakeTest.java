package org.camunda.community.mockito.typedvalues;


import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableContextFakeTest {

  private final VariableContextFake fake = new VariableContextFake();

  @Test
  public void can_add_to_fake() {
    assertThat(fake.keySet()).isEmpty();
    assertThat(fake.containsVariable("foo")).isFalse();
    assertThat(fake.resolve("foo")).isNull();

    fake.add("foo", Variables.booleanValue(false));

    assertThat(fake.keySet()).containsOnly("foo");
    assertThat(fake.containsVariable("foo")).isTrue();
    assertThat(fake.resolve("foo")).isEqualTo(Variables.booleanValue(false));
  }

  @Test
  public void to_variableMap() {
    fake.add("bar", Variables.stringValue("foo"));
    fake.add("foo", Variables.stringValue("bar"));

    VariableMap map = fake.get();

    assertThat(map.getValue("foo", String.class)).isEqualTo("bar");
    assertThat(map.getValue("bar", String.class)).isEqualTo("foo");
  }
}
