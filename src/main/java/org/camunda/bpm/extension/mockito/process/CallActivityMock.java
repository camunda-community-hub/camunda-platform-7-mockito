package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.extension.mockito.function.DeployProcess;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.builder.AbstractFlowNodeBuilder;
import org.camunda.bpm.model.bpmn.builder.ProcessBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.function.Consumer;

import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.camunda.bpm.extension.mockito.Expressions.registerInstance;

public class CallActivityMock {

	/**
	 * Interface used as a callback to set some attributes of the mocked process model (e.g. versionTag, name etc.)
	 */
	@FunctionalInterface
	public interface MockedModelConfigurer {
		void setProcessModelAttributes(ProcessBuilder processBuilder);
	}

  private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  private final String processId;
  private AbstractFlowNodeBuilder<?, ?> flowNodeBuilder;

  public CallActivityMock(final String processId, final MockedModelConfigurer modelConfigurer) {
    this.processId = processId;
    ProcessBuilder processBuilder = Bpmn.createExecutableProcess(processId);
    if (modelConfigurer != null) {
    	modelConfigurer.setProcessModelAttributes(processBuilder);
    }
    this.flowNodeBuilder = processBuilder.startEvent("start");
  }

  public CallActivityMock(final String processId) {
  	this(processId, null);
  }

  /**
   * On execution, the MockProcess will set the given VariableMap to the execution
   * (ATTENTION: This means all current process variables are replaced with the given Map!)
   *
   * @param variables variables to set
   * @return this mock instance
   */
  public CallActivityMock onExecutionSetVariables(final VariableMap variables){
    return this.onExecutionDo("setVariablesServiceMock_"+ randomUUID(),
      (execution) -> execution.setVariables(variables)
    );
  }

  /**
   * On execution, the MockProcess will add the given VariableMap to the execution
   *
   * @param variables the variables to add
   * @return self
   */
  public CallActivityMock onExecutionAddVariables(final VariableMap variables){
    return this.onExecutionDo("addVariablesServiceMock_"+ randomUUID(),
      (execution) -> variables.forEach(execution::setVariable)
    );
  }

  /**
   * On execution, the MockProcess will add the given process variable
   *
   * @param key ... key of the process variable
   * @param val ... value of the process variable
   * @return self
   */
  public CallActivityMock onExecutionAddVariable(final String key, final Object val){
    return this.onExecutionAddVariables(createVariables().putValue(key, val));
  }

  /**
   * On execution, the MockProcess will call the given consumer with a DelegateExecution.
   *
   * @param consumer the javaDelegate code to be called on execution
   * @return self
   */
  public CallActivityMock onExecutionDo(final Consumer<DelegateExecution> consumer) {
    return this.onExecutionDo("serviceMock_"+ randomUUID(),
      consumer
    );
  }

  /**
   * On execution, the MockProcess will execute the given consumer with a DelegateExecution.
   *
   * @param serviceId ... the id of the mock delegate
   * @param consumer delegate for service task
   * @return self
   */
  public CallActivityMock onExecutionDo(final String serviceId, final Consumer<DelegateExecution> consumer) {
    flowNodeBuilder = flowNodeBuilder.serviceTask(serviceId)
      .camundaDelegateExpression("${id}".replace("id", serviceId));

    registerInstance(serviceId, (JavaDelegate) consumer::accept);
    return this;
  }

  /**
   * On execution, the MockProcess will wait on a timer which is configured with an date
   *
   * @param date the timer dueDate
   * @return self
   */
  public CallActivityMock onExecutionWaitForTimerWithDate(final Date date) {
    return this.onExecutionWaitForTimerWithDate(df.format(date));
  }

  /**
   * On execution, the MockProcess will wait on a timer which is configured with an ISO 8601 date.
   * E.g.: 2018-10-14T14:10:00Z
   *
   * @param iso8601date the timer dueDate
   * @return self
   */
  public CallActivityMock onExecutionWaitForTimerWithDate(final String iso8601date) {
    flowNodeBuilder = flowNodeBuilder.intermediateCatchEvent()
      .timerWithDate(iso8601date);
    return this;
  }

  /**
   * On execution, the MockProcess will wait on a timer which is configured with an ISO 8601 duration.
   * E.g.: PT60S ... will wait for 60 sec
   *
   * @param iso8601duration the timer dueDate
   * @return self
   */
  public CallActivityMock onExecutionWaitForTimerWithDuration(final String iso8601duration) {
    flowNodeBuilder = flowNodeBuilder.intermediateCatchEvent()
      .timerWithDuration(iso8601duration);
    return this;
  }

  /**
   * On execution, the MockProcess will send the given message to all
   *
   * @param message the message to receive
   * @return self
   */
  public CallActivityMock onExecutionSendMessage(final String message) {
    return onExecutionDo(execution -> execution.getProcessEngineServices()
      .getRuntimeService().correlateMessage(message));
  }

  /**
   * On execution, the MockProcess will send the given message to a process instance with the given businessId
   *
   * @param message the message to receive
   * @param businessId the process business key
   * @return self
   */
  public CallActivityMock onExecutionSendMessage(final String message, final String businessId) {
    return onExecutionDo(execution -> execution.getProcessEngineServices()
      .getRuntimeService().correlateMessage(message, businessId));
  }

  /**
   * On execution, the MockProcess will wait for the given message
   *
   * @param message the message to receive
   * @return self
   */
  public CallActivityMock onExecutionWaitForMessage(final String message) {
    flowNodeBuilder = flowNodeBuilder.intermediateCatchEvent()
      .message(message);
    return this;
  }

  /**
   * On execution, the MockProcess will throw a RuntimeException for the given Throwable.
   *
   * @param exception the error to throw
   * @return self
   */
  public CallActivityMock onExecutionRunIntoError(final Throwable exception) {
    return this.onExecutionDo("throwErrorServiceMock",execution -> {
      throw new RuntimeException(exception);
    });
  }

  /**
   * On execution, the MockProcess will wait for the given signal
   *
   * @param signalName the signal to receive
   * @return self
   */
  public CallActivityMock onExecutionWaitForSignal(final String signalName) {
    flowNodeBuilder = flowNodeBuilder.intermediateCatchEvent()
      .signal(signalName);
    return this;
  }


  /**
   * This will deploy the mock process.
   *
   */
  public Deployment deploy(final RepositoryService repositoryService){
    return new DeployProcess(repositoryService).apply(processId, flowNodeBuilder.endEvent("end").done());
  }

  public Deployment deploy(final ProcessEngineServices processEngineServices){
    return this.deploy(processEngineServices.getRepositoryService());
  }

  private String randomUUID() {
    return UUID.randomUUID().toString().substring(0, 8);
  }
}
