package org.camunda.bpm.extension.mockito.query;

import com.google.common.base.Supplier;
import org.camunda.bpm.engine.query.Query;
import org.camunda.bpm.extension.mockito.answer.FluentAnswer;
import org.camunda.bpm.extension.mockito.generator.annotation.GenerateQueryMocks;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Throwables.propagate;
import static org.mockito.Mockito.when;

/**
 * This looks more complicated than it actually is ... To easily mock the
 * behaviour of queries, all common functionality is extracted to this abstract
 * super class, the high level of Generics is needed for the fluent api pattern,
 * so the abstract mock implementing the generic Query interface must also keep
 * a reference to itself.
 *
 * @param <M>
 *          the type of the AbstractQueryMock (repeat the type of the class you
 *          are building). Used to "return this"
 * @param <Q>
 *          the type of the query to mock (for example: TaskQuery).
 * @param <R>
 *          the type of the expected result (for example: Execution).
 * @param <S>
 *          the type of the service the query belongs to (used for "forService"
 *          binding), for Example: TaskService.
 *
 * @author Jan Galinski
 */
@GenerateQueryMocks
abstract class AbstractQueryMock<M extends AbstractQueryMock<M, Q, R, S>, Q extends Query<?, R>, R extends Object, S> implements Supplier<Q> {

  /**
   * The internally stored query instance.
   */
  private final Q query;

  /**
   * Used for mocking the query creation via reflection.
   */
  private final Method createMethod;

  /**
   * Creates a new query mock and mocks fluent api behavior by adding a default
   * answer to the mock. Every createMethod will return the mock itself, except
   * <ul>
   * <li>list() - returns empty ArrayList</li>
   * <lI>singeResult() - returns null</lI>
   * </ul>
   *
   * @param queryType
   *          the type of the query to mock.
   * @param serviceType
   *          the type of service that generates this query
   */
  protected AbstractQueryMock(@Nonnull final Class<Q> queryType, @Nonnull final Class<S> serviceType) {
    query = FluentAnswer.createMock(queryType);
    createMethod = createMethod(queryType, serviceType);

    list(new ArrayList<R>());
    singleResult(null);
  }

  private Method createMethod(@Nonnull final Class<Q> queryType, @Nonnull Class<S> serviceType) {
    try {
      return serviceType.getDeclaredMethod("create" + queryType.getSimpleName());
    } catch (NoSuchMethodException e) {
      throw propagate(e);
    }
  }

  public Q list(final List<R> result) {
    when(query.list()).thenReturn(result);
    return get();
  }

  public Q singleResult(final R result) {
    when(query.singleResult()).thenReturn(result);
    return get();
  }

  public Q count(long result) {
    when(query.count()).thenReturn(result);
    return get();
  }

  public Q listPage(List<R> result, int min, int max) {
    when(query.listPage(min,max)).thenReturn(result);
    return get();
  }

  public M forService(S service) {
    try {
      when(createMethod.invoke(service)).thenReturn(get());
    } catch (Exception e) {
      propagate(e);
    }
    return (M) this;
  }

  @Override
  public final Q get() {
    return query;
  }
}
