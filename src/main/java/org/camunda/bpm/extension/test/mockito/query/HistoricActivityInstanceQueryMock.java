package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricActivityInstanceQuery;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class HistoricActivityInstanceQueryMock extends AbstractQueryMock<HistoricActivityInstanceQueryMock, HistoricActivityInstanceQuery, HistoricActivityInstance, HistoryService> {

  public HistoricActivityInstanceQueryMock() {
    super(HistoricActivityInstanceQuery.class, HistoryService.class);
  }

}
