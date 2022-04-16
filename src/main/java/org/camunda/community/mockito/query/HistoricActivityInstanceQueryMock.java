package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricActivityInstanceQuery;

public class HistoricActivityInstanceQueryMock extends AbstractQueryMock<HistoricActivityInstanceQueryMock, HistoricActivityInstanceQuery, HistoricActivityInstance, HistoryService> {

  public HistoricActivityInstanceQueryMock() {
    super(HistoricActivityInstanceQuery.class, HistoryService.class);
   }

}
