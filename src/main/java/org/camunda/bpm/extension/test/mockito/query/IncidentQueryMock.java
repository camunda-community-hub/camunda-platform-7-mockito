package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.IncidentQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class IncidentQueryMock extends AbstractQueryMock<IncidentQueryMock, IncidentQuery, Incident, RuntimeService> {

  public IncidentQueryMock() {
    super(IncidentQuery.class, RuntimeService.class);
  }

}
