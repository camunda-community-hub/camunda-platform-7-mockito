package org.camunda.bpm.extension.test.mockito.answer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

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
