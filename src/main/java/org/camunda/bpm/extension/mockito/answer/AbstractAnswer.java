package org.camunda.bpm.extension.mockito.answer;

import org.camunda.bpm.engine.delegate.VariableScope;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Specialized {@link org.mockito.stubbing.Answer} that takes the single argument of an execute() or
 * notify() method and delegates to type safe call.
 *
 * @param <T> can be {@link org.camunda.bpm.engine.delegate.DelegateExecution} or {@link org.camunda.bpm.engine.delegate.DelegateTask}
 */
abstract class AbstractAnswer<T extends VariableScope> implements Answer<Void> {

  @Override
  @SuppressWarnings("unchecked")
  public final Void answer(final InvocationOnMock invocation) throws Throwable {
    answer((T) invocation.getArguments()[0]);
    return null;
  }

  /**
   * Every implementing class must define what "answer" should actually do.
   *
   * @param parameter either DelegateTask or DelegateExecution.
   * @throws Exception when anything fails
   */
  protected abstract void answer(T parameter) throws Exception;

}
