package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricActivityStatistics;
import org.camunda.bpm.engine.history.HistoricActivityStatisticsQuery;

public class HistoricActivityStatisticsQueryMock extends AbstractQueryMock<HistoricActivityStatisticsQueryMock, HistoricActivityStatisticsQuery, HistoricActivityStatistics, HistoryService> {

  public HistoricActivityStatisticsQueryMock() {
    super(HistoricActivityStatisticsQuery.class, HistoryService.class);
   }

}
