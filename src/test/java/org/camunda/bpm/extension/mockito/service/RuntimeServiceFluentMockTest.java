package org.camunda.bpm.extension.mockito.service;

import org.assertj.core.util.Lists;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.camunda.bpm.extension.mockito.task.TaskFake;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class RuntimeServiceFluentMockTest {

  private RuntimeService runtimeService = mock(RuntimeService.class);
  private Task task = TaskFake.builder().executionId("4711").build();

  @Test
  public void testSetVariableBehavior() {

    // given
    new RuntimeServiceFluentMock(runtimeService)
      .getVariable("Foo", "Bar")
      .getVariable("Bar", "Zee", "Tree")
      .getVariables(Variables.createVariables().putValue("Kermit", "TheFrog"))
      .getVariables(Lists.newArrayList("Piggy"), Variables.createVariables().putValue("Piggy", "ThePig"));


    // when -> then
    // single
    assertThat(runtimeService.getVariable(task.getExecutionId(), "Foo")).isEqualTo("Bar");
    // multiple
    assertThat(runtimeService.getVariable(task.getExecutionId(), "Bar")).isEqualTo("Zee");
    assertThat(runtimeService.getVariable(task.getExecutionId(), "Bar")).isEqualTo("Tree");
    // all
    assertThat(runtimeService.getVariables(task.getExecutionId()))
      .isEqualTo(Variables.createVariables().putValue("Kermit", "TheFrog"));
    // some
    assertThat(runtimeService.getVariables(task.getExecutionId(), Lists.newArrayList("Piggy")))
      .isEqualTo(Variables.createVariables().putValue("Piggy", "ThePig"));

  }

  @Test
  public void testSetVariableLocalBehavior() {

    // given
    new RuntimeServiceFluentMock(runtimeService)
      .getVariableLocal("Foo", "Bar")
      .getVariableLocal("Bar", "Zee", "Tree")
      .getVariablesLocal(Variables.createVariables().putValue("Kermit", "TheFrog"))
      .getVariablesLocal(Lists.newArrayList("Piggy"), Variables.createVariables().putValue("Piggy", "ThePig"));


    // when -> then
    // single
    assertThat(runtimeService.getVariableLocal(task.getExecutionId(), "Foo")).isEqualTo("Bar");
    // multiple
    assertThat(runtimeService.getVariableLocal(task.getExecutionId(), "Bar")).isEqualTo("Zee");
    assertThat(runtimeService.getVariableLocal(task.getExecutionId(), "Bar")).isEqualTo("Tree");
    // all
    assertThat(runtimeService.getVariablesLocal(task.getExecutionId()))
      .isEqualTo(Variables.createVariables().putValue("Kermit", "TheFrog"));
    // some
    assertThat(runtimeService.getVariablesLocal(task.getExecutionId(), Lists.newArrayList("Piggy")))
      .isEqualTo(Variables.createVariables().putValue("Piggy", "ThePig"));
  }

  @Test
  public void testSetVariableLocalTypedBehavior() {

    // given
    new RuntimeServiceFluentMock(runtimeService)
      .getVariableLocalTyped("Foo", true, Variables.stringValue("Bar"))
      .getVariableLocalTyped("Bar", true, Variables.stringValue("Zee"), Variables.stringValue("Tree"))
      .getVariablesLocalTyped(true, Variables.createVariables().putValue("Kermit", "TheFrog"))
      .getVariablesLocalTyped(Lists.newArrayList("Piggy"), true, Variables.createVariables().putValue("Piggy", "ThePig"));

    // given
    new RuntimeServiceFluentMock(runtimeService)
      .getVariableLocalTyped("Foo", Variables.stringValue("Bar"))
      .getVariableLocalTyped("Bar", Variables.stringValue("Zee"), Variables.stringValue("Tree"))
      .getVariablesLocalTyped(Variables.createVariables().putValue("Kermit", "TheFrog"));


    // when -> then
    // single
    assertThat(runtimeService.<StringValue>getVariableLocalTyped(task.getExecutionId(), "Foo", true)).isEqualTo(Variables.stringValue("Bar"));
    // multiple
    assertThat(runtimeService.<StringValue>getVariableLocalTyped(task.getExecutionId(), "Bar", true)).isEqualTo(Variables.stringValue("Zee"));
    assertThat(runtimeService.<StringValue>getVariableLocalTyped(task.getExecutionId(), "Bar", true)).isEqualTo(Variables.stringValue("Tree"));
    // all
    assertThat(runtimeService.getVariablesLocalTyped(task.getExecutionId(), true))
      .isEqualTo(Variables.createVariables().putValue("Kermit", "TheFrog"));
    // some
    assertThat(runtimeService.getVariablesLocalTyped(task.getExecutionId(), Lists.newArrayList("Piggy"), true))
      .isEqualTo(Variables.createVariables().putValue("Piggy", "ThePig"));

    assertThat(runtimeService.<StringValue>getVariableLocalTyped(task.getExecutionId(), "Foo")).isEqualTo(Variables.stringValue("Bar"));
    // multiple
    assertThat(runtimeService.<StringValue>getVariableLocalTyped(task.getExecutionId(), "Bar")).isEqualTo(Variables.stringValue("Zee"));
    assertThat(runtimeService.<StringValue>getVariableLocalTyped(task.getExecutionId(), "Bar")).isEqualTo(Variables.stringValue("Tree"));
    // all
    assertThat(runtimeService.getVariablesLocalTyped(task.getExecutionId()))
      .isEqualTo(Variables.createVariables().putValue("Kermit", "TheFrog"));

  }


  @Test
  public void testSetVariableTypedBehavior() {

    // given
    new RuntimeServiceFluentMock(runtimeService)
      .getVariableTyped("Foo", true, Variables.stringValue("Bar"))
      .getVariableTyped("Bar", true, Variables.stringValue("Zee"), Variables.stringValue("Tree"))
      .getVariablesTyped(true, Variables.createVariables().putValue("Kermit", "TheFrog"))
      .getVariablesTyped(Lists.newArrayList("Piggy"), true, Variables.createVariables().putValue("Piggy", "ThePig"));

    // given
    new RuntimeServiceFluentMock(runtimeService)
      .getVariableTyped("Foo", Variables.stringValue("Bar"))
      .getVariableTyped("Bar", Variables.stringValue("Zee"), Variables.stringValue("Tree"))
      .getVariablesTyped(Variables.createVariables().putValue("Kermit", "TheFrog"));


    // when -> then
    // single
    assertThat(runtimeService.<StringValue>getVariableTyped(task.getExecutionId(), "Foo", true)).isEqualTo(Variables.stringValue("Bar"));
    // multiple
    assertThat(runtimeService.<StringValue>getVariableTyped(task.getExecutionId(), "Bar", true)).isEqualTo(Variables.stringValue("Zee"));
    assertThat(runtimeService.<StringValue>getVariableTyped(task.getExecutionId(), "Bar", true)).isEqualTo(Variables.stringValue("Tree"));
    // all
    assertThat(runtimeService.getVariablesTyped(task.getExecutionId(), true))
      .isEqualTo(Variables.createVariables().putValue("Kermit", "TheFrog"));
    // some
    assertThat(runtimeService.getVariablesTyped(task.getExecutionId(), Lists.newArrayList("Piggy"), true))
      .isEqualTo(Variables.createVariables().putValue("Piggy", "ThePig"));

    assertThat(runtimeService.<StringValue>getVariableTyped(task.getExecutionId(), "Foo")).isEqualTo(Variables.stringValue("Bar"));
    // multiple
    assertThat(runtimeService.<StringValue>getVariableTyped(task.getExecutionId(), "Bar")).isEqualTo(Variables.stringValue("Zee"));
    assertThat(runtimeService.<StringValue>getVariableTyped(task.getExecutionId(), "Bar")).isEqualTo(Variables.stringValue("Tree"));
    // all
    assertThat(runtimeService.getVariablesTyped(task.getExecutionId()))
      .isEqualTo(Variables.createVariables().putValue("Kermit", "TheFrog"));

  }

}
