package org.camunda.bpm.extension.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DelegateTaskFake extends VariableScopeFake implements DelegateTask {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private String id;

  private List<String> candidateUsers = new ArrayList<>();
  private List<String> candidateGroups = new ArrayList<>();

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
  private ProcessEngineServices processEngineServices;


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
    return processInstanceId;
  }

  public DelegateTaskFake withProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

  @Override
  public String getExecutionId() {
    return executionId;
  }

  public DelegateTaskFake withExecutionI(String executionId) {
    this.executionId = executionId;
    return this;
  }


  @Override
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public DelegateTaskFake withProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

  @Override
  public String getCaseInstanceId() {
    return caseInstanceId;
  }

  public DelegateTaskFake withCaseInstanceId(String caseInstanceId) {
    this.caseInstanceId = caseInstanceId;
    return this;
  }

  @Override
  public String getCaseExecutionId() {
    return caseExecutionId;
  }

  public DelegateTaskFake withCaseExecutionId(String caseExecutionId) {
    this.caseExecutionId = caseExecutionId;
    return this;
  }

  @Override
  public String getCaseDefinitionId() {
    return caseDefinitionId;
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
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public DelegateCaseExecution getCaseExecution() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getEventName() {
    return eventName;
  }

  public DelegateTaskFake withEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  @Override
  public void addCandidateUser(String userId) {
    candidateUsers.add(userId);
  }

  @Override
  public void addCandidateUsers(Collection<String> candidateUsers) {
    this.candidateUsers.addAll(candidateUsers);
  }

  @Override
  public void addCandidateGroup(String groupId) {
    candidateGroups.add(groupId);
  }

  @Override
  public void addCandidateGroups(Collection<String> candidateGroups) {
    this.candidateGroups.addAll(candidateGroups);
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
  public void addUserIdentityLink(String userId, String identityLinkType) {
    addCandidateUser(userId);
  }

  @Override
  public void addGroupIdentityLink(String groupId, String identityLinkType) {
    addCandidateGroup(groupId);
  }

  @Override
  public void deleteCandidateUser(String userId) {
    candidateUsers.remove(userId);
  }

  @Override
  public void deleteCandidateGroup(String groupId) {
    candidateGroups.remove(groupId);
  }

  @Override
  public void deleteUserIdentityLink(String userId, String identityLinkType) {
    deleteCandidateUser(userId);
  }

  @Override
  public void deleteGroupIdentityLink(String groupId, String identityLinkType) {
    deleteCandidateGroup(groupId);
  }

  @Override
  public Set<IdentityLink> getCandidates() {
    throw new UnsupportedOperationException("not implemented");
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
    return tenantId;
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
    return processEngineServices;
  }

  public DelegateTaskFake withProcessEngineServices(ProcessEngineServices processEngineServices) {
    this.processEngineServices = processEngineServices;
    return this;
  }


}
