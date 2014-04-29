package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.ProcessDefinitionStatistics;
import org.camunda.bpm.engine.management.ProcessDefinitionStatisticsQuery;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.JobQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class ProcessDefinitionStatisticsQueryMock extends AbstractQueryMock<ProcessDefinitionStatisticsQueryMock, ProcessDefinitionStatisticsQuery, ProcessDefinitionStatistics, ManagementService> {

  public ProcessDefinitionStatisticsQueryMock() {
    super(ProcessDefinitionStatisticsQuery.class, ManagementService.class);
  }

}
