package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;

public class HistoricTaskInstanceQueryMock extends AbstractQueryMock<HistoricTaskInstanceQueryMock, HistoricTaskInstanceQuery, HistoricTaskInstance, HistoryService> {

  public HistoricTaskInstanceQueryMock() {
    super(HistoricTaskInstanceQuery.class, HistoryService.class);
   }

}
