package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricDecisionInstance;
import org.camunda.bpm.engine.history.HistoricDecisionInstanceQuery;

public class HistoricDecisionInstanceQueryMock extends AbstractQueryMock<HistoricDecisionInstanceQueryMock, HistoricDecisionInstanceQuery, HistoricDecisionInstance, HistoryService> {

  public HistoricDecisionInstanceQueryMock() {
    super(HistoricDecisionInstanceQuery.class, HistoryService.class);
   }

}
