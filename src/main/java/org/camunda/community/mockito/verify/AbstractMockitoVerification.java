package org.camunda.community.mockito.verify;

import static org.mockito.Mockito.never;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

/**
 * Base implementation of {@link MockitoVerification}.
 *
 * @param <M>
 *          type of the mock Delegate
 * @param <P>
 *          type of the simple argument the verified method takes
 */
public abstract class AbstractMockitoVerification<M, P> implements MockitoVerification<P> {

  protected final M mock;
  protected final ArgumentCaptor<P> argumentCaptor;

  /**
   * Create new instance.
   *
   * @param mock
   *          the wrapped mock (has to be either
   *          {@link org.camunda.bpm.engine.delegate.JavaDelegate},
   *          {@link org.camunda.bpm.engine.delegate.TaskListener} or
   *          {@link org.camunda.bpm.engine.delegate.ExecutionListener}.
   * @param parameterType
   *          the parameter the main method (execute() or notify() expects, one
   *          of either
   *          {@link org.camunda.bpm.engine.delegate.DelegateExecution} or
   *          {@link org.camunda.bpm.engine.delegate.DelegateTask}.
   */
  public AbstractMockitoVerification(final M mock, final Class<P> parameterType) {
    this.mock = mock;
    this.argumentCaptor = ArgumentCaptor.forClass(parameterType);
  }

  @Override
  public final ArgumentCaptor<P> executed() {
    return this.executed(Mockito.times(1));
  }

  @Override
  public final ArgumentCaptor<P> executed(final VerificationMode verificationMode) {
    try {
      doVerify(verificationMode);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
    return argumentCaptor;
  }

  @Override
  public ArgumentCaptor<P> executedNever() {
    return executed(never());
  }

  /**
   * The concrete implementation must implement the method call
   * (verify(mock).METHOD_CALL(argumentCaptor.capture)).
   *
   * @param verificationMode
   *          defaults to times(1), but any
   *          {@link org.mockito.verification.VerificationMode} can be used.
   * @throws Exception
   *           just in case the METHOD_CALL throws an exception. If caught, it
   *           is propagated unchecked.
   */
  protected abstract void doVerify(VerificationMode verificationMode) throws Exception;

}
