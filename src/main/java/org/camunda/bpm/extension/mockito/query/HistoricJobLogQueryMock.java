package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricJobLog;
import org.camunda.bpm.engine.history.HistoricJobLogQuery;

public class HistoricJobLogQueryMock extends AbstractQueryMock<HistoricJobLogQueryMock, HistoricJobLogQuery, HistoricJobLog, HistoryService> {

  public HistoricJobLogQueryMock() {
    super(HistoricJobLogQuery.class, HistoryService.class);
   }

}
