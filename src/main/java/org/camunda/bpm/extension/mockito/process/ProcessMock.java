package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.extension.mockito.function.DeployProcess;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.builder.AbstractFlowNodeBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.function.Consumer;

import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.camunda.bpm.extension.mockito.Expressions.registerInstance;

public class ProcessMock {

  private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  private String processId;
  private AbstractFlowNodeBuilder flowNodeBuilder;

  public ProcessMock(String processId) {
    this.processId = processId;
    this.flowNodeBuilder = Bpmn.createExecutableProcess(processId)
      .startEvent("start");
  }

  public ProcessMock onExecutionSetVariables(final VariableMap variables){
    return this.onExecutionDo("setVariablesServiceMock_"+ randomUUID(),
      (execution) -> execution.setVariables(variables)
    );
  }

  public ProcessMock onExecutionAddVariables(final VariableMap variables){
    return this.onExecutionDo("addVariablesServiceMock_"+ randomUUID(),
      (execution) -> variables.forEach(execution::setVariable)
    );
  }

  public ProcessMock onExecutionAddVariable(final String key, final Object val){
    return this.onExecutionAddVariables(createVariables().putValue(key, val));
  }

  public ProcessMock onExecutionDo(final Consumer<DelegateExecution> consumer) {
    return this.onExecutionDo("serviceMock_"+ randomUUID(),
      consumer
    );
  }

  public ProcessMock onExecutionDo(final String serviceId, final Consumer<DelegateExecution> consumer) {
    flowNodeBuilder = flowNodeBuilder.serviceTask(serviceId)
      .camundaDelegateExpression("${id}".replace("id", serviceId));

    registerInstance(serviceId, (JavaDelegate)execution -> {
      consumer.accept(execution);
    });
    return this;
  }

  public ProcessMock onExecutionWaitForTimerWithCycle(final String iso8601cycle) {
    flowNodeBuilder = flowNodeBuilder.intermediateCatchEvent()
      .timerWithCycle(iso8601cycle);
    return this;
  }

  /**
   * On execution, the process will wait on a timer which is configured with an date
   *
   * @param date
   * @return
   */
  public ProcessMock onExecutionWaitForTimerWithDate(final Date date) {
    return this.onExecutionWaitForTimerWithDate(df.format(date));
  }

  /**
   * On execution, the process will wait on a timer which is configured with an ISO 8601 date.
   * E.g.: 2018-10-14T14:10:00Z
   *
   * @param iso8601date
   * @return
   */
  public ProcessMock onExecutionWaitForTimerWithDate(final String iso8601date) {
    flowNodeBuilder = flowNodeBuilder.intermediateCatchEvent()
      .timerWithDate(iso8601date);
    return this;
  }

  /**
   * On execution, the process will wait on a timer which is configured with an ISO 8601 duration.
   * E.g.: PT60S ... will wait for 60 sec
   *
   * @param iso8601duration
   * @return
   */
  public ProcessMock onExecutionWaitForTimerWithDuration(final String iso8601duration) {
    flowNodeBuilder = flowNodeBuilder.intermediateCatchEvent()
      .timerWithDuration(iso8601duration);
    return this;
  }

  public ProcessMock onExecutionSendMessage(final String message) {
    return this;
  }

  public ProcessMock onExecutionWaitForMessage(final String message) {
    return this;
  }

  public ProcessMock onExecutionRunIntoError(final Throwable exception) {
    return this.onExecutionDo("throwErrorServiceMock",execution -> {
      throw new RuntimeException(exception);
    });
  }

  public Deployment deploy(ProcessEngineRule rule){
    return DeployProcess.INSTANCE.apply(rule,
      flowNodeBuilder
        .endEvent("end")
        .done(),
      this.processId);
  }

  private String randomUUID() {
    return UUID.randomUUID().toString().substring(0, 8);
  }
}
