package org.camunda.bpm.extension.test.mockito.query;

import com.google.common.base.Supplier;
import org.camunda.bpm.engine.query.Query;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <M> the type of the AbstractQueryMock (repeat the type of the class you are building). Used to "return this"
 * @param <Q> the type of the query to mock (for example: TaskQuery).
 * @param <R> the type of the expected result (for example: Execution).
 * @param <S> the type of the service the query belongs to (used for "forService" binding), for Example: TaskService.
 *
 * @author Jan Galinski
 */
abstract class AbstractQueryMock<M extends AbstractQueryMock<M, Q, R, S>, Q extends Query<?, R>, R extends Object, S> implements Supplier<Q> {

  private final Q query;

  /**
   * Creates a new query mock and mocks fluent api behavior by adding a default answer to the mock.
   * Every method will return the mock itself, except
   * <ul>
   * <li>list() - returns empty ArrayList</li>
   * <lI>singeResult() - returns null</lI>
   * </ul>
   *
   * @param queryType the type of the query to mock.
   */
  protected AbstractQueryMock(final Class<Q> queryType) {
    query = Mockito.mock(queryType, new Answer<Q>() {

      @Override
      public Q answer(InvocationOnMock invocation) throws Throwable {
        if (queryType.equals(invocation.getMethod().getReturnType())) {
          return (Q) invocation.getMock();

        }
        return null;
      }
    });

    Mockito.when(query.list()).thenReturn(new ArrayList<R>());
    Mockito.when(query.singleResult()).thenReturn(null);
  }

  public final Q list(List<R> result) {
    Mockito.when(query.list()).thenReturn(result);
    return get();
  }

  public final Q singleResult(R result) {
    Mockito.when(query.singleResult()).thenReturn(result);
    return get();
  }

  public abstract M forService(S service);

  @Override
  public final Q get() {
    return query;
  }
}
