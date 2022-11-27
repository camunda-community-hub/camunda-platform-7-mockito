package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.community.mockito.ServiceExpressions;
import org.camunda.community.mockito.verify.RuntimeServiceVerification;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.mockito.Mockito.*;

public class RuntimeServiceVerifierTest {

  private static final VariableFactory<String> VAR = stringVariable("var");

  private final RuntimeService runtimeService = mock(RuntimeService.class);

  @Before
  public void resetMocks() {
    reset(runtimeService);
  }

  @Test
  public void verifyGetTimes() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).defineAndInitialize(VAR, "value").build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(runtimeService, executionId).get();
    VAR.from(runtimeService, executionId).get();

    verifier.verifyGet(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGet() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).defineAndInitialize(VAR, "value").build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(runtimeService, executionId).get();

    verifier.verifyGet(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetTimesLocal() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).defineAndInitializeLocal(VAR, "value").build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(runtimeService, executionId).getLocal();
    VAR.from(runtimeService, executionId).getLocal();

    verifier.verifyGetLocal(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetLocal() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).defineAndInitializeLocal(VAR, "value").build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(runtimeService, executionId).getLocal();

    verifier.verifyGetLocal(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifySet() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).set("value");
    verifier.verifySet(VAR, "value", executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifySetTimes() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).set("value");
    VAR.on(runtimeService, executionId).set("value");
    verifier.verifySet(VAR, "value", executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }


  @Test
  public void verifySetLocal() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).setLocal("value");
    verifier.verifySetLocal(VAR, "value", executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifySetTimesLocal() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).setLocal("value");
    VAR.on(runtimeService, executionId).setLocal("value");
    verifier.verifySetLocal(VAR, "value", executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemove() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).remove();
    verifier.verifyRemove(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemoveTimes() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).remove();
    VAR.on(runtimeService, executionId).remove();
    verifier.verifyRemove(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemoveLocal() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).removeLocal();
    verifier.verifyRemoveLocal(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyRemoveLocalTimes() {
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService).define(VAR).build();
    RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);
    String executionId = UUID.randomUUID().toString();
    VAR.on(runtimeService, executionId).removeLocal();
    VAR.on(runtimeService, executionId).removeLocal();
    verifier.verifyRemoveLocal(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }
}
