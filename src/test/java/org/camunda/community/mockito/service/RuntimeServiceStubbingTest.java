package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.community.mockito.ServiceExpressions;
import org.camunda.community.mockito.verify.RuntimeServiceVerification;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.booleanVariable;
import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

/**
 * Test to for runtime service variable access.
 */
public class RuntimeServiceStubbingTest {
  private static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  private static final VariableFactory<Boolean> ORDER_FLAG = booleanVariable("orderFlag");


  @Test
  public void stubs_variable_access() {

    final RuntimeService runtimeService = mock(RuntimeService.class);
    final RuntimeServiceAwareService serviceUnderTest = new RuntimeServiceAwareService(runtimeService);

    String executionId = UUID.randomUUID().toString();

    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService)
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

  @Test
  public void verify_variable_access() {

    // setup mock
    final RuntimeService runtimeService = mock(RuntimeService.class);
    final RuntimeServiceAwareService serviceUnderTest = new RuntimeServiceAwareService(runtimeService);
    final RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);

    String executionId = UUID.randomUUID().toString();
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService)
                      .defineAndInitializeLocal(ORDER_ID, "initial-Value")
                      .define(ORDER_FLAG)
                      .build();


    // execute service calls and check results
    serviceUnderTest.writeLocalId(executionId, "4712");
    String orderId = serviceUnderTest.readLocalId(executionId);
    assertThat(orderId).isEqualTo("4712");

    assertThat(serviceUnderTest.flagExists(executionId)).isFalse();
    serviceUnderTest.writeFlag(executionId, true);
    assertThat(serviceUnderTest.flagExists(executionId)).isTrue();
    Boolean orderFlag = serviceUnderTest.readFlag(executionId);
    assertThat(orderFlag).isEqualTo(true);

    // verify service access
    verifier.verifySetLocal(ORDER_ID, "4712", executionId );
    verifier.verifyGetLocal(ORDER_ID, executionId);
    verifier.verifyGetVariables(executionId, times(2));
    verifier.verifySet(ORDER_FLAG, true, executionId);
    verifier.verifyGet(ORDER_FLAG, executionId);
    verifier.verifyNoMoreInteractions();

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
