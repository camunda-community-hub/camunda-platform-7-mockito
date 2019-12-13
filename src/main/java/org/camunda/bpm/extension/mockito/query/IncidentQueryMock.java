package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.IncidentQuery;

public class IncidentQueryMock extends AbstractQueryMock<IncidentQueryMock, IncidentQuery, Incident, RuntimeService> {

  public IncidentQueryMock() {
    super(IncidentQuery.class, RuntimeService.class);
   }

}
