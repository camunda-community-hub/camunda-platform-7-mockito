package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.CaseService;
import org.camunda.community.mockito.ServiceExpressions;
import org.camunda.community.mockito.verify.CaseServiceVerification;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.mockito.Mockito.*;

public class CaseServiceVerifierTest {

  private static final VariableFactory<String> VAR = stringVariable("var");

  private final CaseService caseService = mock(CaseService.class);

  @Before
  public void resetMocks() {
    reset(caseService);
  }

  @Test
  public void verifyGetTimes() {
    ServiceExpressions.caseServiceVariableStubBuilder(caseService).defineAndInitialize(VAR, "value").build();
    CaseServiceVerification verifier = ServiceExpressions.caseServiceVerification(caseService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(caseService, executionId).get();
    VAR.from(caseService, executionId).get();

    verifier.verifyGet(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGet() {
    ServiceExpressions.caseServiceVariableStubBuilder(caseService).defineAndInitialize(VAR, "value").build();
    CaseServiceVerification verifier = ServiceExpressions.caseServiceVerification(caseService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(caseService, executionId).get();

    verifier.verifyGet(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetTimesLocal() {
    ServiceExpressions.caseServiceVariableStubBuilder(caseService).defineAndInitializeLocal(VAR, "value").build();
    CaseServiceVerification verifier = ServiceExpressions.caseServiceVerification(caseService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(caseService, executionId).getLocal();
    VAR.from(caseService, executionId).getLocal();

    verifier.verifyGetLocal(VAR, executionId, times(2));
    verifier.verifyNoMoreInteractions();
  }

  @Test
  public void verifyGetLocal() {
    ServiceExpressions.caseServiceVariableStubBuilder(caseService).defineAndInitializeLocal(VAR, "value").build();
    CaseServiceVerification verifier = ServiceExpressions.caseServiceVerification(caseService);
    String executionId = UUID.randomUUID().toString();

    VAR.from(caseService, executionId).getLocal();

    verifier.verifyGetLocal(VAR, executionId);
    verifier.verifyNoMoreInteractions();
  }

}
