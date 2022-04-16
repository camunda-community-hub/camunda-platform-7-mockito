package org.camunda.community.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.ProcessEngineServicesAware;

public class ProcessEngineServicesAwareFake implements ProcessEngineServicesAware {

  private ProcessEngine processEngine;
  private ProcessEngineServices processEngineServices;

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    return processEngineServices;
  }

  public ProcessEngineServicesAwareFake withProcessEngineServices(ProcessEngineServices processEngineServices) {
    this.processEngineServices = processEngineServices;
    return this;
  }

  @Override
  public ProcessEngine getProcessEngine() {
    return processEngine;
  }

  public ProcessEngineServicesAwareFake withProcessEngine(ProcessEngine processEngine) {
    this.processEngine = processEngine;
    return withProcessEngineServices(processEngine);
  }

}
