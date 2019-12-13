package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;

public class ExecutionQueryMock extends AbstractQueryMock<ExecutionQueryMock, ExecutionQuery, Execution, RuntimeService> {

  public ExecutionQueryMock() {
    super(ExecutionQuery.class, RuntimeService.class);
   }

}
