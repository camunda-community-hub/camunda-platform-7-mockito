package org.camunda.bpm.extension.test.mockito.answer;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.annotation.Nonnull;

/**
 * @param <T> the return type of the answer
 */
public class FluentAnswer<T> implements Answer<T> {

  /**
   *
   * @param type type of mock
   * @param <T> generic parameter for type of mock
   * @return new mock instance of type T with default fluent answer.
   */
  public static <T> T createMock(Class<T> type) {
    return Mockito.mock(type, createAnswer(type));
  }

  /**
   *
   * @param type the return type of the answer
   * @param <T> generic parameter of the return type
   * @return new Answer that returns the mock itself
   */
  public static <T> FluentAnswer<T> createAnswer(Class<T> type) {
    return new FluentAnswer<T>(type);
  }

  private final Class<T> type;

  private FluentAnswer(Class<T> type) {
    this.type = type;
  }

  @Override
  public T answer(@Nonnull final InvocationOnMock invocation) throws Throwable {
    if (type.equals(invocation.getMethod().getReturnType())) {
      return (T) invocation.getMock();
    }
    return null;
  }
}
