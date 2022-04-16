package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.history.HistoricCaseInstanceQuery;

public class HistoricCaseInstanceQueryMock extends AbstractQueryMock<HistoricCaseInstanceQueryMock, HistoricCaseInstanceQuery, HistoricCaseInstance, HistoryService> {

  public HistoricCaseInstanceQueryMock() {
    super(HistoricCaseInstanceQuery.class, HistoryService.class);
   }

}
