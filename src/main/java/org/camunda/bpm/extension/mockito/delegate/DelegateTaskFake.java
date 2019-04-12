package org.camunda.bpm.extension.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.camunda.bpm.engine.task.IdentityLinkType.CANDIDATE;

@SuppressWarnings({"WeakerAccess","UnusedReturnValue", "unused"})
public class DelegateTaskFake extends DelegateFake<DelegateTaskFake> implements DelegateTask, Serializable {

  public static Set<String> candidateUserIds(DelegateTask task) {
    return userIds(task, CANDIDATE);
  }

  public static Set<String> candidateGroupIds(DelegateTask task) {
    return groupIds(task, CANDIDATE);
  }

  public static Set<String> userIds(DelegateTask task) {
    return userIds(task, null);
  }

  public static Set<String> userIds(DelegateTask task, String type) {
    return linkIds(task, IdentityLink::getUserId, type);
  }

  public static Set<String> groupIds(DelegateTask task) {
    return groupIds(task, null);
  }

  public static Set<String> groupIds(DelegateTask task, String type) {
    return linkIds(task, IdentityLink::getGroupId, type);
  }

  private static Set<String> linkIds(DelegateTask task, Function<IdentityLink, String> extract, String type) {
    return Optional.ofNullable(task.getCandidates()).orElseGet(HashSet::new)
      .stream()
      .filter(link -> type == null || type.equals(link.getType()))
      .map(extract)
      .filter(Objects::nonNull)
      .collect(Collectors.toSet());
  }

  private static Predicate<IdentityLink> isUserLink = link -> link.getUserId() != null;
  private static Predicate<IdentityLink> isGroupLink = link -> link.getGroupId() != null;

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private String id;

  private final Set<IdentityLink> candidates = new LinkedHashSet<>();

  private String name;
  private String description;
  private int priority;
  private String processInstanceId;
  private String executionId;
  private String processDefinitionId;
  private String caseInstanceId;
  private String caseExecutionId;
  private String caseDefinitionId;
  private Date createTime;
  private String taskDefinitionKey;
  private String eventName;
  private String owner;
  private String assignee;
  private Date dueDate;
  private String deleteReason;
  private String tenantId;
  private boolean completed;
  private ProcessEngine processEngine;
  private ProcessEngineServices processEngineServices;
  private DelegateExecution delegateExecution;
  private DelegateCaseExecution delegateCaseExecution;

  public DelegateTaskFake() {
    this(null);
  }

  public DelegateTaskFake(final String id) {
    withId(id);
  }

  @Override
  public String getId() {
    return id;
  }

  public DelegateTaskFake withId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public DelegateTaskFake withName(String name) {
    setName(name);
    return this;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  public DelegateTaskFake withDescription(String description) {
    setDescription(description);
    return this;
  }

  @Override
  public int getPriority() {
    return priority;
  }

  @Override
  public void setPriority(int priority) {
    this.priority = priority;
  }

  public DelegateTaskFake withPriority(int priority) {
    setPriority(priority);
    return this;
  }

  @Override
  public String getProcessInstanceId() {
    return valueFromCaseOrProcessExecution(
      DelegateExecution::getProcessInstanceId,
      c -> null,
      processInstanceId
    );
  }

  public DelegateTaskFake withProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }


  @Override
  public String getProcessDefinitionId() {
    return Optional.ofNullable(delegateExecution)
      .map(DelegateExecution::getProcessDefinitionId)
      .orElse(processDefinitionId);
  }

  public DelegateTaskFake withProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

  @Override
  public String getCaseInstanceId() {
    return Optional.ofNullable(delegateCaseExecution)
      .map(DelegateCaseExecution::getCaseInstanceId)
      .orElse(caseInstanceId);
  }

  public DelegateTaskFake withCaseInstanceId(String caseInstanceId) {
    this.caseInstanceId = caseInstanceId;
    return this;
  }

  @Override
  public String getCaseExecutionId() {
    return Optional.ofNullable(delegateCaseExecution)
      .map(DelegateCaseExecution::getId)
      .orElse(caseExecutionId);
  }

