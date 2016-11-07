package org.camunda.bpm.extension.mockito.context;


import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class DelegateTaskFake extends VariableScopeFake implements DelegateTask {

  @Override
  public String getId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void setName(String name) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getDescription() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void setDescription(String description) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public int getPriority() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void setPriority(int priority) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getProcessInstanceId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getExecutionId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getProcessDefinitionId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getCaseInstanceId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getCaseExecutionId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getCaseDefinitionId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public Date getCreateTime() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getTaskDefinitionKey() {
    throw new UnsupportedOperationException("not implemented");
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
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void addCandidateUser(String userId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void addCandidateUsers(Collection<String> candidateUsers) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void addCandidateGroup(String groupId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void addCandidateGroups(Collection<String> candidateGroups) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getOwner() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void setOwner(String owner) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getAssignee() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void setAssignee(String assignee) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public Date getDueDate() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void setDueDate(Date dueDate) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getDeleteReason() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void addUserIdentityLink(String userId, String identityLinkType) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void addGroupIdentityLink(String groupId, String identityLinkType) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void deleteCandidateUser(String userId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void deleteCandidateGroup(String groupId) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void deleteUserIdentityLink(String userId, String identityLinkType) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void deleteGroupIdentityLink(String groupId, String identityLinkType) {
    throw new UnsupportedOperationException("not implemented");
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
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    throw new UnsupportedOperationException("not implemented");
  }
}
