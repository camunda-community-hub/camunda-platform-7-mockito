package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricIncident;
import org.camunda.bpm.engine.history.HistoricIncidentQuery;

public class HistoricIncidentQueryMock extends AbstractQueryMock<HistoricIncidentQueryMock, HistoricIncidentQuery, HistoricIncident, HistoryService> {

  public HistoricIncidentQueryMock() {
    super(HistoricIncidentQuery.class, HistoryService.class);
   }

}
