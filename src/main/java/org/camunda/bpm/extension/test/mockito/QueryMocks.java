package org.camunda.bpm.extension.test.mockito;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.extension.test.mockito.query.TaskQueryMock;

/**
 * Central provider for all mocked Queries.
 *
 * @author Jan Galinski, Holisticon AG
 */
public final class QueryMocks {

  /**
   * This is a util class, do not instantiate!
   */
  private QueryMocks() {
    // empty
  }

  public static TaskQueryMock mockTaskQuery(final TaskService taskServiceMock) {
    return new TaskQueryMock().forService(taskServiceMock);
  }

}