  public DelegateTaskFake withCaseExecutionId(String caseExecutionId) {
    this.caseExecutionId = caseExecutionId;
    return this;
  }

  @Override
  public String getCaseDefinitionId() {
    return Optional.ofNullable(delegateCaseExecution)
      .map(DelegateCaseExecution::getCaseDefinitionId)
      .orElse(caseDefinitionId);
  }

  public DelegateTaskFake withCaseDefinitionId(String caseDefinitionId) {
    this.caseDefinitionId = caseDefinitionId;
    return this;
  }

  @Override
  public Date getCreateTime() {
    return createTime;
  }

  public DelegateTaskFake withCreateTime(Date createTime) {
    this.createTime = createTime;
    return this;
  }

  @Override
  public String getTaskDefinitionKey() {
    return taskDefinitionKey;
  }

  public DelegateTaskFake withTaskDefinitionKey(String taskDefinitionKey) {
    this.taskDefinitionKey = taskDefinitionKey;
    return this;
  }

  @Override
  public DelegateExecution getExecution() {
    return delegateExecution;
  }

  /**
   * Tries to cast execution to fake.
   *
   * @return delegate execution fake
   */
  public DelegateExecutionFake getExecutionFake() {
    return (DelegateExecutionFake) getExecution();
  }

  @Override
  public String getExecutionId() {
    return Optional.ofNullable(delegateExecution).map(DelegateExecution::getId).orElse(executionId);
  }

  public DelegateTaskFake withExecutionId(String executionId) {
    this.executionId = executionId;
    return this;
  }

  public DelegateTaskFake withExecution(DelegateExecution execution) {
    this.delegateExecution = execution;

    return this;
  }

  @Override
  public DelegateCaseExecution getCaseExecution() {
    return delegateCaseExecution;
  }

  public DelegateCaseExecutionFake getCaseExecutionFake() {
    return (DelegateCaseExecutionFake) getCaseExecution();
  }

  public DelegateTaskFake withCaseExecution(DelegateCaseExecution caseExecution) {
    this.delegateCaseExecution = caseExecution;

    return this;
  }

  @Override
  public String getEventName() {
    return eventName;
  }

  public DelegateTaskFake withEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  private IdentityLink identityLink(String userId, String groupId, String type) {
    return new IdentityLink() {
      @Override
      public String getId() {
        return UUID.randomUUID().toString();
      }

      @Override
      public String getType() {
        return type;
      }

      @Override
      public String getUserId() {
        return userId;
      }

      @Override
      public String getGroupId() {
        return groupId;
      }

      @Override
      public String getTaskId() {
        return getId();
      }

      @Override
      public String getProcessDefId() {
        return getProcessDefinitionId();
      }

      @Override
      public String getTenantId() {
        return valueFromCaseOrProcessExecution(
          DelegateExecution::getTenantId,
          DelegateCaseExecution::getTenantId,
          tenantId
        );
      }

      @Override
      public String toString() {
        return new StringJoiner(", ", IdentityLink.class.getSimpleName() + "[", "]")
          .add("userId=" + getUserId())
          .add("groupId=" + getGroupId())
          .add("type=" + getType())
          .toString();
      }
    };
  }

  @Override
  public void addCandidateUser(String userId) {
    addUserIdentityLink(userId, CANDIDATE);
  }

  @Override
  public void addCandidateUsers(Collection<String> candidateUsers) {
    candidateUsers.forEach(this::addCandidateUser);
  }

  @Override
  public void addCandidateGroup(String groupId) {
    addGroupIdentityLink(groupId, CANDIDATE);
  }

  @Override
  public void addCandidateGroups(Collection<String> candidateGroups) {
    candidateGroups.forEach(this::addCandidateGroup);
  }


  @Override
  public void addUserIdentityLink(String userId, String type) {
    candidates.add(identityLink(userId, null, type));
  }

  @Override
  public void addGroupIdentityLink(String groupId, String type) {
    candidates.add(identityLink(null, groupId, type));
  }

