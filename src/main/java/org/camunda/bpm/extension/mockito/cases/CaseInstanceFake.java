package org.camunda.bpm.extension.mockito.cases;

import org.camunda.bpm.engine.runtime.CaseInstance;

public class CaseInstanceFake implements CaseInstance {

  private boolean disabled;
  private boolean terminated;
  private boolean completed;
  private boolean required;
  private boolean enabled;
  private boolean active;
  private boolean available;
  private final String businessKey;
  private final String id;
  private final String caseInstanceId;
  private final String caseDefinitionId;
  private final String activityId;
  private final String activityName;
  private final String activityType;
  private final String parentId;
  private final String activityDescription;
  private final String tenantId;

  public static CaseInstanceFakeBuilder builder() {
    return new CaseInstanceFakeBuilder();
  }

  public CaseInstanceFake(boolean disabled, boolean terminated, boolean completed, boolean required, boolean enabled,
                          boolean active, boolean available, String businessKey, String id, String caseInstanceId,
                          String caseDefinitionId, String activityId, String activityName, String activityType,
                          String parentId, String activityDescription, String tenantId) {
    this.disabled = disabled;
    this.terminated = terminated;
    this.completed = completed;
    this.required = required;
    this.enabled = enabled;
    this.active = active;
    this.available = available;
    this.businessKey = businessKey;
    this.id = id;
    this.caseInstanceId = caseInstanceId;
    this.caseDefinitionId = caseDefinitionId;
    this.activityId = activityId;
    this.activityName = activityName;
    this.activityType = activityType;
    this.parentId = parentId;
    this.activityDescription = activityDescription;
    this.tenantId = tenantId;
  }

  @Override
  public String getBusinessKey() {
    return businessKey;
  }

  @Override
  public boolean isCompleted() {
    return completed;
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

  public void setCompleted(boolean completed) {
    this.completed = completed;
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

  public static class CaseInstanceFakeBuilder {
    private boolean disabled = false;
    private boolean terminated = false;
    private boolean completed = false;
    private boolean required = false;
    private boolean enabled = false;
    private boolean active = false;
    private boolean available = false;
    private String businessKey;
    private String id;
    private String caseInstanceId;
    private String caseDefinitionId;
    private String activityId;
    private String activityName;
    private String activityType;
    private String parentId;
    private String activityDescription;
    private String tenantId;

    public CaseInstanceFakeBuilder disabled(boolean disabled) {
      this.disabled = disabled;
      return this;
    }

    public CaseInstanceFakeBuilder terminated(boolean terminated) {
      this.terminated = terminated;
      return this;
    }

    public CaseInstanceFakeBuilder completed(boolean completed) {
      this.completed = completed;
      return this;
    }

    public CaseInstanceFakeBuilder required(boolean required) {
      this.required = required;
      return this;
    }

    public CaseInstanceFakeBuilder enabled(boolean enabled) {
      this.enabled = enabled;
      return this;
    }

    public CaseInstanceFakeBuilder active(boolean active) {
      this.active = active;
      return this;
    }

    public CaseInstanceFakeBuilder available(boolean available) {
      this.available = available;
      return this;
    }

    public CaseInstanceFakeBuilder businessKey(String businessKey) {
      this.businessKey = businessKey;
      return this;
    }

    public CaseInstanceFakeBuilder id(String id) {
      this.id = id;
      return this;
    }

    public CaseInstanceFakeBuilder caseInstanceId(String caseInstanceId) {
      this.caseInstanceId = caseInstanceId;
      return this;
    }

    public CaseInstanceFakeBuilder caseDefinitionId(String caseDefinitionId) {
      this.caseDefinitionId = caseDefinitionId;
      return this;
    }

    public CaseInstanceFakeBuilder activityId(String activityId) {
      this.activityId = activityId;
      return this;
    }

    public CaseInstanceFakeBuilder activityName(String activityName) {
      this.activityName = activityName;
      return this;
    }

    public CaseInstanceFakeBuilder activityType(String activityType) {
      this.activityType = activityType;
      return this;
    }

    public CaseInstanceFakeBuilder parentId(String parentId) {
      this.parentId = parentId;
      return this;
    }

    public CaseInstanceFakeBuilder activityDescription(String activityDescription) {
      this.activityDescription = activityDescription;
      return this;
    }

    public CaseInstanceFakeBuilder tenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    @Override
    public String toString() {
      return "CaseInstanceFakeBuilder{" +
        "disabled=" + disabled +
        ", terminated=" + terminated +
        ", completed=" + completed +
        ", required=" + required +
        ", enabled=" + enabled +
        ", active=" + active +
        ", available=" + available +
        ", businessKey='" + businessKey + '\'' +
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

    public CaseInstanceFake build() {
      return new CaseInstanceFake(
        disabled, terminated, completed, required, enabled,
        active, available, businessKey, id, caseInstanceId,
        caseDefinitionId, activityId, activityName, activityType,
        parentId, activityDescription, tenantId
      );
    }
  }
}
