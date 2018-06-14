package org.camunda.bpm.extension.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState;
import org.camunda.bpm.model.cmmn.CmmnModelInstance;
import org.camunda.bpm.model.cmmn.instance.CmmnElement;

public class DelegateCaseExecutionFake extends VariableScopeFake<DelegateCaseExecutionFake> implements DelegateCaseExecution {

  private String id;
  private String caseInstanceId;
  private String eventName;
  private String businessKey;
  private String caseBusinessKey;
  private String caseDefinitionId;
  private String parentId;
  private String activityId;
  private String activityName;
  private String tenantId;
  private ProcessEngineServices processEngineServices;
  private CaseExecutionState caseExecutionState;

  public DelegateCaseExecutionFake() {
      this(null);
  }

  public DelegateCaseExecutionFake(String id) {
    withId(id);
  }

  @Override
  public String getId() {
    return id;
  }
  public DelegateCaseExecutionFake withId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public String getCaseInstanceId() {
    return caseInstanceId;
  }
  public DelegateCaseExecutionFake withCaseInstanceId(String caseInstanceId) {
    this.caseInstanceId = caseInstanceId;
    return this;
  }

  @Override
  public String getEventName() {
    return eventName;
  }
  public DelegateCaseExecutionFake withEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  @Override
  public String getBusinessKey() {
    return businessKey;
  }
  public DelegateCaseExecutionFake withBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  @Override
  public String getCaseBusinessKey() {
    return caseBusinessKey;
  }
  public DelegateCaseExecutionFake withCaseBusinessKey(String caseBusinessKey) {
    this.caseBusinessKey = caseBusinessKey;
    return this;
  }

  @Override
  public String getCaseDefinitionId() {
    return caseDefinitionId;
  }
  public DelegateCaseExecutionFake withCaseDefinitionId(String caseDefinitionId) {
    this.caseDefinitionId = caseDefinitionId;
    return this;
  }

  @Override
  public String getParentId() {
    return parentId;
  }
  public DelegateCaseExecutionFake withParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  @Override
  public String getActivityId() {
    return activityId;
  }
  public DelegateCaseExecutionFake withActivityId(String activityId) {
    this.activityId = activityId;
    return this;
  }

  @Override
  public String getActivityName() {
    return activityName;
  }
  public DelegateCaseExecutionFake withActivityName(String activityName) {
    this.activityName = activityName;
    return this;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }
  public DelegateCaseExecutionFake withTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  @Override
  public boolean isAvailable() {
    return caseExecutionState == CaseExecutionState.AVAILABLE;
  }

  @Override
  public boolean isEnabled() {
    return caseExecutionState == CaseExecutionState.ENABLED;
  }

  @Override
  public boolean isDisabled() {
    return caseExecutionState == CaseExecutionState.DISABLED;
  }

  @Override
  public boolean isActive() {
    return caseExecutionState == CaseExecutionState.ACTIVE;
  }

  @Override
  public boolean isSuspended() {
    return caseExecutionState == CaseExecutionState.SUSPENDED;
  }

  @Override
  public boolean isTerminated() {
    return caseExecutionState == CaseExecutionState.TERMINATED;
  }

  @Override
  public boolean isCompleted() {
    return caseExecutionState == CaseExecutionState.COMPLETED;
  }

  @Override
  public boolean isFailed() {
    return caseExecutionState == CaseExecutionState.FAILED;
  }

  @Override
  public boolean isClosed() {
    return caseExecutionState == CaseExecutionState.CLOSED;
  }

  public DelegateCaseExecutionFake withCaseExecutionState(CaseExecutionState caseExecutionState) {
    this.caseExecutionState = caseExecutionState;
    return this;
  }

  @Override
  public CmmnModelInstance getCmmnModelInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public CmmnElement getCmmnModelElementInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    return processEngineServices;
  }

  public DelegateCaseExecutionFake withProcessEngineServices(ProcessEngineServices processEngineServices) {
    this.processEngineServices = processEngineServices;
    return this;
  }
}
