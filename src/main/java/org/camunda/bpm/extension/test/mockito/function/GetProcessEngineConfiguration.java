package org.camunda.bpm.extension.test.mockito.function;

import com.google.common.base.Function;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Hides the nasty "getConfiguration from given Engine Hack" in an easy to use
 * function.
 */
public enum GetProcessEngineConfiguration implements Function<ProcessEngine, ProcessEngineConfiguration> {
  INSTANCE;

  @Override
  public ProcessEngineConfiguration apply(final ProcessEngine processEngine) {
    checkArgument(processEngine instanceof ProcessEngineImpl, "processEngine must not be null and of type ProcessEngineImpl!");

    return ((ProcessEngineImpl) processEngine).getProcessEngineConfiguration();
  }

}
