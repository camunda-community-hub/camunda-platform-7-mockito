package org.camunda.bpm.extension.mockito.function;

import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public enum DeployProcess {

  INSTANCE;

  public Deployment apply(ProcessEngineRule rule, BpmnModelInstance instance, String processId) {
    final Deployment deployment = rule.getRepositoryService().createDeployment()
      .addModelInstance(processId + ".bpmn", instance)
      .deploy();
    rule.manageDeployment(deployment);
    return deployment;
  }
}
