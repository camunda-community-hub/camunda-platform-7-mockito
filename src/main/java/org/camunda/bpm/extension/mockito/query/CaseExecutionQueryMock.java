package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseExecutionQuery;

public class CaseExecutionQueryMock extends AbstractQueryMock<CaseExecutionQueryMock, CaseExecutionQuery, CaseExecution, CaseService>{

  public CaseExecutionQueryMock() {
    super(CaseExecutionQuery.class, CaseService.class);
  }

}