  @Override
  public void deleteCandidateUser(String userId) {
    deleteUserIdentityLink(userId, CANDIDATE);
  }

  @Override
  public void deleteCandidateGroup(String groupId) {
    deleteGroupIdentityLink(groupId, CANDIDATE);
  }

  @Override
  public void deleteUserIdentityLink(String userId, String type) {
    candidates.removeIf(identityLink -> type.equals(identityLink.getType()) && identityLink.getUserId() != null && userId.equals(identityLink.getUserId()));
  }

  @Override
  public void deleteGroupIdentityLink(String groupId, String type) {
    candidates.removeIf(identityLink -> type.equals(identityLink.getType()) && identityLink.getGroupId() != null && groupId.equals(identityLink.getGroupId()));
  }

  @Override
  public Set<IdentityLink> getCandidates() {
    return candidates;
  }

  @Override
  public String getOwner() {
    return owner;
  }

  @Override
  public void setOwner(String owner) {
    this.owner = owner;
  }

  @Override
  public String getAssignee() {
    return assignee;
  }

  @Override
  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public DelegateTaskFake withAssignee(String assignee) {
    setAssignee(assignee);
    return this;
  }

  @Override
  public Date getDueDate() {
    return dueDate;
  }

  @Override
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public String getDeleteReason() {
    return deleteReason;
  }

  public DelegateTaskFake withDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
    return this;
  }


  @Override
  public BpmnModelInstance getBpmnModelInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public UserTask getBpmnModelElementInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getTenantId() {
    return valueFromCaseOrProcessExecution(
      DelegateExecution::getTenantId,
      DelegateCaseExecution::getTenantId,
      tenantId
    );
  }

  public DelegateTaskFake withTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  @Override
  public void complete() {
    log.info("task completed!");
    this.completed = true;
  }

  public boolean isCompleted() {
    return completed;
  }

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    return valueFromCaseOrProcessExecution(
      DelegateExecution::getProcessEngineServices,
      DelegateCaseExecution::getProcessEngineServices,
      processEngineServicesAwareFake.getProcessEngineServices()
    );
  }

  @Override
  public ProcessEngine getProcessEngine() {
    return valueFromCaseOrProcessExecution(
      DelegateExecution::getProcessEngine,
      DelegateCaseExecution::getProcessEngine,
      processEngineServicesAwareFake.getProcessEngine()
    );
  }

  private <T> T valueFromCaseOrProcessExecution(
    Function<DelegateExecution, T> fromProcess,
    Function<DelegateCaseExecution, T> fromCase,
    T fromTask) {

    return Optional.ofNullable(delegateExecution)
      .map(fromProcess)
      .orElse(Optional.ofNullable(delegateCaseExecution)
        .map(fromCase)
        .orElse(fromTask)
      );
  }

  @Override public String toString() {
    return "DelegateTaskFake{" +
      "id='" + id + '\'' +
      ", candidates=" + candidates +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", priority=" + priority +
      ", processInstanceId='" + processInstanceId + '\'' +
      ", executionId='" + executionId + '\'' +
      ", processDefinitionId='" + processDefinitionId + '\'' +
      ", caseInstanceId='" + caseInstanceId + '\'' +
      ", caseExecutionId='" + caseExecutionId + '\'' +
      ", caseDefinitionId='" + caseDefinitionId + '\'' +
      ", createTime=" + createTime +
      ", taskDefinitionKey='" + taskDefinitionKey + '\'' +
      ", eventName='" + eventName + '\'' +
      ", owner='" + owner + '\'' +
      ", assignee='" + assignee + '\'' +
      ", dueDate=" + dueDate +
      ", deleteReason='" + deleteReason + '\'' +
      ", tenantId='" + tenantId + '\'' +
      ", completed=" + completed +
      ", processEngine='" + getProcessEngine() + '\'' +
      ", processEngineServices=" + getProcessEngineServices() +
      ", delegateExecution=" + delegateExecution +
      ", delegateCaseExecution=" + delegateCaseExecution +
      '}';
  }
}
