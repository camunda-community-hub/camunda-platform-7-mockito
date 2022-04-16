package org.camunda.community.mockito.answer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * This is a specialized {@link org.mockito.stubbing.Answer} that delegates to
 * the given {@link org.camunda.bpm.engine.delegate.JavaDelegate}. When using an
 * JavaDelegate-Mock, this Answer can be used to implement internal behavior of
 * the mock by delegating the method call to the given delegate instance.
 *
 * @author Jan Galinski, Holisticon AG
 */
public class JavaDelegateAnswer extends AbstractAnswer<DelegateExecution> implements JavaDelegate {

  private final JavaDelegate javaDelegate;

  public JavaDelegateAnswer(final JavaDelegate javaDelegate) {
    this.javaDelegate = javaDelegate;
  }

  @Override
  public void execute(final DelegateExecution execution) throws Exception {
    javaDelegate.execute(execution);
  }

  @Override
  protected void answer(final DelegateExecution delegateExecution) throws Exception {
    execute(delegateExecution);
  }

}
