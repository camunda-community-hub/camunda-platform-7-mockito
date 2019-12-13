package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.ActivityStatistics;
import org.camunda.bpm.engine.management.ActivityStatisticsQuery;

public class ActivityStatisticsQueryMock extends AbstractQueryMock<ActivityStatisticsQueryMock, ActivityStatisticsQuery, ActivityStatistics, ManagementService> {

  public ActivityStatisticsQueryMock() {
    super(ActivityStatisticsQuery.class, ManagementService.class);
   }

}
