package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricIncident;
import org.camunda.bpm.engine.history.HistoricIncidentQuery;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class HistoricIncidentQueryMock extends AbstractQueryMock<HistoricIncidentQueryMock, HistoricIncidentQuery, HistoricIncident, HistoryService> {

  public HistoricIncidentQueryMock() {
    super(HistoricIncidentQuery.class, HistoryService.class);
  }

}
