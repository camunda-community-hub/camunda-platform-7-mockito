package org.camunda.bpm.extension.mockito.spring;

import static org.assertj.core.api.Assertions.fail;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public abstract class SpringListeners implements ExecutionListener {

  @Override
  public void notify(DelegateExecution delegateExecution) throws Exception {
    fail(this.getClass().getSimpleName() + ": not implemented!");
  }

  @Component
  public static class SpringComponentListener extends SpringListeners {};

  @Component("namedComponent")
  public static class SpringNamedComponentListener extends SpringListeners {};

  @Service
  public static class SpringServiceListener extends SpringListeners {};

  @Service("namedService")
  public static class SpringNamedServiceListener extends SpringListeners {};

}
