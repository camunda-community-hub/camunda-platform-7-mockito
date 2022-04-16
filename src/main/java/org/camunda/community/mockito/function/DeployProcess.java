package org.camunda.community.mockito.function;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.function.BiFunction;

public class DeployProcess implements BiFunction<String, BpmnModelInstance, Deployment> {

  private final RepositoryService repositoryService;

  public DeployProcess(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  public DeployProcess(ProcessEngineServices processEngineServices) {
    this(processEngineServices.getRepositoryService());
  }


  public Deployment apply(String processId, BpmnModelInstance instance) {
    return repositoryService.createDeployment()
      .addModelInstance(processId + ".bpmn", instance)
      .deploy();
  }
}
