package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public class SubProcessMock extends ProcessMock {

  private final ProcessEngineRule rule;
  private final String processDefinitionKey;

  public SubProcessMock(final ProcessEngineRule rule, final String processDefinitionKey, final String subProcessModelId) {
    super(processDefinitionKey);
    this.rule = rule;
    this.processDefinitionKey = processDefinitionKey;

    this.extendGivenProcess(subProcessModelId);
  }

  private void extendGivenProcess(String subProcessModelId) {
    final ProcessDefinition processDefinition = this.rule.getRepositoryService()
      .createProcessDefinitionQuery()
      .processDefinitionKey(processDefinitionKey)
      .latestVersion().singleResult();

    final BpmnModelInstance bpmnModelInstance = this.rule.getRepositoryService()
      .getBpmnModelInstance(processDefinition.getId());

    System.out.println(Bpmn.convertToString(bpmnModelInstance));

    flowNodeBuilder = bpmnModelInstance.getModelElementById(subProcessModelId);


   // bpmnModelInstance.
  }

}
