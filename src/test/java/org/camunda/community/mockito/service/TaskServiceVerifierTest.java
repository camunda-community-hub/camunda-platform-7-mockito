package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.CamundaBpmData;
import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.variable.VariableMap;
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
    String taskId = UUID.randomUUID().toString();

    VAR.from(taskService, taskId).get();
    VAR.from(taskService, taskId).get();

    verifier.verifyGet(VAR, taskId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGet() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).defineAndInitialize(VAR, "value").build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();

    VAR.from(taskService, taskId).get();

    verifier.verifyGet(VAR, taskId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetTimesLocal() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).defineAndInitializeLocal(VAR, "value").build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();

    VAR.from(taskService, taskId).getLocal();
    VAR.from(taskService, taskId).getLocal();

    verifier.verifyGetLocal(VAR, taskId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetLocal() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).defineAndInitializeLocal(VAR, "value").build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();

    VAR.from(taskService, taskId).getLocal();

    verifier.verifyGetLocal(VAR, taskId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifySet() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).set("value");
    verifier.verifySet(VAR, "value", taskId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifySetTimes() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).set("value");
    VAR.on(taskService, taskId).set("value");
    verifier.verifySet(VAR, "value", taskId, times(2));
    verifier.verifyNoMoreInteractions();
  }


  @Test
  public void verifySetLocal() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).setLocal("value");
    verifier.verifySetLocal(VAR, "value", taskId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifySetTimesLocal() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).setLocal("value");
    VAR.on(taskService, taskId).setLocal("value");
    verifier.verifySetLocal(VAR, "value", taskId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemove() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).remove();
    verifier.verifyRemove(VAR, taskId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemoveTimes() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).remove();
    VAR.on(taskService, taskId).remove();
    verifier.verifyRemove(VAR, taskId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemoveLocal() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).removeLocal();
    verifier.verifyRemoveLocal(VAR, taskId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemoveLocalTimes() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    VAR.on(taskService, taskId).removeLocal();
    VAR.on(taskService, taskId).removeLocal();
    verifier.verifyRemoveLocal(VAR, taskId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyComplete() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();
    taskService.complete(taskId);
    verifier.verifyComplete(taskId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyCompleteWithVariables() {
    ServiceExpressions.taskServiceVariableStubBuilder(taskService).define(VAR).build();
    TaskServiceVerification verifier = ServiceExpressions.taskServiceVerification(taskService);
    String taskId = UUID.randomUUID().toString();

    VariableMap vars = CamundaBpmData
      .builder()
      .set(VAR, "someValue")
      .build();

    taskService.complete(taskId, vars);
    verifier.verifyComplete(vars, taskId);
    verifier.verifyNoMoreInteractions();
  }

}
