package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.history.UserOperationLogQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class UserOperationLogQueryMock extends AbstractQueryMock<UserOperationLogQueryMock, UserOperationLogQuery, UserOperationLogEntry, HistoryService> {

  public UserOperationLogQueryMock() {
    super(UserOperationLogQuery.class, HistoryService.class);
   }

}
