package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricCaseActivityInstance;
import org.camunda.bpm.engine.history.HistoricCaseActivityInstanceQuery;

public class HistoricCaseActivityInstanceQueryMock extends AbstractQueryMock<HistoricCaseActivityInstanceQueryMock, HistoricCaseActivityInstanceQuery, HistoricCaseActivityInstance, HistoryService>{

  public HistoricCaseActivityInstanceQueryMock() {
    super(HistoricCaseActivityInstanceQuery.class, HistoryService.class);
  }

}
