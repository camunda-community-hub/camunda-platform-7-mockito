package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.TaskService;
import org.camunda.community.mockito.ServiceExpressions;
import org.camunda.community.mockito.verify.TaskServiceVerification;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.mockito.Mockito.*;

public class TaskServiceVerifierTest {

  private static final VariableFactory<String> VAR = stringVariable("var");

  private final TaskService taskService = mock(TaskService.class);

  @Before
  public void resetMocks() {
    reset(taskService);
  }

  @Test
  public void verifyGetTimes() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).defineAndInitialize(VAR, "value").build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(taskService, executionId).get();
    VAR.from(taskService, executionId).get();

    verifier.verifyGet(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGet() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).defineAndInitialize(VAR, "value").build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(taskService, executionId).get();

    verifier.verifyGet(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetTimesLocal() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).defineAndInitializeLocal(VAR, "value").build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(taskService, executionId).getLocal();
    VAR.from(taskService, executionId).getLocal();

    verifier.verifyGetLocal(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetLocal() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).defineAndInitializeLocal(VAR, "value").build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(taskService, executionId).getLocal();

    verifier.verifyGetLocal(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

}
