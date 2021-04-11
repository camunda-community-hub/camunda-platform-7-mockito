package org.camunda.bpm.extension.mockito.delegate;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

/**
 * Fake delegateExecution to test simple delegates/listeners without mocking.
 * <p>
 * Not all operations are support, extend if needed.
 */
@SuppressWarnings({"WeakerAccess","UnusedReturnValue", "unused"})
public class DelegateExecutionFake extends DelegateFake<DelegateExecutionFake> implements DelegateExecution {

  private static final long serialVersionUID = -8413557219169444178L;
  private String processInstanceId;
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
  private final FlowElement flowElement = Mockito.mock(FlowElement.class, RETURNS_DEEP_STUBS);

  private final Map<String,Incident> incidents = new HashMap<>();

  public DelegateExecutionFake() {
    this(null);
  }

  public DelegateExecutionFake(final String id) {
    withId(id);
  }

  public static DelegateExecutionFake of() {
    return new DelegateExecutionFake();
  }

  @Override
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public DelegateExecutionFake withProcessInstanceId(final String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

  @Override
  public String getProcessBusinessKey() {
    return processBusinessKey;
  }

  @Override
  public void setProcessBusinessKey(String processBusinessKey) {
    withProcessBusinessKey(processBusinessKey);
  }

  public DelegateExecutionFake withProcessBusinessKey(final String processBusinessKey) {
    this.processBusinessKey = processBusinessKey;
    return this;
  }

  @Override
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  public DelegateExecutionFake withProcessDefinitionId(final String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

  @Override
  public String getParentId() {
    return parentId;
  }
  public DelegateExecutionFake withParentId(final String parentId) {
    this.parentId = parentId;
    return this;
  }

  @Override
  public String getCurrentActivityId() {
    return currentActivityId;
  }
  public DelegateExecutionFake withCurrentActivityId(final String currentActivityId) {
    this.currentActivityId = currentActivityId;
    return this;
  }

  @Override
  public String getCurrentActivityName() {
    return currentActivityName;
  }
  public DelegateExecutionFake withCurrentActivityName(final String currentActivityName) {
    this.currentActivityName = currentActivityName;
    return this;
  }

  @Override
  public String getActivityInstanceId() {
    return activityInstanceId;
  }
  public DelegateExecutionFake withActivityInstanceId(final String activityInstanceId) {
    this.activityInstanceId = activityInstanceId;
    return this;
  }

  @Override
  public String getParentActivityInstanceId() {
    return parentActivityInstanceId;
  }
  public DelegateExecutionFake withParentActivityInstanceId(final String parentActivityInstanceId) {
    this.parentActivityInstanceId = parentActivityInstanceId;
    return this;
  }

  @Override
  public String getCurrentTransitionId() {
    return currentTransitionId;
  }
  public DelegateExecutionFake withCurrentTransitionId(final String currentTransitionId) {
    this.currentTransitionId = currentTransitionId;
    return this;
  }

  @Override
  public DelegateExecution getProcessInstance() {
    return processInstance;
  }
  public DelegateExecutionFake withProcessInstance(final DelegateExecution processInstance) {
    this.processInstance = processInstance;
    return this;
  }

  @Override
  public DelegateExecution getSuperExecution() {
    return superExecution;
  }
  public DelegateExecutionFake withSuperExecution(final DelegateExecution  superExecution) {
    this.superExecution = superExecution;
    return this;
  }

  @Override
  public boolean isCanceled() {
    return canceled;
  }
  public DelegateExecutionFake withCanceled(final boolean canceled) {
    this.canceled = canceled;
    return this;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }
  public DelegateExecutionFake withTenantId(final String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  @Override
  public void setVariable(final String variableName, final Object value, final String activityId) {
    setVariable(variableName, value);
  }

  @Override
  public Incident createIncident(final String incidentType, final String configuration) {
    return createIncident(incidentType, configuration, null);
  }

  @Override
  public Incident createIncident(final String incidentType, final String configuration, final String message) {
    final IncidentFake incident = new IncidentFake(this, incidentType, configuration, message, this.getCurrentActivityId(), null, currentActivityId, null);
    incidents.put(incident.getId(), incident);
    return incident;
  }

  @Override
  public void resolveIncident(final String incidentId) {
    incidents.remove(incidentId);
  }

  @Override
  public String getId() {
    return id;
  }
  public DelegateExecutionFake withId(final String id) {
    this.id = id;
    return this;
  }

  @Override
  public String getEventName() {
    return eventName;
  }
  public DelegateExecutionFake withEventName(final String eventName) {
    this.eventName = eventName;
    return this;
  }

  @Override
  public String getBusinessKey() {
    return businessKey;
  }
  public DelegateExecutionFake withBusinessKey(final String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  @Override
  public BpmnModelInstance getBpmnModelInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public FlowElement getBpmnModelElementInstance() {
    return flowElement;
  }

  public DelegateExecutionFake withBpmnModelElementInstanceType(String type) {
    when(flowElement.getElementType().getTypeName()).thenReturn(type);
    return this;
  }


  public Map<String, Incident> getIncidents() {
    return incidents;
  }

  public static class ProcessInstanceNotSetException extends IllegalStateException {
    private static final long serialVersionUID = 1739284977147050452L;
    // empty
  }

  @Override public String toString() {
    return "DelegateExecutionFake{" +
      "processInstanceId='" + processInstanceId + '\'' +
      ", processEngine='" + getProcessEngine() + '\'' +
      ", processEngineServices=" + getProcessEngineServices() +
      ", processBusinessKey='" + processBusinessKey + '\'' +
      ", processDefinitionId='" + processDefinitionId + '\'' +
      ", parentId='" + parentId + '\'' +
      ", currentActivityId='" + currentActivityId + '\'' +
      ", currentActivityName='" + currentActivityName + '\'' +
      ", activityInstanceId='" + activityInstanceId + '\'' +
      ", parentActivityInstanceId='" + parentActivityInstanceId + '\'' +
      ", currentTransitionId='" + currentTransitionId + '\'' +
      ", processInstance=" + processInstance +
      ", superExecution=" + superExecution +
      ", canceled=" + canceled +
      ", tenantId='" + tenantId + '\'' +
      ", id='" + id + '\'' +
      ", eventName='" + eventName + '\'' +
      ", businessKey='" + businessKey + '\'' +
      ", incidents=" + incidents +
      '}';
  }
}
