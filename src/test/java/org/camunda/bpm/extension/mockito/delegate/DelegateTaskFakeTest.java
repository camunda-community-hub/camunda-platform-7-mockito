package org.camunda.bpm.extension.mockito.delegate;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DelegateTaskFakeTest {

  private final DelegateTaskFake delegate = new DelegateTaskFake();

  @Test
  public void withId() throws Exception {
    assertThat(delegate.withId("1").getId()).isEqualTo("1");
  }

  @Test
  public void setName() throws Exception {
    assertThat(delegate.getName()).isNull();
    delegate.setName("foo");
    assertThat(delegate.getName()).isEqualTo("foo");
  }

  @Test
  public void setDescription() throws Exception {
    assertThat(delegate.getDescription()).isNull();
    delegate.setDescription("foo");
    assertThat(delegate.getDescription()).isEqualTo("foo");
  }

  @Test
  public void setPriority() throws Exception {
    assertThat(delegate.getPriority()).isEqualTo(0);
    delegate.setPriority(10);
    assertThat(delegate.getPriority()).isEqualTo(10);
  }
}
