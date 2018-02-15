package org.camunda.bpm.extension.mockito.delegate;


import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Fake delegateExecution to test simple delegates/listeners without mocking.
 * <p>
 * Not all operations are support, extend if needed.
 */
public class DelegateExecutionFake extends VariableScopeFake<DelegateExecutionFake> implements DelegateExecution {

  private String processInstanceId;
  private ProcessEngineServices processEngineServices;
  private String processBusinessKey;
  private String processDefinitionId;
  private String parentId;
  private String currentActivityId;
  private String currentActivityName;
  private String activityInstanceId;
  private String parentActivityInstanceId;
  private String currentTransitionId;
  private DelegateExecution processInstance;
  private DelegateExecution superExecution;
  private boolean canceled;
  private String tenantId;
  private String id;
  private String eventName;
  private String businessKey;

  private final Map<String,Incident> incidents = new HashMap<>();

  @Override
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public DelegateExecutionFake withProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

  @Override
  public String getProcessBusinessKey() {
    return processBusinessKey;
  }
  public DelegateExecutionFake withProcessBusinessKey(String processBusinessKey) {
    this.processBusinessKey = processBusinessKey;
    return this;
  }

  @Override
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  public DelegateExecutionFake withProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

  @Override
  public String getParentId() {
    return parentId;
  }
  public DelegateExecutionFake withParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  @Override
  public String getCurrentActivityId() {
    return currentActivityId;
  }
  public DelegateExecutionFake withCurrentActivityId(String currentActivityId) {
    this.currentActivityId = currentActivityId;
    return this;
  }

  @Override
  public String getCurrentActivityName() {
    return currentActivityName;
  }
  public DelegateExecutionFake withCurrentActivityName(String currentActivityName) {
    this.currentActivityName = currentActivityName;
    return this;
  }

  @Override
  public String getActivityInstanceId() {
    return activityInstanceId;
  }
  public DelegateExecutionFake withActivityInstanceId(String activityInstanceId) {
    this.activityInstanceId = activityInstanceId;
    return this;
  }

  @Override
  public String getParentActivityInstanceId() {
    return parentActivityInstanceId;
  }
  public DelegateExecutionFake withParentActivityInstanceId(String parentActivityInstanceId) {
    this.parentActivityInstanceId = parentActivityInstanceId;
    return this;
  }

  @Override
  public String getCurrentTransitionId() {
    return currentTransitionId;
  }
  public DelegateExecutionFake withCurrentTransitionId(String currentTransitionId) {
    this.currentTransitionId = currentTransitionId;
    return this;
  }

  @Override
  public DelegateExecution getProcessInstance() {
    return processInstance;
  }
  public DelegateExecutionFake withProcessInstance(DelegateExecution processInstance) {
    this.processInstance = processInstance;
    return this;
  }

  @Override
  public DelegateExecution getSuperExecution() {
    return superExecution;
  }
  public DelegateExecutionFake withSuperExecution(DelegateExecution  superExecution) {
    this.superExecution = superExecution;
    return this;
  }

  @Override
  public boolean isCanceled() {
    return canceled;
  }
  public DelegateExecutionFake withCanceled(boolean canceled) {
    this.canceled = canceled;
    return this;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }
  public DelegateExecutionFake withTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  @Override
  public void setVariable(String variableName, Object value, String activityId) {
    setVariable(variableName, value);
  }

  @Override
  public Incident createIncident(String incidentType, String configuration) {
    return createIncident(incidentType, configuration, null);
  }

  @Override
  public Incident createIncident(String incidentType, String configuration, String message) {
    IncidentFake incident = new IncidentFake(this, incidentType, configuration, message, this.getCurrentActivityId());
    incidents.put(incident.getId(), incident);
    return incident;
  }

  @Override
  public void resolveIncident(String incidentId) {
    incidents.remove(incidentId);
  }

  @Override
  public String getId() {
    return id;
  }
  public DelegateExecutionFake withId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public String getEventName() {
    return eventName;
  }
  public DelegateExecutionFake withEventName(String eventName) {
    this.eventName = eventName;
    return this;
  }

  @Override
  public String getBusinessKey() {
    return businessKey;
  }
  public DelegateExecutionFake withBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  @Override
  public BpmnModelInstance getBpmnModelInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public FlowElement getBpmnModelElementInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    return processEngineServices;
  }

  public DelegateExecutionFake withProcessEngineServices(ProcessEngineServices processEngineServices) {
    this.processEngineServices = processEngineServices;
    return this;
  }

  public Map<String, Incident> getIncidents() {
    return incidents;
  }

  public static class ProcessInstanceNotSetException extends IllegalStateException {
    // empty
  }

}
