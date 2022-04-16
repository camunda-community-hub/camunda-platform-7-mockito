package org.camunda.community.mockito.answer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/**
 * This is a specialized {@link org.mockito.stubbing.Answer} that delegates to
 * the given {@link org.camunda.bpm.engine.delegate.ExecutionListener}. When
 * using an ExecutionListener-Mock, this Answer can be used to implement
 * internal behavior of the mock by delegating the method call to the given
 * delegate instance.
 *
 * @author Jan Galinski, Holisticon AG
 */
public class ExecutionListenerAnswer extends AbstractAnswer<DelegateExecution> implements ExecutionListener {

  private final ExecutionListener executionListener;

  public ExecutionListenerAnswer(final ExecutionListener executionListener) {
    this.executionListener = executionListener;
  }

  @Override
  public void notify(final DelegateExecution execution) throws Exception {
    executionListener.notify(execution);
  }

  @Override
  protected void answer(final DelegateExecution parameter) throws Exception {
    notify(parameter);
  }

}
