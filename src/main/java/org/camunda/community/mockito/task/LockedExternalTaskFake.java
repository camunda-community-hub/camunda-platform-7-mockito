package org.camunda.community.mockito.task;

import io.holunda.camunda.bpm.data.CamundaBpmData;
import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LockedExternalTaskFake implements LockedExternalTask {

  public static LockedExternalTaskFakeBuilder builder() {
    return new LockedExternalTaskFakeBuilder();
  }

  private String id;
  private String topicName;

  private String workerId;
  private String processInstanceId;
  private String executionId;

  private Date lockExpirationTime;
  private String activityId;
  private String activityInstanceId;
  private String processDefinitionId;
  private String processDefinitionKey;
  private String processDefinitionVersionTag;
  private Integer retries;
  private String errorMessage;
  private String errorDetails;
  private VariableMap variables = Variables.createVariables();
  private String tenantId;
  private long priority;
  private String businessKey;
  private Map<String, String> extensionProperties = new HashMap<>();

  @java.beans.ConstructorProperties({"id", "topicName", "workerId",
    "processInstanceId", "executionId", "lockExpirationTime",
    "activityId", "activityInstanceId", "processDefinitionId",
    "processDefinitionKey", "processDefinitionVersionTag", "retries",
    "errorMessage", "errorDetails", "variables",
    "tenantId", "priority", "businessKey",
    "extensionProperties"
  })
  LockedExternalTaskFake(String id, String topicName, String workerId, String processInstanceId, String executionId, Date lockExpirationTime, String activityId, String activityInstanceId, String processDefinitionId, String processDefinitionKey, String processDefinitionVersionTag, Integer retries, String errorMessage, String errorDetails, VariableMap variables, String tenantId, long priority, String businessKey, Map<String, String> extensionProperties) {
    this.id = id;
    this.topicName = topicName;
    this.workerId = workerId;
    this.processInstanceId = processInstanceId;
    this.executionId = executionId;
    this.lockExpirationTime = lockExpirationTime;
    this.activityId = activityId;
    this.activityInstanceId = activityInstanceId;
    this.processDefinitionId = processDefinitionId;
    this.processDefinitionKey = processDefinitionKey;
    this.processDefinitionVersionTag = processDefinitionVersionTag;
    this.retries = retries;
    this.errorMessage = errorMessage;
    this.errorDetails = errorDetails;
    this.variables = variables;
    this.tenantId = tenantId;
    this.priority = priority;
    this.businessKey = businessKey;
    this.extensionProperties = extensionProperties;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getTopicName() {
    return topicName;
  }

  @Override
  public String getWorkerId() {
    return workerId;
  }

  @Override
  public Date getLockExpirationTime() {
    return lockExpirationTime;
  }

  @Override
  public String getProcessInstanceId() {
    return processInstanceId;
  }

  @Override
  public String getExecutionId() {
    return executionId;
  }

  @Override
  public String getActivityId() {
    return activityId;
  }

  @Override
  public String getActivityInstanceId() {
    return activityInstanceId;
  }

  @Override
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  @Override
  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }

  @Override
  public String getProcessDefinitionVersionTag() {
    return processDefinitionVersionTag;
  }

  @Override
  public Integer getRetries() {
    return retries;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public String getErrorDetails() {
    return errorDetails;
  }

  @Override
  public VariableMap getVariables() {
    return variables;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }

  @Override
  public long getPriority() {
    return priority;
  }

  @Override
  public String getBusinessKey() {
    return businessKey;
  }

  @Override
  public Map<String, String> getExtensionProperties() {
    return extensionProperties;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public void setWorkerId(String workerId) {
    this.workerId = workerId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }

  public void setLockExpirationTime(Date lockExpirationTime) {
    this.lockExpirationTime = lockExpirationTime;
  }

  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public void setActivityInstanceId(String activityInstanceId) {
    this.activityInstanceId = activityInstanceId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }

  public void setProcessDefinitionVersionTag(String processDefinitionVersionTag) {
    this.processDefinitionVersionTag = processDefinitionVersionTag;
  }

  public void setRetries(Integer retries) {
    this.retries = retries;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setErrorDetails(String errorDetails) {
    this.errorDetails = errorDetails;
  }

  public void setVariables(VariableMap variables) {
    this.variables = variables;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public void setPriority(long priority) {
    this.priority = priority;
  }

  public void setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
  }

  public void setExtensionProperties(Map<String, String> extensionProperties) {
    this.extensionProperties = extensionProperties;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LockedExternalTaskFake that = (LockedExternalTaskFake) o;
    return priority == that.priority && Objects.equals(id, that.id) && Objects.equals(topicName, that.topicName) && Objects.equals(workerId, that.workerId) && Objects.equals(processInstanceId, that.processInstanceId) && Objects.equals(executionId, that.executionId) && Objects.equals(lockExpirationTime, that.lockExpirationTime) && Objects.equals(activityId, that.activityId) && Objects.equals(activityInstanceId, that.activityInstanceId) && Objects.equals(processDefinitionId, that.processDefinitionId) && Objects.equals(processDefinitionKey, that.processDefinitionKey) && Objects.equals(processDefinitionVersionTag, that.processDefinitionVersionTag) && Objects.equals(retries, that.retries) && Objects.equals(errorMessage, that.errorMessage) && Objects.equals(errorDetails, that.errorDetails) && Objects.equals(variables, that.variables) && Objects.equals(tenantId, that.tenantId) && Objects.equals(businessKey, that.businessKey) && Objects.equals(extensionProperties, that.extensionProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, topicName, workerId, processInstanceId, executionId, lockExpirationTime, activityId, activityInstanceId, processDefinitionId, processDefinitionKey, processDefinitionVersionTag, retries, errorMessage, errorDetails, variables, tenantId, priority, businessKey, extensionProperties);
  }

  @Override
  public String toString() {
    return "LockedExternalTaskFake{" +
      "id='" + id + '\'' +
      ", topicName='" + topicName + '\'' +
      ", workerId='" + workerId + '\'' +
      ", processInstanceId='" + processInstanceId + '\'' +
      ", executionId='" + executionId + '\'' +
      ", lockExpirationTime=" + lockExpirationTime +
      ", activityId='" + activityId + '\'' +
      ", activityInstanceId='" + activityInstanceId + '\'' +
      ", processDefinitionId='" + processDefinitionId + '\'' +
      ", processDefinitionKey='" + processDefinitionKey + '\'' +
      ", processDefinitionVersionTag='" + processDefinitionVersionTag + '\'' +
      ", retries=" + retries +
      ", errorMessage='" + errorMessage + '\'' +
      ", errorDetails='" + errorDetails + '\'' +
      ", variables=" + variables +
      ", tenantId='" + tenantId + '\'' +
      ", priority=" + priority +
      ", businessKey='" + businessKey + '\'' +
      ", extensionProperties=" + extensionProperties +
      '}';
  }

  public static class LockedExternalTaskFakeBuilder {

    private String id;
    private String topicName;
    private String workerId;
    private String processInstanceId;
    private String executionId;
    private Date lockExpirationTime;
    private String activityId;
    private String activityInstanceId;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String processDefinitionVersionTag;
    private Integer retries;
    private String errorMessage;
    private String errorDetails;
    private VariableMap variables = Variables.createVariables();
    private String tenantId;
    private long priority;
    private String businessKey;
    private Map<String, String> extensionProperties = new HashMap<>();

    LockedExternalTaskFakeBuilder() {
    }

    public LockedExternalTaskFakeBuilder id(String id) {
      this.id = id;
      return this;
    }


    public LockedExternalTaskFakeBuilder topicName(String topicName) {
      this.topicName = topicName;
      return this;
    }

    public LockedExternalTaskFakeBuilder workerId(String workerId) {
      this.workerId = workerId;
      return this;
    }

    public LockedExternalTaskFakeBuilder lockExpirationTime(Date lockExpirationTime) {
      this.lockExpirationTime = lockExpirationTime;
      return this;
    }

    public LockedExternalTaskFakeBuilder activityId(String activityId) {
      this.activityId = activityId;
      return this;
    }

    public LockedExternalTaskFakeBuilder activityInstanceId(String activityInstanceId) {
      this.activityInstanceId = activityInstanceId;
      return this;
    }

    public LockedExternalTaskFakeBuilder processInstanceId(String processInstanceId) {
      this.processInstanceId = processInstanceId;
      return this;
    }

    public LockedExternalTaskFakeBuilder executionId(String executionId) {
      this.executionId = executionId;
      return this;
    }

    public LockedExternalTaskFakeBuilder processDefinitionId(String processDefinitionId) {
      this.processDefinitionId = processDefinitionId;
      return this;
    }

    public LockedExternalTaskFakeBuilder processDefinitionKey(String processDefinitionKey) {
      this.processDefinitionKey = processDefinitionKey;
      return this;
    }

    public LockedExternalTaskFakeBuilder processDefinitionVersionTag(String processDefinitionVersionTag) {
      this.processDefinitionVersionTag = processDefinitionVersionTag;
      return this;
    }

    public LockedExternalTaskFakeBuilder errorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
      return this;
    }

    public LockedExternalTaskFakeBuilder errorDetails(String errorDetails) {
      this.errorDetails = errorDetails;
      return this;
    }

    public LockedExternalTaskFakeBuilder retries(int retries) {
      this.retries = retries;
      return this;
    }

    public LockedExternalTaskFakeBuilder priority(long priority) {
      this.priority = priority;
      return this;
    }

    public LockedExternalTaskFakeBuilder variables(VariableMap variables) {
      this.variables = variables;
      return this;
    }

    public LockedExternalTaskFakeBuilder tenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    public LockedExternalTaskFakeBuilder businessKey(String businessKey) {
      this.businessKey = businessKey;
      return this;
    }

    public LockedExternalTaskFakeBuilder extensionProperties(Map<String, String> extensionProperties) {
      this.extensionProperties = extensionProperties;
      return this;
    }

    public <T> LockedExternalTaskFakeBuilder variable(VariableFactory<T> factory, T value) {
      if (variables == null) {
        variables(Variables.createVariables());
      }

      CamundaBpmData.writer(variables)
        .set(factory, value);
      return this;
    }

    public LockedExternalTaskFake build() {
      return new LockedExternalTaskFake(
        id, topicName, workerId,
        processInstanceId, executionId, lockExpirationTime,
        activityId, activityInstanceId, processDefinitionId,
        processDefinitionKey, processDefinitionVersionTag, retries,
        errorMessage, errorDetails, variables,
        tenantId, priority, businessKey,
        extensionProperties
      );
    }

    @Override
    public String toString() {
      return "LockedExternalTaskFake.LockedExternalTaskFakeBuilder{" +
        "id='" + id + '\'' +
        ", topicName='" + topicName + '\'' +
        ", workerId='" + workerId + '\'' +
        ", processInstanceId='" + processInstanceId + '\'' +
        ", executionId='" + executionId + '\'' +
        ", lockExpirationTime=" + lockExpirationTime +
        ", activityId='" + activityId + '\'' +
        ", activityInstanceId='" + activityInstanceId + '\'' +
        ", processDefinitionId='" + processDefinitionId + '\'' +
        ", processDefinitionKey='" + processDefinitionKey + '\'' +
        ", processDefinitionVersionTag='" + processDefinitionVersionTag + '\'' +
        ", retries=" + retries +
        ", errorMessage='" + errorMessage + '\'' +
        ", errorDetails='" + errorDetails + '\'' +
        ", variables=" + variables +
        ", tenantId='" + tenantId + '\'' +
        ", priority=" + priority +
        ", businessKey='" + businessKey + '\'' +
        ", extensionProperties=" + extensionProperties +
        '}';
    }
  }
}
