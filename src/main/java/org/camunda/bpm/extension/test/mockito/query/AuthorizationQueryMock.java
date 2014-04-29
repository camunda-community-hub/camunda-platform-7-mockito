package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.AuthorizationQuery;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.IncidentQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class AuthorizationQueryMock extends AbstractQueryMock<AuthorizationQueryMock, AuthorizationQuery, Authorization, AuthorizationService> {

  public AuthorizationQueryMock() {
    super(AuthorizationQuery.class, AuthorizationService.class);
  }

}
