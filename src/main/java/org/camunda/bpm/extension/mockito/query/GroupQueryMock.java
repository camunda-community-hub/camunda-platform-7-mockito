package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class GroupQueryMock extends AbstractQueryMock<GroupQueryMock, GroupQuery, Group, IdentityService> {

  public GroupQueryMock() {
    super(GroupQuery.class, IdentityService.class);
   }

}
