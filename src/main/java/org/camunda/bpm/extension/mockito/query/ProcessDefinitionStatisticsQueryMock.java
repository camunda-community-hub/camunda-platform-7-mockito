package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.ProcessDefinitionStatistics;
import org.camunda.bpm.engine.management.ProcessDefinitionStatisticsQuery;

public class ProcessDefinitionStatisticsQueryMock extends AbstractQueryMock<ProcessDefinitionStatisticsQueryMock, ProcessDefinitionStatisticsQuery, ProcessDefinitionStatistics, ManagementService> {

  public ProcessDefinitionStatisticsQueryMock() {
    super(ProcessDefinitionStatisticsQuery.class, ManagementService.class);
   }

}
