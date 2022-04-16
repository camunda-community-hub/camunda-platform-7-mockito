package org.camunda.community.mockito.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.AuthorizationQuery;
//import org.camunda.community.mockito.QueryMocks1;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class AuthorizationQueryMockTest {

  private final AuthorizationService serviceMock = mock(AuthorizationService.class);
  private final Authorization authorization = mock(Authorization.class);

  @Test
  public void mock_authorizationQuery() {
    final AuthorizationQuery query = null; //QueryMocks1.mockAuthorizationQuery(serviceMock).singleResult(authorization);

    // @formatter:off
    final Authorization result = serviceMock.createAuthorizationQuery()
      .authorizationId("foo")
      .authorizationType(1)
      .userIdIn("user")
      .singleResult();
    // @formatter:on

    assertThat(result).isEqualTo(authorization);

    verify(query).userIdIn("user");
    verify(query).authorizationId("foo");
    verify(query).authorizationType(1);
  }

}
