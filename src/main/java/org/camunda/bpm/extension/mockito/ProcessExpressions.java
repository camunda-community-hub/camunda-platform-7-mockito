package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.extension.mockito.process.CallActivityMock;

public enum ProcessExpressions {
  ;

  public static CallActivityMock registerCallActivityMock(final String processDefinitionKey) {
    return new CallActivityMock(processDefinitionKey);
  }
}
