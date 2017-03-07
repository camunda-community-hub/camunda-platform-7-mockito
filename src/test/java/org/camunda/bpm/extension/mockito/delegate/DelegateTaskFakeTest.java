package org.camunda.bpm.extension.mockito.delegate;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.task.IdentityLinkType.ASSIGNEE;
import static org.camunda.bpm.engine.task.IdentityLinkType.CANDIDATE;
import static org.camunda.bpm.engine.task.IdentityLinkType.OWNER;

public class DelegateTaskFakeTest {

  private final DelegateTaskFake delegate = new DelegateTaskFake();

  @Test
  public void withId() throws Exception {
    assertThat(delegate.withId("1").getId()).isEqualTo("1");
  }

  @Test
  public void setName() throws Exception {
    assertThat(delegate.getName()).isNull();
    delegate.setName("foo");
    assertThat(delegate.getName()).isEqualTo("foo");
  }

  @Test
  public void setDescription() throws Exception {
    assertThat(delegate.getDescription()).isNull();
    delegate.setDescription("foo");
    assertThat(delegate.getDescription()).isEqualTo("foo");
  }

  @Test
  public void setPriority() throws Exception {
    assertThat(delegate.getPriority()).isEqualTo(0);
    delegate.setPriority(10);
    assertThat(delegate.getPriority()).isEqualTo(10);
  }

  @Test
  public void addCandidateGroup() throws Exception {
    delegate.addCandidateGroup("foo");

    assertThat(DelegateTaskFake.candidateGroupIds(delegate)).containsOnly("foo");
  }

  @Test
  public void addAndDeleteUserAssignee() throws Exception {
    delegate.addUserIdentityLink("foo", ASSIGNEE);
    delegate.addUserIdentityLink("bar", OWNER);

    assertThat(DelegateTaskFake.userIds(delegate)).containsOnly("bar", "foo");

    delegate.deleteCandidateUser("foo");
    assertThat(DelegateTaskFake.userIds(delegate)).containsOnly("foo","bar");

    delegate.deleteUserIdentityLink("foo", ASSIGNEE);
    assertThat(DelegateTaskFake.userIds(delegate)).containsOnly("bar");

  }

  @Test
  public void candidateUsers() throws Exception {
    initIdentityLinks();

    assertThat(DelegateTaskFake.candidateUserIds(delegate)).containsOnly("user3");
  }

  @Test
  public void candidateGroups() throws Exception {
    initIdentityLinks();

    assertThat(DelegateTaskFake.candidateGroupIds(delegate)).containsOnly("group3");
  }

  private void initIdentityLinks() {
    delegate.addUserIdentityLink("user1", ASSIGNEE);
    delegate.addUserIdentityLink("user2", OWNER);
    delegate.addUserIdentityLink("user3", CANDIDATE);
    delegate.addGroupIdentityLink("group1", ASSIGNEE);
    delegate.addGroupIdentityLink("group2", OWNER);
    delegate.addGroupIdentityLink("group3", CANDIDATE);

  }
}
