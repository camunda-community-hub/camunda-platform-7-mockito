package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.batch.BatchStatistics;
import org.camunda.bpm.engine.batch.BatchStatisticsQuery;

public class BatchStatisticsQueryMock extends AbstractQueryMock<BatchStatisticsQueryMock, BatchStatisticsQuery, BatchStatistics, ManagementService> {

  public BatchStatisticsQueryMock() {
    super(BatchStatisticsQuery.class, ManagementService.class);
   }

}
