package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.batch.history.HistoricBatch;
import org.camunda.bpm.engine.batch.history.HistoricBatchQuery;

public class HistoricBatchQueryMock extends AbstractQueryMock<HistoricBatchQueryMock, HistoricBatchQuery, HistoricBatch, HistoryService> {

  public HistoricBatchQueryMock() {
    super(HistoricBatchQuery.class, HistoryService.class);
   }

}
