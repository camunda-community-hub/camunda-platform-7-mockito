package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.TaskService;
import org.camunda.community.mockito.ServiceExpressions;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.booleanVariable;
import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test to for task service variable access.
 */
public class TaskServiceStubbingTest {
  private static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  private static final VariableFactory<Boolean> ORDER_FLAG = booleanVariable("orderFlag");

  private final TaskService taskService = mock(TaskService.class);
  private final MyTaskRestController myTaskRestController = new MyTaskRestController(taskService);

  @Test
  public void stubs_variable_access() {

    String taskId = UUID.randomUUID().toString();

    ServiceExpressions.taskServiceVariableStubBuilder(taskService)
      .defineAndInitializeLocal(ORDER_ID, "initial-Value")
      .define(ORDER_FLAG)
      .build();

    myTaskRestController.writeLocalId(taskId, "4712");
    String orderId = myTaskRestController.readLocalId(taskId);
    assertThat(orderId).isEqualTo("4712");

    assertThat(myTaskRestController.flagExists(taskId)).isFalse();
    myTaskRestController.writeFlag(taskId, true);
    assertThat(myTaskRestController.flagExists(taskId)).isTrue();
    Boolean orderFlag = myTaskRestController.readFlag(taskId);
    assertThat(orderFlag).isEqualTo(true);
  }


  static class MyTaskRestController {
    private final TaskService taskService;

    MyTaskRestController(TaskService taskService) {
      this.taskService = taskService;
    }

    public String readLocalId(String taskId) {
      return ORDER_ID.from(taskService, taskId).getLocal();
    }

    public void writeLocalId(String taskId, String value) {
      ORDER_ID.on(taskService, taskId).setLocal(value);
    }

    public void writeFlag(String taskId, Boolean value) {
      ORDER_FLAG.on(taskService, taskId).set(value);
    }

    public Boolean readFlag(String taskId) {
      return ORDER_FLAG.from(taskService, taskId).get();
    }

    public Boolean flagExists(String taskId) {
      return taskService.getVariables(taskId).containsKey(ORDER_FLAG.getName());
    }
  }
}
