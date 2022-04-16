package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;

public class HistoricProcessInstanceQueryMock extends AbstractQueryMock<HistoricProcessInstanceQueryMock, HistoricProcessInstanceQuery, HistoricProcessInstance, HistoryService> {

  public HistoricProcessInstanceQueryMock() {
    super(HistoricProcessInstanceQuery.class, HistoryService.class);
   }

}
