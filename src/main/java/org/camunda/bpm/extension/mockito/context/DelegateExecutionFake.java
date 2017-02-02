package org.camunda.bpm.extension.mockito.context;


import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowElement;

/**
 * Fake delegateExecution to test simple delegates/listeners without mocking.
 * <p>
 * Not all operations are support, extend if needed.
 */
public class DelegateExecutionFake extends VariableScopeFake implements DelegateExecution {

  @Override
  public String getProcessInstanceId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getProcessBusinessKey() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getProcessDefinitionId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getParentId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getCurrentActivityId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getCurrentActivityName() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getActivityInstanceId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getParentActivityInstanceId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getCurrentTransitionId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public DelegateExecution getProcessInstance() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public DelegateExecution getSuperExecution() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public boolean isCanceled() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getTenantId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void setVariable(String variableName, Object value, String activityId) {
    setVariable(variableName, value);
  }

  @Override
  public String getId() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getEventName() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public String getBusinessKey() {
    throw new UnsupportedOperationException("not implemented");
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
    throw new UnsupportedOperationException("not implemented");
  }

  public static class ProcessInstanceNotSetException extends IllegalStateException {
    // empty
  }

}
