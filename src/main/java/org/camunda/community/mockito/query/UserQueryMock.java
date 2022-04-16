package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;

public class UserQueryMock extends AbstractQueryMock<UserQueryMock, UserQuery, User, IdentityService> {

  public UserQueryMock() {
    super(UserQuery.class, IdentityService.class);
   }

}
