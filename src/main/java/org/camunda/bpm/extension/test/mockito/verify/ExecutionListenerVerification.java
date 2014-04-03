package org.camunda.bpm.extension.test.mockito.verify;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.verify;

public class ExecutionListenerVerification extends AbstractMockitoVerification<ExecutionListener, DelegateExecution> {

  public ExecutionListenerVerification(final ExecutionListener mock) {
    super(mock, DelegateExecution.class);
  }

  @Override
  protected void doVerify(final VerificationMode verificationMode) throws Exception {
    verify(mock, verificationMode).notify(argumentCaptor.capture());
  }

}
