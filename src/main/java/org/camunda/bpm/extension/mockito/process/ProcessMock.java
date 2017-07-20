package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.extension.mockito.Expressions;
import org.camunda.bpm.extension.mockito.function.DeployProcess;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ProcessMock {

  private String processId;
  private Set<Consumer<DelegateExecution>> mockServiceFunctions = new LinkedHashSet<>();

  public ProcessMock(String processId) {
    this.processId = processId;
  }

  public ProcessMock onExecutionSetVariables(final VariableMap variables){
    mockServiceFunctions.add((execution) -> {
      execution.setVariables(variables);
    });
    return this;
  }

  public ProcessMock onExecutionAddVariables(final VariableMap variables){
    mockServiceFunctions.add((execution) -> {
      variables.forEach(execution::setVariable);
    });
    return this;
  }

  public ProcessMock onExecutionAddVariable(final String key, final Object val){
    mockServiceFunctions.add((execution) -> {
      execution.setVariable(key, val);
    });
    return this;
  }

  public ProcessMock onExecutionDo(final Consumer<DelegateExecution> consumer) {
    mockServiceFunctions.add(consumer);
    return this;
  }

  public void deploy(ProcessEngineRule rule){
    DeployProcess.INSTANCE.apply(rule, initBpmModel(), this.processId);
  }

  private BpmnModelInstance initBpmModel() {
    final String mockServiceTaskId = processId + "MockServiceTask";

    Expressions.registerInstance(mockServiceTaskId, (JavaDelegate)execution -> {
      mockServiceFunctions.forEach(f -> f.accept(execution));
    });

    return Bpmn.createExecutableProcess(processId)
      .startEvent("start")
      .serviceTask(processId + "_serviceTask")
        .camundaDelegateExpression("${id}".replace("id", mockServiceTaskId))
      .endEvent("end")
      .done();
  }
}
