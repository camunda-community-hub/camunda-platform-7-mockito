package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.DeploymentStatistics;
import org.camunda.bpm.engine.management.DeploymentStatisticsQuery;

public class DeploymentStatisticsQueryMock extends AbstractQueryMock<DeploymentStatisticsQueryMock, DeploymentStatisticsQuery, DeploymentStatistics, ManagementService> {

  public DeploymentStatisticsQueryMock() {
    super(DeploymentStatisticsQuery.class, ManagementService.class);
   }

}
