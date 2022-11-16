package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.TaskService;
import org.camunda.community.mockito.ServiceExpressions;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.booleanVariable;
import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.camunda.bpm.model.xml.test.assertions.ModelAssertions.assertThat;
import static org.mockito.Mockito.mock;

public class TaskServiceStubbingTest {
  private static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  private static final VariableFactory<Boolean> ORDER_FLAG = booleanVariable("orderFlag");

  private final TaskService taskService = mock(TaskService.class);
  private final TaskServiceAwareService testee = new TaskServiceAwareService(taskService);

  @Test
  public void stubs_variable_access() {

    String executionId = UUID.randomUUID().toString();

    ServiceExpressions.taskServiceVariableStubBuilder(taskService)
      .defineAndInitializeLocal(ORDER_ID, "initial-Value")
      .define(ORDER_FLAG)
      .build();

    testee.writeLocalId(executionId, "4712");
    String orderId = testee.readLocalId(executionId);
    assertThat(orderId).isEqualTo("4712");

    assertThat(testee.flagExists(executionId)).isFalse();
    testee.writeFlag(executionId, true);
    assertThat(testee.flagExists(executionId)).isTrue();
    Boolean orderFlag = testee.readFlag(executionId);
    assertThat(orderFlag).isEqualTo(true);
  }


  static class TaskServiceAwareService {
    private final TaskService taskService;

    TaskServiceAwareService(TaskService taskService) {
      this.taskService = taskService;
    }

    public String readLocalId(String execution) {
      return ORDER_ID.from(taskService, execution).getLocal();
    }

    public void writeLocalId(String execution, String value) {
      ORDER_ID.on(taskService, execution).setLocal(value);
    }

    public void writeFlag(String execution, Boolean value) {
      ORDER_FLAG.on(taskService, execution).set(value);
    }

    public Boolean readFlag(String execution) {
      return ORDER_FLAG.from(taskService, execution).get();
    }

    public Boolean flagExists(String execution) {
      return taskService.getVariables(execution).containsKey(ORDER_FLAG.getName());
    }
  }
}
