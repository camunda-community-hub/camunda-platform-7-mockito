package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricIdentityLinkLog;
import org.camunda.bpm.engine.history.HistoricIdentityLinkLogQuery;

public class HistoricIdentityLinkLogQueryMock extends AbstractQueryMock<HistoricIdentityLinkLogQueryMock, HistoricIdentityLinkLogQuery, HistoricIdentityLinkLog, HistoryService> {

  public HistoricIdentityLinkLogQueryMock() {
    super(HistoricIdentityLinkLogQuery.class, HistoryService.class);
   }

}
