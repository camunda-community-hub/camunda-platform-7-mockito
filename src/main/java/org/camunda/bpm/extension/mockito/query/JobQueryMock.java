package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.JobQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class JobQueryMock extends AbstractQueryMock<JobQueryMock, JobQuery, Job, ManagementService> {

  public JobQueryMock() {
    super(JobQuery.class, ManagementService.class);
   }

}
