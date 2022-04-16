package org.camunda.community.mockito.cases;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class CaseInstanceFakeBuilder {
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
  private final VariableMap variables = Variables.createVariables();

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

  public CaseInstanceFakeBuilder variable(String key, Object value) {
    variables.putValue(key, value);
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
      parentId, activityDescription, tenantId, variables
    );
  }
}
