package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.CaseService;
import org.camunda.community.mockito.ServiceExpressions;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.booleanVariable;
import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.camunda.bpm.model.xml.test.assertions.ModelAssertions.assertThat;
import static org.mockito.Mockito.mock;

public class CaseServiceStubbingTest {
  private static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  private static final VariableFactory<Boolean> ORDER_FLAG = booleanVariable("orderFlag");

  private final CaseService caseService = mock(CaseService.class);
  private final CaseServiceAwareService testee = new CaseServiceAwareService(caseService);

  @Test
  public void stubs_variable_access() {

    String executionId = UUID.randomUUID().toString();

    ServiceExpressions.caseServiceVariableStubBuilder(caseService)
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


  static class CaseServiceAwareService {
    private final CaseService caseService;

    CaseServiceAwareService(CaseService caseService) {
      this.caseService = caseService;
    }

    public String readLocalId(String execution) {
      return ORDER_ID.from(caseService, execution).getLocal();
    }

    public void writeLocalId(String execution, String value) {
      ORDER_ID.on(caseService, execution).setLocal(value);
    }

    public void writeFlag(String execution, Boolean value) {
      ORDER_FLAG.on(caseService, execution).set(value);
    }

    public Boolean readFlag(String execution) {
      return ORDER_FLAG.from(caseService, execution).get();
    }

    public Boolean flagExists(String execution) {
      return caseService.getVariables(execution).containsKey(ORDER_FLAG.getName());
    }
  }
}
