package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class ExecutionQueryMock extends AbstractQueryMock<ExecutionQueryMock, ExecutionQuery, Execution, RuntimeService> {

  public ExecutionQueryMock() {
    super(ExecutionQuery.class, RuntimeService.class);
   }

}
