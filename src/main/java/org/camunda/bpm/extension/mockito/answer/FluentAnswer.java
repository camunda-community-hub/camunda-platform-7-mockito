package org.camunda.bpm.extension.mockito.answer;

import javax.annotation.Nonnull;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * A fluent answer always returns the mock instance itself for all methods that have the same return type as
 * the mock instance.
 * This makes it possible to easily mock fluent-api behaviour without chaining the when/then stubbings or relying on
 * deep stubs.
 *
 * @param <T> the return type of the answer
 */
public final class FluentAnswer<T> implements Answer<T> {

  /**
   * Creates a new mock of given type with a fluent default answer already set up.
   *
   * @param type type of mock
   * @param <T> generic parameter for type of mock
   * @return new mock instance of type T with default fluent answer.
   */
  public static <T> T createMock(Class<T> type) {
    return Mockito.mock(type, createAnswer(type));
  }

  /**
   * Creates a new instance for the given type.
   *
   * @param type the return type of the answer
   * @param <T> generic parameter of the return type
   * @return new Answer that returns the mock itself
   */
  public static <T> FluentAnswer<T> createAnswer(Class<T> type) {
    return new FluentAnswer<T>(type);
  }

  /**
   * Holds the internal type.
   */
  private final Class<T> type;

  /**
   * Private constructor, use static factory methods to create.
   *
   * @param type the type of the return value for the answer
   */
  private FluentAnswer(Class<T> type) {
    this.type = type;
  }

  /**
   * Returns the mock itself if return type and mock type match (assume fluent api).
   *
   * @param invocation the method invocation. If its return type equals the mock type, just return the mock.
   * @return the mock itself or null (meaning further stubbing is required).
   * @throws Throwable when anything fails
   */
  @Override
  public T answer(@Nonnull final InvocationOnMock invocation) throws Throwable {
    if (type.equals(invocation.getMethod().getReturnType())) {
      return (T) invocation.getMock();
    }
    return null;
  }
}
