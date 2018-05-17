package org.camunda.bpm.extension.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.CaseVariableListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.delegate.DelegateCaseVariableInstance;
import org.camunda.bpm.engine.variable.impl.value.AbstractTypedValue;
import org.camunda.bpm.engine.variable.type.ValueType;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.Optional;

/**
 * This {@link DelegateCaseVariableInstance} is useful when testing {@link CaseVariableListener}s in case plan items.
 */
public class DelegateCaseVariableInstanceFake implements DelegateCaseVariableInstance {

  private String id;
  private String name;
  private String eventName;
  private DelegateCaseExecution sourceExecution;
  private ProcessEngineServices processEngineServices;
  private TypedValue typedValue;
  private String processInstanceId;
  private String executionId;
  private String caseInstanceId;
  private String caseExecutionId;
  private String taskId;
  private String activityInstanceId;
  private String errorMessage;
  private String tenantId;

  public DelegateCaseVariableInstanceFake create(String name, TypedValue value) {
    return forEvent(CaseVariableListener.CREATE, name, value);
  }

  public DelegateCaseVariableInstanceFake update(String name, TypedValue value) {
    return forEvent(CaseVariableListener.UPDATE, name, value);
  }

  public DelegateCaseVariableInstanceFake delete(String name, TypedValue typedValue) {
    return forEvent(CaseVariableListener.DELETE, name, typedValue);
  }

  private DelegateCaseVariableInstanceFake forEvent(String eventName, String name, TypedValue value) {
    return withEventName(eventName)
      .withName(name)
      .withValue(value);
  }

  @Override
  public String getEventName() {
    return eventName;
  }
  public DelegateCaseVariableInstanceFake withEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  @Override
  public DelegateCaseExecution getSourceExecution() {
    return sourceExecution;
  }
  public DelegateCaseVariableInstanceFake withSourceExecution(DelegateCaseExecution sourceExecution) {
    this.sourceExecution = sourceExecution;
    return this;
  }

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    return processEngineServices;
  }

  public DelegateCaseVariableInstanceFake withProcessEngineServices(ProcessEngineServices processEngineServices) {
    this.processEngineServices = processEngineServices;
    return this;
  }

  @Override
  public String getId() {
    return id;
  }
  public DelegateCaseVariableInstanceFake withId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public String getName() {
    return name;
  }
  public DelegateCaseVariableInstanceFake withName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String getTypeName() {
    return Optional.ofNullable(typedValue).map(TypedValue::getType).map(ValueType::getName).orElse("undefined");
  }

  @Override
  public Object getValue() {
    return typedValue.getValue();
  }

  @Override
  public TypedValue getTypedValue() {
    return typedValue;
  }

  public DelegateCaseVariableInstanceFake withValue(Object value) {
    return withValue(new AbstractTypedValue<>(value, null));
  }

  public DelegateCaseVariableInstanceFake withValue(TypedValue typedValue) {
    this.typedValue = typedValue;
    return this;
  }


  @Override
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public DelegateCaseVariableInstanceFake withProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

  @Override
  public String getExecutionId() {
    return executionId;
  }
  public DelegateCaseVariableInstanceFake withExecutionId(String executionId) {
    this.executionId = executionId;
    return this;
  }

  @Override
  public String getCaseInstanceId() {
    return caseInstanceId;
  }
  public DelegateCaseVariableInstanceFake withCaseInstanceId(String caseInstanceId) {
    this.caseInstanceId = caseInstanceId;
    return this;
  }

  @Override
  public String getCaseExecutionId() {
    return caseExecutionId;
  }
  public DelegateCaseVariableInstanceFake withCaseExecutionId(String caseExecutionId) {
    this.caseExecutionId = caseExecutionId;
    return this;
  }

  @Override
  public String getTaskId() {
    return taskId;
  }
  public DelegateCaseVariableInstanceFake withTaskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  @Override
  public String getActivityInstanceId() {
    return activityInstanceId;
  }
  public DelegateCaseVariableInstanceFake withActivityInstanceId(String activityInstanceId) {
    this.activityInstanceId = activityInstanceId;
    return this;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }
  public DelegateCaseVariableInstanceFake withErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }
  public DelegateCaseVariableInstanceFake withTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
