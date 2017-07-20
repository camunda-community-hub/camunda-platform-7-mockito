package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.extension.mockito.process.ProcessMock;

public class ProcessExpressions {

  private ProcessExpressions() {
  }

  public static ProcessMock registerSubProcessMock(String processId) {
    return new ProcessMock(processId);
  }
}
