package org.camunda.bpm.extension.test.mockito.verify;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.verify;

public class JavaDelegateVerification extends AbstractMockitoVerification<JavaDelegate, DelegateExecution> {

  public JavaDelegateVerification(final JavaDelegate mock) {
    super(mock, DelegateExecution.class);
  }

  @Override
  protected void doVerify(final VerificationMode verificationMode) throws Exception {
    verify(mock, verificationMode).execute(argumentCaptor.capture());
  }

}
