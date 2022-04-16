package org.camunda.community.mockito;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.community.mockito.message.MessageCorrelationBuilderMock;
import org.camunda.community.mockito.process.CallActivityMock;

import static org.mockito.ArgumentMatchers.any;

public enum ProcessExpressions {
  ;

  /**
   * Registers a call activity mock for the given process definition key.
   * @param processDefinitionKey process definition key of the called process
   * @param mockedModelConfigurer configurer for adjusting the attributes of the mocked model
   * @return A mock for the called process (its behaviour should be configured via further calls)
   */
  public static CallActivityMock registerCallActivityMock(final String processDefinitionKey,
                                                          CallActivityMock.MockedModelConfigurer mockedModelConfigurer) {
    return new CallActivityMock(processDefinitionKey, mockedModelConfigurer);
  }

  /**
   * Registers a call activity mock for the given process definition key (without the possibility
   * to adjust the properties of the mocked model).
   *
   * @param processDefinitionKey process definition key of the called process
   * @return A mock for the called process (its behaviour should be configured via further calls)
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
