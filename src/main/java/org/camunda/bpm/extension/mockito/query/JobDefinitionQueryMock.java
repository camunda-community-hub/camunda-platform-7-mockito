package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.JobDefinition;
import org.camunda.bpm.engine.management.JobDefinitionQuery;

public class JobDefinitionQueryMock extends AbstractQueryMock<JobDefinitionQueryMock, JobDefinitionQuery, JobDefinition, ManagementService> {

  public JobDefinitionQueryMock() {
    super(JobDefinitionQuery.class, ManagementService.class);
   }

}
