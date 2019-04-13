package org.camunda.bpm.extension.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.ProcessEngineServicesAware;

abstract class DelegateFake<T extends DelegateFake> extends VariableScopeFake<T> implements ProcessEngineServicesAware {

  protected final ProcessEngineServicesAwareFake processEngineServicesAwareFake = new ProcessEngineServicesAwareFake();

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    return processEngineServicesAwareFake.getProcessEngineServices();
  }

  public T withProcessEngineServices(ProcessEngineServices processEngineServices) {
    processEngineServicesAwareFake.withProcessEngineServices(processEngineServices);
    return (T) this;
  }


  @Override
  public ProcessEngine getProcessEngine() {
    return processEngineServicesAwareFake.getProcessEngine();
  }

  public T withProcessEngine(ProcessEngine processEngine) {
    processEngineServicesAwareFake.withProcessEngine(processEngine);
    return (T) this;
  }

}
