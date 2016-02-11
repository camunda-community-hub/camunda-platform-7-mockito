package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricActivityStatistics;
import org.camunda.bpm.engine.history.HistoricActivityStatisticsQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class HistoricActivityStatisticsQueryMock extends
    AbstractQueryMock<HistoricActivityStatisticsQueryMock, HistoricActivityStatisticsQuery, HistoricActivityStatistics, HistoryService> {

  public HistoricActivityStatisticsQueryMock() {
    super(HistoricActivityStatisticsQuery.class, HistoryService.class);
  }

}
