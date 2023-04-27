package org.camunda.community.mockito.mock;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.community.mockito.DelegateExpressions;
import org.camunda.community.mockito.delegate.DelegateExecutionFake;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FluentExecutionListenerMockTest {

  private static final String BEAN_NAME = "foo";
  private static final String MESSAGE = "message";
  private final FluentExecutionListenerMock executionListener = DelegateExpressions.registerExecutionListenerMock(BEAN_NAME);

  private final DelegateExecutionFake delegateExecution = new DelegateExecutionFake();

  @Test
  public void throws_bpmnError() {
    executionListener.onExecutionThrowBpmnError("code", MESSAGE);
    assertThatThrownBy(() -> executionListener.notify(delegateExecution))
      .isInstanceOf(BpmnError.class)
      .hasMessage(MESSAGE);
  }

  @Test
  public void set_single_variable() throws Exception {
    executionListener.onExecutionSetVariable("foo", "bar");

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("foo")).isTrue();
    assertThat((String) delegateExecution.getVariable("foo")).isEqualTo("bar");
  }

  @Test
  public void consecutive_set_variables() throws Exception {
    executionListener.onExecutionSetVariables(Map.of("foo", "bar"), Map.of("bar", "foo"));

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("foo")).isTrue();
    assertThat((String) delegateExecution.getVariable("foo")).isEqualTo("bar");

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("bar")).isTrue();
    assertThat((String) delegateExecution.getVariable("bar")).isEqualTo("foo");
  }

  @Test
  public void consecutive_set_variables_three_times() throws Exception {
    executionListener.onExecutionSetVariables(Map.of("foo", "bar"), Map.of("bar", "foo"));

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("foo")).isTrue();
    assertThat((String) delegateExecution.getVariable("foo")).isEqualTo("bar");

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("bar")).isTrue();
    assertThat((String) delegateExecution.getVariable("bar")).isEqualTo("foo");

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("bar")).isTrue();
    assertThat((String) delegateExecution.getVariable("bar")).isEqualTo("foo");
  }

  @Test
  public void consecutive_set_variables_map() throws Exception {
    executionListener.onExecutionSetVariables(Map.of("foo", "bar"));

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("foo")).isTrue();
    assertThat((String) delegateExecution.getVariable("foo")).isEqualTo("bar");

    executionListener.notify(delegateExecution);

    assertThat(delegateExecution.hasVariable("foo")).isTrue();
    assertThat((String) delegateExecution.getVariable("foo")).isEqualTo("bar");
  }

}
