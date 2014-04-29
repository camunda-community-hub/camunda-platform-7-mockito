package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.DeploymentStatistics;
import org.camunda.bpm.engine.management.DeploymentStatisticsQuery;
import org.camunda.bpm.engine.management.ProcessDefinitionStatistics;
import org.camunda.bpm.engine.management.ProcessDefinitionStatisticsQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class DeploymentStatisticsQueryMock extends AbstractQueryMock<DeploymentStatisticsQueryMock, DeploymentStatisticsQuery, DeploymentStatistics, ManagementService> {

  public DeploymentStatisticsQueryMock() {
    super(DeploymentStatisticsQuery.class, ManagementService.class);
  }

}
