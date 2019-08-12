package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

@Ignore
public class MessageCorrelationMockExample {

  private final RuntimeService runtimeService = mock(RuntimeService.class);
  private MessageCorrelationBuilder correlation;

  @Test
  public void mock_messageCorrelation() {

    // setup mock
    correlation = ProcessExpressions.mockMessageCorrelation(runtimeService, "MESSAGE_NAME");

    // execute correlation, e.g. in a class under test (service, delegate, whatever)
    runtimeService
      .createMessageCorrelation("MESSAGE_NAME")
      .processDefinitionId("some_process_id")
      .processInstanceBusinessKey("my-business-key")
      .setVariable("myVar1", "value-1")
      .correlate();

    // verify
    verify(correlation).correlate();
    verify(correlation).processDefinitionId("some_process_id");
    verify(correlation).processInstanceBusinessKey("my-business-key");
    verify(correlation).setVariable("myVar1", "value-1");

    verifyNoMoreInteractions(correlation);

  }
}
