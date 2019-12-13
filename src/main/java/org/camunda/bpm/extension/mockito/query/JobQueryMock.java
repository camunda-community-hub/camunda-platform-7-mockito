package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.JobQuery;

public class JobQueryMock extends AbstractQueryMock<JobQueryMock, JobQuery, Job, ManagementService> {

  public JobQueryMock() {
    super(JobQuery.class, ManagementService.class);
   }

}
