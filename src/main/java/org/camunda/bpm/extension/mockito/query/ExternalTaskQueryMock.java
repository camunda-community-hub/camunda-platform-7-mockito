package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.externaltask.ExternalTaskQuery;

public class ExternalTaskQueryMock extends AbstractQueryMock<ExternalTaskQueryMock, ExternalTaskQuery, ExternalTask, ExternalTaskService> {

  public ExternalTaskQueryMock() {
    super(ExternalTaskQuery.class, ExternalTaskService.class);
   }

}
