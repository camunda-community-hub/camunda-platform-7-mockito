package org.camunda.bpm.extension.mockito.cases;

import org.camunda.bpm.engine.runtime.CaseExecution;

public class CaseExecutionFake implements CaseExecution {
  private final String id;
  private final String caseInstanceId;
  private final String caseDefinitionId;
  private final String activityId;
  private final String activityName;
  private final String activityType;
  private final String activityDescription;
  private final String parentId;
  private final String tenantId;

  private boolean disabled;
  private boolean terminated;
  private boolean required;
  private boolean enabled;
  private boolean active;
  private boolean available;

  public static CaseExecutionFakeBuilder builder() {
    return new CaseExecutionFakeBuilder();
  }

  public CaseExecutionFake(String id, String caseInstanceId, String caseDefinitionId, String activityId, String activityName,
                           String activityType, String activityDescription, String parentId, String tenantId,
                           boolean disabled, boolean terminated, boolean required,
                           boolean enabled, boolean active, boolean available) {
    this.id = id;
    this.caseInstanceId = caseInstanceId;
    this.caseDefinitionId = caseDefinitionId;
    this.activityId = activityId;
    this.activityName = activityName;
    this.activityType = activityType;
    this.activityDescription = activityDescription;
    this.parentId = parentId;
    this.tenantId = tenantId;
    this.disabled = disabled;
    this.terminated = terminated;
    this.required = required;
    this.enabled = enabled;
    this.active = active;
    this.available = available;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getCaseInstanceId() {
    return caseInstanceId;
  }

  @Override
  public String getCaseDefinitionId() {
    return caseDefinitionId;
  }

  @Override
  public String getActivityId() {
    return activityId;
  }

  @Override
  public String getActivityName() {
    return activityName;
  }

  @Override
  public String getActivityType() {
    return activityType;
  }

  @Override
  public String getActivityDescription() {
    return activityDescription;
  }

  @Override
  public String getParentId() {
    return parentId;
  }

  @Override
  public boolean isRequired() {
    return required;
  }

  @Override
  public boolean isAvailable() {
    return available;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public boolean isDisabled() {
    return disabled;
  }

  @Override
  public boolean isTerminated() {
    return terminated;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public void setTerminated(boolean terminated) {
    this.terminated = terminated;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public static class CaseExecutionFakeBuilder {

    private boolean disabled = false;
    private boolean terminated = false;
    private boolean required = false;
    private boolean enabled = false;
    private boolean active = false;
    private boolean available = false;

    private String id;
    private String caseInstanceId;
    private String caseDefinitionId;
    private String activityId;
    private String activityName;
    private String activityType;
    private String activityDescription;
    private String parentId;
    private String tenantId;

    public CaseExecutionFakeBuilder disabled(boolean disabled) {
      this.disabled = disabled;
      return this;
    }

    public CaseExecutionFakeBuilder terminated(boolean terminated) {
      this.terminated = terminated;
      return this;
    }

    public CaseExecutionFakeBuilder required(boolean required) {
      this.required = required;
      return this;
    }

    public CaseExecutionFakeBuilder enabled(boolean enabled) {
      this.enabled = enabled;
      return this;
    }

    public CaseExecutionFakeBuilder active(boolean active) {
      this.active = active;
      return this;
    }

    public CaseExecutionFakeBuilder available(boolean available) {
      this.available = available;
      return this;
    }

    public CaseExecutionFakeBuilder id(String id) {
      this.id = id;
      return this;
    }

    public CaseExecutionFakeBuilder caseInstanceId(String caseInstanceId) {
      this.caseInstanceId = caseInstanceId;
      return this;
    }

    public CaseExecutionFakeBuilder caseDefinitionId(String caseDefinitionId) {
      this.caseDefinitionId = caseDefinitionId;
      return this;
    }

    public CaseExecutionFakeBuilder activityId(String activityId) {
      this.activityId = activityId;
      return this;
    }

    public CaseExecutionFakeBuilder activityName(String activityName) {
      this.activityName = activityName;
      return this;
    }

    public CaseExecutionFakeBuilder activityType(String activityType) {
      this.activityType = activityType;
      return this;
    }

    public CaseExecutionFakeBuilder parentId(String parentId) {
      this.parentId = parentId;
      return this;
    }

    public CaseExecutionFakeBuilder activityDescription(String activityDescription) {
      this.activityDescription = activityDescription;
      return this;
    }

    public CaseExecutionFakeBuilder tenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    @Override
    public String toString() {
      return "CaseInstanceFakeBuilder{" +
        "disabled=" + disabled +
        ", terminated=" + terminated +
        ", required=" + required +
        ", enabled=" + enabled +
        ", active=" + active +
        ", available=" + available +
        ", id='" + id + '\'' +
        ", caseInstanceId='" + caseInstanceId + '\'' +
        ", caseDefinitionId='" + caseDefinitionId + '\'' +
        ", activityId='" + activityId + '\'' +
        ", activityName='" + activityName + '\'' +
        ", activityType='" + activityType + '\'' +
        ", parentId='" + parentId + '\'' +
        ", activityDescription='" + activityDescription + '\'' +
        ", tenantId='" + tenantId + '\'' +
        '}';
    }

    public CaseExecutionFake build() {
      return new CaseExecutionFake(id, caseInstanceId, caseDefinitionId, activityId, activityName, activityType, activityDescription,
        parentId, tenantId, disabled, terminated, required, enabled, active, available
      );
    }
  }
}
