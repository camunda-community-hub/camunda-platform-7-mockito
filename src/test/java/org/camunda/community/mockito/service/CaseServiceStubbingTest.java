package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.CaseService;
import org.camunda.community.mockito.ServiceExpressions;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.booleanVariable;
import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test to for case service variable access.
 */
public class CaseServiceStubbingTest {
  private static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  private static final VariableFactory<Boolean> ORDER_FLAG = booleanVariable("orderFlag");

  @Test
  public void stubs_variable_access() {

    final CaseService caseService = mock(CaseService.class);
    final CaseServiceAwareService serviceUnderTest = new CaseServiceAwareService(caseService);

    String executionId = UUID.randomUUID().toString();

    ServiceExpressions.caseServiceVariableStubBuilder(caseService)
      .defineAndInitializeLocal(ORDER_ID, "initial-Value")
      .define(ORDER_FLAG)
      .build();

    serviceUnderTest.writeLocalId(executionId, "4712");
    String orderId = serviceUnderTest.readLocalId(executionId);
    assertThat(orderId).isEqualTo("4712");

    assertThat(serviceUnderTest.flagExists(executionId)).isFalse();
    serviceUnderTest.writeFlag(executionId, true);
    assertThat(serviceUnderTest.flagExists(executionId)).isTrue();
    Boolean orderFlag = serviceUnderTest.readFlag(executionId);
    assertThat(orderFlag).isEqualTo(true);
  }


  static class CaseServiceAwareService {
    private final CaseService caseService;

    CaseServiceAwareService(CaseService caseService) {
      this.caseService = caseService;
    }

    public String readLocalId(String executionId) {
      return ORDER_ID.from(caseService, executionId).getLocal();
    }

    public void writeLocalId(String executionId, String value) {
      ORDER_ID.on(caseService, executionId).setLocal(value);
    }

    public void writeFlag(String executionId, Boolean value) {
      ORDER_FLAG.on(caseService, executionId).set(value);
    }

    public Boolean readFlag(String executionId) {
      return ORDER_FLAG.from(caseService, executionId).get();
    }

    public Boolean flagExists(String executionId) {
      return caseService.getVariables(executionId).containsKey(ORDER_FLAG.getName());
    }
  }
}
