package org.camunda.bpm.extension.mockito.task;

import org.apache.commons.lang3.NotImplementedException;
import org.camunda.bpm.engine.form.CamundaFormRef;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;

import java.util.Date;

public class TaskFake implements Task {

  private String id;
  private String name;
  private String description;
  private int priority;
  private String owner;
  private String assignee;
  private String processDefinitionId;
  private String caseDefinitionId;
  private String executionId;
  private String processInstanceId;
  private String caseInstanceId;
  private String caseExecutionId;
  private Date createTime;
  private String taskDefinitionKey;
  private Date dueDate;
  private Date followUpDate;
  private String parentTaskId;
  private boolean suspended;
  private String tenantId;
  private String formKey;
  private DelegationState delegationState;
  private CamundaFormRef camundaFormRef;

  @java.beans.ConstructorProperties({"id", "name", "description", "priority", "owner", "assignee",
    "processDefinitionId", "caseDefinitionId", "executionId", "processInstanceId", "caseInstanceId",
    "caseExecutionId", "createTime", "taskDefinitionKey", "dueDate", "followUpDate", "parentTaskId",
    "suspended", "tenantId", "formKey", "delegationState", "camundaFormRef"})
  TaskFake(final String id, final String name, final String description, final int priority, final String owner,
           final String assignee, final String processDefinitionId, final String caseDefinitionId,
           final String executionId, final String processInstanceId, final String caseInstanceId,
           final String caseExecutionId, final Date createTime, final String taskDefinitionKey,
           final Date dueDate, final Date followUpDate, final String parentTaskId, final boolean suspended,
           final String tenantId, final String formKey, final DelegationState delegationState,
           final CamundaFormRef camundaFormRef) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.priority = priority;
    this.owner = owner;
    this.assignee = assignee;
    this.processDefinitionId = processDefinitionId;
    this.caseDefinitionId = caseDefinitionId;
    this.executionId = executionId;
    this.processInstanceId = processInstanceId;
    this.caseInstanceId = caseInstanceId;
    this.caseExecutionId = caseExecutionId;
    this.createTime = createTime;
    this.taskDefinitionKey = taskDefinitionKey;
    this.dueDate = dueDate;
    this.followUpDate = followUpDate;
    this.parentTaskId = parentTaskId;
    this.suspended = suspended;
    this.tenantId = tenantId;
    this.formKey = formKey;
    this.delegationState = delegationState;
    this.camundaFormRef = camundaFormRef;
  }

  public static TaskFakeBuilder builder() {
    return new TaskFakeBuilder();
  }

  @Override
  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(final String description) {
    this.description = description;
  }

  @Override
  public int getPriority() {
    return priority;
  }

  @Override
  public void setPriority(final int priority) {
    this.priority = priority;
  }

  @Override
  public String getOwner() {
    return owner;
  }

  @Override
  public void setOwner(final String owner) {

    this.owner = owner;
  }

  @Override
  public String getAssignee() {
    return assignee;
  }

  @Override
  public void setAssignee(final String assignee) {
    this.assignee = assignee;
  }

  @Override
  public DelegationState getDelegationState() {
    return delegationState;
  }

  @Override
  public void setDelegationState(final DelegationState delegationState) {
    this.delegationState = delegationState;
  }

  @Override
  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(final String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  @Override
  public String getExecutionId() {
    return executionId;
  }

  public void setExecutionId(final String executionId) {
    this.executionId = executionId;
  }

  @Override
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(final String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  @Override
  public String getCaseInstanceId() {
    return caseInstanceId;
  }

  @Override
  public void setCaseInstanceId(final String caseInstanceId) {
    this.caseInstanceId = caseInstanceId;
  }

  @Override
  public String getCaseExecutionId() {
    return caseExecutionId;
  }

  public void setCaseExecutionId(final String caseExecutionId) {
    this.caseExecutionId = caseExecutionId;
  }

  @Override
  public String getCaseDefinitionId() {
    return caseDefinitionId;
  }

  public void setCaseDefinitionId(final String caseDefinitionId) {
    this.caseDefinitionId = caseDefinitionId;
  }

  @Override
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(final Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String getTaskDefinitionKey() {
    return taskDefinitionKey;
  }

  public void setTaskDefinitionKey(final String taskDefinitionKey) {
    this.taskDefinitionKey = taskDefinitionKey;
  }

  @Override
  public Date getDueDate() {
    return dueDate;
  }

  @Override
  public void setDueDate(final Date dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public Date getFollowUpDate() {
    return followUpDate;
  }

  @Override
  public void setFollowUpDate(final Date followUpDate) {
    this.followUpDate = followUpDate;
  }

  @Override
  public void delegate(final String userId) {
    throw new NotImplementedException("Not implemented yet");
  }

  @Override
  public String getParentTaskId() {
    return parentTaskId;
  }

  @Override
  public void setParentTaskId(final String parentTaskId) {
    this.parentTaskId = parentTaskId;
  }

  @Override
  public boolean isSuspended() {
    return suspended;
  }

  public void setSuspended(final boolean suspended) {
    this.suspended = suspended;
  }

  @Override
  public String getFormKey() {
    return formKey;
  }

  @Override
  public CamundaFormRef getCamundaFormRef() {
    return camundaFormRef;
  }

  public void setCamundaFormRef(CamundaFormRef camundaFormRef) {
    this.camundaFormRef = camundaFormRef;
  }

  public void setFormKey(final String formKey) {
    this.formKey = formKey;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }

  @Override
  public void setTenantId(final String tenantId) {
    this.tenantId = tenantId;
  }

  @Override
  public String toString() {
    return "TaskFake{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", priority=" + priority +
      ", owner='" + owner + '\'' +
      ", assignee='" + assignee + '\'' +
      ", processDefinitionId='" + processDefinitionId + '\'' +
      ", caseDefinitionId='" + caseDefinitionId + '\'' +
      ", executionId='" + executionId + '\'' +
      ", processInstanceId='" + processInstanceId + '\'' +
      ", caseInstanceId='" + caseInstanceId + '\'' +
      ", caseExecutionId='" + caseExecutionId + '\'' +
      ", createTime=" + createTime +
      ", taskDefinitionKey='" + taskDefinitionKey + '\'' +
      ", dueDate=" + dueDate +
      ", followUpDate=" + followUpDate +
      ", parentTaskId='" + parentTaskId + '\'' +
      ", suspended=" + suspended +
      ", tenantId='" + tenantId + '\'' +
      ", formKey='" + formKey + '\'' +
      ", delegationState=" + delegationState +
      '}';
  }

  public static class TaskFakeBuilder {
    private String id;
    private String name;
    private String description;
    private int priority;
    private String owner;
    private String assignee;
    private String processDefinitionId;
    private String caseDefinitionId;
    private String executionId;
    private String processInstanceId;
    private String caseInstanceId;
    private String caseExecutionId;
    private Date createTime;
    private String taskDefinitionKey;
    private Date dueDate;
    private Date followUpDate;
    private String parentTaskId;
    private boolean suspended;
    private String tenantId;
    private String formKey;
    private DelegationState delegationState;
    private CamundaFormRef camundaFormRef;

    TaskFakeBuilder() {
    }

    public TaskFakeBuilder id(final String id) {
      this.id = id;
      return this;
    }

    public TaskFakeBuilder name(final String name) {
      this.name = name;
      return this;
    }

    public TaskFakeBuilder description(final String description) {
      this.description = description;
      return this;
    }

    public TaskFakeBuilder priority(final int priority) {
      this.priority = priority;
      return this;
    }

    public TaskFakeBuilder owner(final String owner) {
      this.owner = owner;
      return this;
    }

    public TaskFakeBuilder assignee(final String assignee) {
      this.assignee = assignee;
      return this;
    }

    public TaskFakeBuilder processDefinitionId(final String processDefinitionId) {
      this.processDefinitionId = processDefinitionId;
      return this;
    }

    public TaskFakeBuilder caseDefinitionId(final String caseDefinitionId) {
      this.caseDefinitionId = caseDefinitionId;
      return this;
    }

    public TaskFakeBuilder executionId(final String executionId) {
      this.executionId = executionId;
      return this;
    }

    public TaskFakeBuilder processInstanceId(final String processInstanceId) {
      this.processInstanceId = processInstanceId;
      return this;
    }

    public TaskFakeBuilder caseInstanceId(final String caseInstanceId) {
      this.caseInstanceId = caseInstanceId;
      return this;
    }

    public TaskFakeBuilder caseExecutionId(final String caseExecutionId) {
      this.caseExecutionId = caseExecutionId;
      return this;
    }

    public TaskFakeBuilder createTime(final Date createTime) {
      this.createTime = createTime;
      return this;
    }

    public TaskFakeBuilder taskDefinitionKey(final String taskDefinitionKey) {
      this.taskDefinitionKey = taskDefinitionKey;
      return this;
    }

    public TaskFakeBuilder dueDate(final Date dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public TaskFakeBuilder followUpDate(final Date followUpDate) {
      this.followUpDate = followUpDate;
      return this;
    }

    public TaskFakeBuilder parentTaskId(final String parentTaskId) {
      this.parentTaskId = parentTaskId;
      return this;
    }

    public TaskFakeBuilder suspended(final boolean suspended) {
      this.suspended = suspended;
      return this;
    }

    public TaskFakeBuilder tenantId(final String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    public TaskFakeBuilder formKey(final String formKey) {
      this.formKey = formKey;
      return this;
    }

    public TaskFakeBuilder delegationState(final DelegationState delegationState) {
      this.delegationState = delegationState;
      return this;
    }

    public TaskFakeBuilder camundaFormRef(final CamundaFormRef camundaFormRef) {
      this.camundaFormRef = camundaFormRef;
      return this;
    }

    public TaskFake build() {
      return new TaskFake(id, name, description, priority, owner, assignee, processDefinitionId, caseDefinitionId, executionId, processInstanceId, caseInstanceId, caseExecutionId, createTime, taskDefinitionKey, dueDate, followUpDate, parentTaskId, suspended, tenantId, formKey, delegationState, camundaFormRef);
    }

    public String toString() {
      return "TaskFake.TaskFakeBuilder(" +
        "id=" + this.id + ", " +
        "name=" + this.name + ", " +
        "description=" + this.description + ", " +
        "priority=" + this.priority + ", " +
        "owner=" + this.owner + ", " +
        "assignee=" + this.assignee + ", " +
        "processDefinitionId=" + this.processDefinitionId + ", " +
        "caseDefinitionId=" + this.caseDefinitionId + ", " +
        "executionId=" + this.executionId + ", " +
        "processInstanceId=" + this.processInstanceId + ", " +
        "caseInstanceId=" + this.caseInstanceId + ", " +
        "caseExecutionId=" + this.caseExecutionId + ", " +
        "createTime=" + this.createTime + ", " +
        "taskDefinitionKey=" + this.taskDefinitionKey + ", " +
        "dueDate=" + this.dueDate + ", " +
        "followUpDate=" + this.followUpDate + ", " +
        "parentTaskId=" + this.parentTaskId + ", " +
        "suspended=" + this.suspended + ", " +
        "tenantId=" + this.tenantId + ", " +
        "formKey=" + this.formKey + ", " +
        "delegationState=" + this.delegationState + ", " +
        "camundaFormRef=" + this.camundaFormRef +
        ")";
    }
  }
}
