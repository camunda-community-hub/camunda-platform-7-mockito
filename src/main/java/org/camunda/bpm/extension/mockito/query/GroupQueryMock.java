package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;

public class GroupQueryMock extends AbstractQueryMock<GroupQueryMock, GroupQuery, Group, IdentityService> {

  public GroupQueryMock() {
    super(GroupQuery.class, IdentityService.class);
   }

}
