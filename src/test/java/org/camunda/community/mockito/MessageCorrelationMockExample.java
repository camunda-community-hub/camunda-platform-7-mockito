package org.camunda.community.mockito;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MessageCorrelationMockExample {


  @Test
  public void mock_messageCorrelation() {

    // setup mock
    final RuntimeService runtimeService = mock(RuntimeService.class);
    final MessageCorrelationBuilder correlation = ProcessExpressions.mockMessageCorrelation(runtimeService, "MESSAGE_NAME");
    final MyCorrelator serviceUnderTest = new MyCorrelator(runtimeService, "my-business-key", "value-1");

    // execute correlation, e.g. in a class under test (service, delegate, whatever)
    serviceUnderTest.correlate();

    // verify
    verify(correlation).correlate();
    verify(correlation).processDefinitionId("some_process_id");
    verify(correlation).processInstanceBusinessKey("my-business-key");
    verify(correlation).setVariable("myVar1", "value-1");

    verify(runtimeService).createMessageCorrelation("MESSAGE_NAME");

    verifyNoMoreInteractions(correlation);
    verifyNoMoreInteractions(runtimeService);
  }

  static class MyCorrelator {

    private final RuntimeService runtimeService;
    private final String value;
    private final String businessKey;

    MyCorrelator(RuntimeService runtimeService, String businessKey, String value) {
      this.runtimeService = runtimeService;
      this.value = value;
      this.businessKey = businessKey;
    }

    void correlate() {
      this.runtimeService
        .createMessageCorrelation("MESSAGE_NAME")
        .processDefinitionId("some_process_id")
        .processInstanceBusinessKey(businessKey)
        .setVariable("myVar1", value)
        .correlate();
    }
  }
}
