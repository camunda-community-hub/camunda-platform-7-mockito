package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.history.UserOperationLogQuery;

public class UserOperationLogQueryMock extends AbstractQueryMock<UserOperationLogQueryMock, UserOperationLogQuery, UserOperationLogEntry, HistoryService> {

  public UserOperationLogQueryMock() {
    super(UserOperationLogQuery.class, HistoryService.class);
   }

}
