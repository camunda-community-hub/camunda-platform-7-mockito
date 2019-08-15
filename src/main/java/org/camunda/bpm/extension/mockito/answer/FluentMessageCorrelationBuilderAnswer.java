package org.camunda.bpm.extension.mockito.answer;

import org.camunda.bpm.engine.query.Query;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.annotation.Nonnull;

/**
 * A fluent answer always returns the mock instance itself for all methods that
 * have the same return type as the mock instance. This makes it possible to
 * easily mock fluent-api behaviour without chaining the when/then stubbings or
 * relying on deep stubs.
 */
public final class FluentMessageCorrelationBuilderAnswer implements Answer<MessageCorrelationBuilder> {

  /**
   * Create builder mock with fluent answer.
   *
   * @return fluent mock.
   */
  public static MessageCorrelationBuilder createMock() {
    return Mockito.mock(MessageCorrelationBuilder.class, new FluentMessageCorrelationBuilderAnswer());
  }

  /**
   * Returns the mock itself if return type and mock type match (assume fluent
   * api).
   *
   * @param invocation the method invocation. If its return type equals the mock type,
   *                   just return the mock.
   *
   * @return the mock itself or null (meaning further stubbing is required).
   *
   * @throws Throwable when anything fails
   */
  @Override
  @SuppressWarnings("unchecked")
  public MessageCorrelationBuilder answer(@Nonnull final InvocationOnMock invocation) throws Throwable {
    final Class<?> methodReturnType = invocation.getMethod().getReturnType();
    if (MessageCorrelationBuilder.class.isAssignableFrom(methodReturnType)) {
      // fluent api methods
      Object mock = invocation.getMock();
      return (MessageCorrelationBuilder) mock;
    }
    return null;
  }
}
