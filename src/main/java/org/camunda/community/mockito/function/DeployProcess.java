package org.camunda.community.mockito.function;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.Arrays;
import java.util.function.BiFunction;

public class DeployProcess implements BiFunction<String, BpmnModelInstance, Deployment> {

  public interface BpmnModelInstanceResource {
    String getResourceName();
    BpmnModelInstance getModelInstance();

    default DeploymentBuilder addToDeployment(DeploymentBuilder builder) {
      builder.addModelInstance(getResourceName(), getModelInstance());
      return builder;
    }
  }

  private final RepositoryService repositoryService;

  public DeployProcess(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  public DeployProcess(ProcessEngineServices processEngineServices) {
    this(processEngineServices.getRepositoryService());
  }

  public Deployment apply(BpmnModelInstanceResource... modelInstanceResources) {
    final DeploymentBuilder builder = repositoryService.createDeployment();
    Arrays.stream(modelInstanceResources).forEach(r -> r.addToDeployment(builder));
    return builder.deploy();
  }


  public Deployment apply(String processId, BpmnModelInstance instance) {
    return apply(new BpmnModelInstanceResource() {
      @Override
      public String getResourceName() {
        return processId + ".bpmn";
      }

      @Override
      public BpmnModelInstance getModelInstance() {
        return instance;
      }
    });
  }
}
