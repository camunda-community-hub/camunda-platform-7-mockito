package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.management.JobDefinition;
import org.camunda.bpm.engine.management.JobDefinitionQuery;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.JobQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class JobDefinitionQueryMock extends AbstractQueryMock<JobDefinitionQueryMock, JobDefinitionQuery, JobDefinition, ManagementService> {

  public JobDefinitionQueryMock() {
    super(JobDefinitionQuery.class, ManagementService.class);
  }

}
