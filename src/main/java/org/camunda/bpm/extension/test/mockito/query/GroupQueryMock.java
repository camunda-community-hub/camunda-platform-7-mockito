package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class GroupQueryMock extends AbstractQueryMock<GroupQueryMock, GroupQuery, Group, IdentityService> {

  public GroupQueryMock() {
    super(GroupQuery.class, IdentityService.class);
  }

}
