package org.camunda.bpm.extension.mockito.delegate;


import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.delegate.TaskListener.EVENTNAME_CREATE;
import static org.camunda.bpm.engine.task.IdentityLinkType.ASSIGNEE;
import static org.camunda.bpm.engine.task.IdentityLinkType.CANDIDATE;
import static org.camunda.bpm.engine.task.IdentityLinkType.OWNER;
import static org.mockito.Mockito.mock;

public class DelegateTaskFakeTest {

  private final ProcessEngineServices processEngineServices = mock(ProcessEngineServices.class);
  private final DelegateTaskFake delegate = new DelegateTaskFake();

  @Test
  public void withId() {
    assertThat(delegate.withId("1").getId()).isEqualTo("1");
  }

  @Test
  public void setName() {
    assertThat(delegate.getName()).isNull();
    delegate.setName("foo");
    assertThat(delegate.getName()).isEqualTo("foo");
  }

  @Test
  public void setDescription() {
    assertThat(delegate.getDescription()).isNull();
    delegate.setDescription("foo");
    assertThat(delegate.getDescription()).isEqualTo("foo");
  }

  @Test
  public void setPriority() {
    assertThat(delegate.getPriority()).isEqualTo(0);
    delegate.setPriority(10);
    assertThat(delegate.getPriority()).isEqualTo(10);
  }

  @Test
  public void addCandidateGroup() {
    delegate.addCandidateGroup("foo");

    assertThat(DelegateTaskFake.candidateGroupIds(delegate)).containsOnly("foo");
  }

  @Test
  public void addAndDeleteUserAssignee() {
    delegate.addUserIdentityLink("foo", ASSIGNEE);
    delegate.addUserIdentityLink("bar", OWNER);

    assertThat(DelegateTaskFake.userIds(delegate)).containsOnly("bar", "foo");

    delegate.deleteCandidateUser("foo");
    assertThat(DelegateTaskFake.userIds(delegate)).containsOnly("foo","bar");

    delegate.deleteUserIdentityLink("foo", ASSIGNEE);
    assertThat(DelegateTaskFake.userIds(delegate)).containsOnly("bar");

  }

  @Test
  public void candidateUsers() {
    initIdentityLinks();

    assertThat(DelegateTaskFake.candidateUserIds(delegate)).containsOnly("user3");
  }

  @Test
  public void candidateGroups() {
    initIdentityLinks();

    assertThat(DelegateTaskFake.candidateGroupIds(delegate)).containsOnly("group3");
  }

  @Test
  public void delegateExecution_can_be_set() {
    assertThat(delegate.getExecution()).isNull();

    DelegateExecution execution = new DelegateExecutionFake();
    delegate.withExecution(execution);
    assertThat(delegate.getExecution()).isEqualTo(execution);
    assertThat(delegate.getExecutionFake()).isEqualTo(execution);
  }

  @Test
  public void delegateExecution_use_attributes_from_execution() {
    delegate.withExecutionId("1");
    assertThat(delegate.getExecutionId()).isEqualTo("1");

    DelegateExecution execution = new DelegateExecutionFake("2")
      .withProcessDefinitionId("pd1")
      .withProcessEngineServices(processEngineServices)
      .withTenantId("tenant")
      .withProcessInstanceId("prId")
        ;
    delegate.withExecution(execution);

    assertThat(delegate.getExecutionId()).isEqualTo("2");
    assertThat(delegate.getProcessDefinitionId()).isEqualTo("pd1");
    assertThat(delegate.getProcessEngineServices()).isEqualTo(processEngineServices);
    assertThat(delegate.getTenantId()).isEqualTo("tenant");
    assertThat(delegate.getProcessInstanceId()).isEqualTo("prId");
  }


  @Test
  public void delegateCaseExecution_use_attributes_from_execution() {
    delegate.withCaseExecutionId("1");
    assertThat(delegate.getCaseExecutionId()).isEqualTo("1");

    DelegateCaseExecution execution = new DelegateCaseExecutionFake("2")
      .withCaseDefinitionId("cd1")
      .withProcessEngineServices(processEngineServices)
      .withTenantId("tenant")
      .withCaseInstanceId("caId")
      ;
    delegate.withCaseExecution(execution);

    assertThat(delegate.getCaseExecutionId()).isEqualTo("2");
    assertThat(delegate.getCaseDefinitionId()).isEqualTo("cd1");
    assertThat(delegate.getProcessEngineServices()).isEqualTo(processEngineServices);
    assertThat(delegate.getTenantId()).isEqualTo("tenant");
    assertThat(delegate.getCaseInstanceId()).isEqualTo("caId");
  }


  @Test
  public void taskListenerSetsCandidateGroup() {
    DelegateTask delegateTask = new DelegateTaskFake()
      .withTaskDefinitionKey("the_task")
      .withEventName(EVENTNAME_CREATE)
      .withVariableLocal("nextGroup", "foo");


    TaskListener taskListener = task -> {
      if (EVENTNAME_CREATE.equals(task.getEventName()) && "the_task".equals(task.getTaskDefinitionKey())) {
        task.addCandidateGroup((String) task.getVariableLocal("nextGroup"));
      }
    };

    taskListener.notify(delegateTask);

    assertThat(DelegateTaskFake.candidateGroupIds(delegateTask)).containsOnly("foo");

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
