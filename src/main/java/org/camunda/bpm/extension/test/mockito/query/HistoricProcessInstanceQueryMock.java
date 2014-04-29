package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class HistoricProcessInstanceQueryMock extends AbstractQueryMock<HistoricProcessInstanceQueryMock, HistoricProcessInstanceQuery, HistoricProcessInstance, HistoryService> {

  public HistoricProcessInstanceQueryMock() {
    super(HistoricProcessInstanceQuery.class, HistoryService.class);
  }

}
