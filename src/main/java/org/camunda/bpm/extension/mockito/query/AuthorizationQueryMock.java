package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.AuthorizationQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class AuthorizationQueryMock extends AbstractQueryMock<AuthorizationQueryMock, AuthorizationQuery, Authorization, AuthorizationService> {

  public AuthorizationQueryMock() {
    super(AuthorizationQuery.class, AuthorizationService.class);
  }

}
