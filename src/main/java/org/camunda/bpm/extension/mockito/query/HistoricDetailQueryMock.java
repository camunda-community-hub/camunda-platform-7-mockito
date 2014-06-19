package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricDetail;
import org.camunda.bpm.engine.history.HistoricDetailQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class HistoricDetailQueryMock extends AbstractQueryMock<HistoricDetailQueryMock, HistoricDetailQuery, HistoricDetail, HistoryService> {

  public HistoricDetailQueryMock() {
    super(HistoricDetailQuery.class, HistoryService.class);
  }

}
