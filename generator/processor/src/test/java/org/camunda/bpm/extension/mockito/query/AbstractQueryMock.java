package org.camunda.bpm.extension.mockito.query;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Supplier;
import org.camunda.bpm.engine.query.Query;
import org.mockito.Mockito;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Throwables.propagate;
import static org.mockito.Mockito.when;

// only needed so tests can compile generated queryMocks.
@VisibleForTesting
@SuppressWarnings("all")
abstract class AbstractQueryMock<M extends AbstractQueryMock<M, Q, R, S>, Q extends Query<?, R>, R extends Object, S> implements Supplier<Q> {

  protected AbstractQueryMock(@Nonnull final Class<Q> queryType, @Nonnull final Class<S> serviceType) {
  }

  @Override
  public Q get() {
    return null;
  }

  public M forService(S service) { return (M) this;}
}
