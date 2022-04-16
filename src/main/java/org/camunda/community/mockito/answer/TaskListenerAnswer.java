package org.camunda.community.mockito.answer;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/**
 * This is a specialized {@link org.mockito.stubbing.Answer} that delegates to
 * the given {@link org.camunda.bpm.engine.delegate.TaskListener}. When using an
 * TaskListener-Mock, this Answer can be used to implement internal behavior of
 * the mock by delegating the method call to the given delegate instance.
 *
 * @author Jan Galinski, Holisticon AG
 */
public class TaskListenerAnswer extends AbstractAnswer<DelegateTask> implements TaskListener {

  private final TaskListener taskListener;

  public TaskListenerAnswer(final TaskListener taskListener) {
    this.taskListener = taskListener;
  }

  @Override
  public void notify(final DelegateTask delegateTask) {
    taskListener.notify(delegateTask);
  }

  @Override
  protected void answer(final DelegateTask delegateTask) throws Exception {
    notify(delegateTask);
  }

}
