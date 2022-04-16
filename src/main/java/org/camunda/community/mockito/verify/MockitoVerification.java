package org.camunda.community.mockito.verify;

import org.mockito.ArgumentCaptor;
import org.mockito.verification.VerificationMode;

public interface MockitoVerification<P> {

  ArgumentCaptor<P> executed();

  ArgumentCaptor<P> executedNever();

  ArgumentCaptor<P> executed(VerificationMode verificationMode);
}
