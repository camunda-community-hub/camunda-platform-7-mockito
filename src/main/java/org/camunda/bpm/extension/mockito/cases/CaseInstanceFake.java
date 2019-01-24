package org.camunda.bpm.extension.mockito.cases;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.extension.mockito.QueryMocks;
import org.mockito.ArgumentMatchers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class CaseInstanceFake implements CaseInstance {

  public static CaseInstanceFake randomId() {
    return id(UUID.randomUUID().toString());
  }

  public static CaseInstanceFake id(String id) {
    return CaseInstanceFake.builder().id(id).build();
  }

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
  private final VariableMap variables;


  public static CaseInstanceFakeBuilder builder() {
    return new CaseInstanceFakeBuilder();
  }

  public CaseInstanceFake(boolean disabled, boolean terminated, boolean completed, boolean required, boolean enabled,
                          boolean active, boolean available, String businessKey, String id, String caseInstanceId,
                          String caseDefinitionId, String activityId, String activityName, String activityType,
                          String parentId, String activityDescription, String tenantId, VariableMap variables) {
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
    this.variables = variables;
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

  public Object getVariable(String key) {
    return variables.get(key);
  }

  public CaseInstanceFake setVariable(String key, Object value) {
    variables.putValue(key, value);
    return this;
  }

  @Override
  public String toString() {
    return "CaseInstanceFake{" +
      "disabled=" + disabled +
      ", terminated=" + terminated +
      ", completed=" + completed +
      ", required=" + required +
      ", enabled=" + enabled +
      ", active=" + active +
      ", available=" + available +
      ", activityDescription='" + activityDescription + '\'' +
      ", activityId='" + activityId + '\'' +
      ", activityName='" + activityName + '\'' +
      ", activityType='" + activityType + '\'' +
      ", businessKey='" + businessKey + '\'' +
      ", id='" + id + '\'' +
      ", caseInstanceId='" + caseInstanceId + '\'' +
      ", caseDefinitionId='" + caseDefinitionId + '\'' +
      ", parentId='" + parentId + '\'' +
      ", tenantId='" + tenantId + '\'' +
      '}';
  }

  public static AtomicReference<CaseInstanceFake> prepareMock(CaseService caseServiceMock, String caseInstanceId) {
    // create an instance with id
    AtomicReference<CaseInstanceFake> caseInstanceFake = new AtomicReference<>();

    when(caseServiceMock.createCaseInstanceByKey(anyString(), anyString(), anyMap())).thenAnswer(invocation -> {
      CaseInstanceFakeBuilder builder = builder().caseInstanceId(caseInstanceId)
        .caseDefinitionId(invocation.getArgument(0) + ":1")
        .businessKey(invocation.getArgument(1));

      ((Map<String, Object>) invocation.getArgument(2)).forEach((k, v) -> builder.variable(k, v));

      caseInstanceFake.set(builder.build());

      // let every caseInstance query return our instance
      QueryMocks.mockCaseInstanceQuery(caseServiceMock)
        .singleResult(Optional.ofNullable(caseInstanceFake.get())
          .orElse(null));


      return caseInstanceFake.get();
    });


    // answer getVariable by asking the fakes variableMap
    when(caseServiceMock.getVariable(ArgumentMatchers.eq(caseInstanceId), anyString()))
      .then(invocation -> Optional.ofNullable(caseInstanceFake.get())
        .map(i -> i.getVariable(invocation.getArgument(1))).orElse(null));

    // modify fakes variableMap on setVariable
    doAnswer(invocation -> {
      caseInstanceFake.get().setVariable(invocation.getArgument(1), invocation.getArgument(2));

      return null;
    }).when(caseServiceMock).setVariable(eq(caseInstanceId), anyString(), any());

    return caseInstanceFake;
  }

}
