package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.batch.BatchQuery;

public class BatchQueryMock extends AbstractQueryMock<BatchQueryMock, BatchQuery, Batch, ManagementService> {

  public BatchQueryMock() {
    super(BatchQuery.class, ManagementService.class);
   }

}
