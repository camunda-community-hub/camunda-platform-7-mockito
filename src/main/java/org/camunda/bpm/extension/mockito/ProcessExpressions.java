package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.extension.mockito.process.CallActivityMock;

public final class ProcessExpressions {

  private ProcessExpressions() {
    // util class, do not instantiate
  }

  public static CallActivityMock registerCallActivityMock(final String processDefinitionKey) {
    return new CallActivityMock(processDefinitionKey);
  }
}
