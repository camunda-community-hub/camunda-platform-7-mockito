package org.camunda.bpm.extension.mockito.function;

import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

/**
 * @deprecated to avoid dependency to 4.12 this will be removed/altered with 4.10
 */
@Deprecated
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
