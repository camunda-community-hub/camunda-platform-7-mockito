package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.mockito.process.ProcessMock;
import org.camunda.bpm.extension.mockito.process.SubProcessMock;

public class ProcessExpressions {

  private ProcessExpressions() {
  }

  public static ProcessMock registerSubProcessMock(String processId) {
    return new ProcessMock(processId);
  }

  public static ProcessMock registerEmbeddedSubProcessMock(final ProcessEngineRule rule, final String processDefinitionKey, final String subProcessModelId) {
    return new SubProcessMock(rule, processDefinitionKey, subProcessModelId);
  }
}
