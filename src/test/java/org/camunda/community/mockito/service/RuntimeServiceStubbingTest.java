package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.community.mockito.ServiceExpressions;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.booleanVariable;
import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.camunda.bpm.model.xml.test.assertions.ModelAssertions.assertThat;
import static org.mockito.Mockito.mock;

public class RuntimeServiceStubbingTest {
  private static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  private static final VariableFactory<Boolean> ORDER_FLAG = booleanVariable("orderFlag");

  private final RuntimeService runtimeService = mock(RuntimeService.class);
  private final RuntimeServiceAwareService testee = new RuntimeServiceAwareService(runtimeService);

  @Test
  public void stubs_variable_access() {

    String executionId = UUID.randomUUID().toString();

    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService)
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


  static class RuntimeServiceAwareService {
    private final RuntimeService runtimeService;

    RuntimeServiceAwareService(RuntimeService runtimeService) {
      this.runtimeService = runtimeService;
    }

    public String readLocalId(String execution) {
      return ORDER_ID.from(runtimeService, execution).getLocal();
    }

    public void writeLocalId(String execution, String value) {
      ORDER_ID.on(runtimeService, execution).setLocal(value);
    }

    public void writeFlag(String execution, Boolean value) {
      ORDER_FLAG.on(runtimeService, execution).set(value);
    }

    public Boolean readFlag(String execution) {
      return ORDER_FLAG.from(runtimeService, execution).get();
    }

    public Boolean flagExists(String execution) {
      return runtimeService.getVariables(execution).containsKey(ORDER_FLAG.getName());
    }
  }
}
