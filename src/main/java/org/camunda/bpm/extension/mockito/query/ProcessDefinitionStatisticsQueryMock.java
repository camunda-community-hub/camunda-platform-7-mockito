package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.ProcessDefinitionStatistics;
import org.camunda.bpm.engine.management.ProcessDefinitionStatisticsQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class ProcessDefinitionStatisticsQueryMock extends AbstractQueryMock<ProcessDefinitionStatisticsQueryMock, ProcessDefinitionStatisticsQuery, ProcessDefinitionStatistics, ManagementService> {

  public ProcessDefinitionStatisticsQueryMock() {
    super(ProcessDefinitionStatisticsQuery.class, ManagementService.class);
  }

}
