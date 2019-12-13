package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.history.HistoricVariableInstanceQuery;

public class HistoricVariableInstanceQueryMock extends AbstractQueryMock<HistoricVariableInstanceQueryMock, HistoricVariableInstanceQuery, HistoricVariableInstance, HistoryService> {

  public HistoricVariableInstanceQueryMock() {
    super(HistoricVariableInstanceQuery.class, HistoryService.class);
   }

}
