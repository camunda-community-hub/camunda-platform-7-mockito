package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.extension.mockito.message.MessageCorrelationBuilderMock;
import org.camunda.bpm.extension.mockito.process.CallActivityMock;

import static org.mockito.ArgumentMatchers.any;

public enum ProcessExpressions {
  ;

  /**
   * Registers a call activity mock for given a process definition key.
   * @param processDefinitionKey process definition.
   * @return a mock.
   */
  public static CallActivityMock registerCallActivityMock(final String processDefinitionKey) {
    return new CallActivityMock(processDefinitionKey);
  }

  /**
   * Registers the mock for message correlation.
   *
   * @param serviceMock runtime service mock.
   * @param messageName name of the message to mock message correlation for.
   *
   * @return mocked builder
   */
  public static MessageCorrelationBuilder mockMessageCorrelation(final RuntimeService serviceMock, final String messageName) {
    return new MessageCorrelationBuilderMock().forServiceAndMessage(serviceMock, messageName).get();
  }
  /**
   * Registers the mock for message correlation.
   *
   * @param serviceMock runtime service mock.
   *
   * @return mocked builder
   */
  public static MessageCorrelationBuilder mockMessageCorrelation(final RuntimeService serviceMock) {
    return mockMessageCorrelation(serviceMock, any());
  }
}
