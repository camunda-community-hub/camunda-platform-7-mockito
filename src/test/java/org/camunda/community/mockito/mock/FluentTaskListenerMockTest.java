package org.camunda.community.mockito.mock;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.community.mockito.DelegateExpressions;
import org.camunda.community.mockito.delegate.DelegateTaskFake;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FluentTaskListenerMockTest {

  private static final String BEAN_NAME = "foo";
  private static final String MESSAGE = "message";
  private final FluentTaskListenerMock taskListener = DelegateExpressions.registerTaskListenerMock(BEAN_NAME);

  private final DelegateTaskFake delegateTask = new DelegateTaskFake();

  @Test
  public void throws_bpmnError() {
    taskListener.onExecutionThrowBpmnError("code", MESSAGE);
    assertThatThrownBy(() -> taskListener.notify(delegateTask))
      .isInstanceOf(BpmnError.class)
      .hasMessage(MESSAGE);
  }

  @Test
  public void set_single_variable() {
    taskListener.onExecutionSetVariable("foo", "bar");

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("foo")).isTrue();
    assertThat((String) delegateTask.getVariable("foo")).isEqualTo("bar");
  }

  @Test
  public void consecutive_set_variables() {
    taskListener.onExecutionSetVariables(Map.of("foo", "bar"), Map.of("bar", "foo"));

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("foo")).isTrue();
    assertThat((String) delegateTask.getVariable("foo")).isEqualTo("bar");

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("bar")).isTrue();
    assertThat((String) delegateTask.getVariable("bar")).isEqualTo("foo");
  }

  @Test
  public void consecutive_set_variables_three_times() {
    taskListener.onExecutionSetVariables(Map.of("foo", "bar"), Map.of("bar", "foo"));

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("foo")).isTrue();
    assertThat((String) delegateTask.getVariable("foo")).isEqualTo("bar");

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("bar")).isTrue();
    assertThat((String) delegateTask.getVariable("bar")).isEqualTo("foo");

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("bar")).isTrue();
    assertThat((String) delegateTask.getVariable("bar")).isEqualTo("foo");
  }

  @Test
  public void consecutive_set_variables_map() {
    taskListener.onExecutionSetVariables(Map.of("foo", "bar"));

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("foo")).isTrue();
    assertThat((String) delegateTask.getVariable("foo")).isEqualTo("bar");

    taskListener.notify(delegateTask);

    assertThat(delegateTask.hasVariable("foo")).isTrue();
    assertThat((String) delegateTask.getVariable("foo")).isEqualTo("bar");
  }
}
