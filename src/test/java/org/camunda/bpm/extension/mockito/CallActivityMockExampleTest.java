package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.mockito.function.DeployProcess;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.jobQuery;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.camunda.bpm.extension.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;
import static org.camunda.bpm.extension.mockito.ProcessExpressions.registerCallActivityMock;

public class CallActivityMockExampleTest {

  public static final String PROCESS_ID = "myProcess";
  public static final String SUB_PROCESS_ID = "mySubProcess";
  public static final String SUB_PROCESS2_ID = "mySubProcess2";
  public static final String MESSAGE_DOIT = "DOIT";

  @Rule
  public final ProcessEngineRule rule = new ProcessEngineRule(mostUsefulProcessEngineConfiguration().buildProcessEngine());

  @Before
  public void setUp() throws Exception {
    prepareProcessWithOneSubprocess();
  }

  @Test
  public void register_subprocess_mock_addVar() throws Exception {
    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionAddVariable("foo", "bar")
      .deploy(rule);

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    assertThat(processInstance).isWaitingAt("user_task");

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = runtimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(1);
    assertThat(variables).containsEntry("foo", "bar");
  }

  @Test
  public void register_subprocess_mock_withOwnConsumer() throws Exception {
    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionDo(execution -> {
        execution.setVariable("foo", "barbar");
      })
      .deploy(rule);

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    assertThat(processInstance).isWaitingAt("user_task");

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = runtimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(1);
    assertThat(variables).containsEntry("foo", "barbar");
  }

  @Test
  public void register_subprocess_mock_withReceiveMessage() throws Exception {
    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForMessage(MESSAGE_DOIT)
      .deploy(rule);

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    assertThatProcessIsWaitingForMessage(MESSAGE_DOIT);

    runtimeService().correlateMessage(MESSAGE_DOIT);
    assertThat(processInstance).isWaitingAt("user_task");
  }

  @Test
  public void register_subprocess_mock_withSendMessage() throws Exception {
    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionSendMessage(MESSAGE_DOIT)
      .deploy(rule);

    final String waitForMessageId = "waitForMessage";
    final BpmnModelInstance waitForMessage = Bpmn.createExecutableProcess(waitForMessageId)
      .startEvent("start")
      .intermediateCatchEvent("waitForMessageCatchEvent")
      .message(MESSAGE_DOIT)
      .endEvent("end")
      .done();

    DeployProcess.INSTANCE.apply(rule, waitForMessage, waitForMessageId);

    //Start monitoring process for testing
    final ProcessInstance waitingProcessInstance = startProcess(waitForMessageId);
    assertThatProcessIsWaitingForMessage(MESSAGE_DOIT);

    //Start our process with mocked subprocess
    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    assertThat(processInstance).isWaitingAt("user_task");
    //Our monitoring process should be finished
    assertThat(waitingProcessInstance).isEnded();
  }

  @Test
  public void register_subprocess_mock_withTimerDate() throws Exception {
    final Date date = Date.from(Instant.now().plusSeconds(60));

    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForTimerWithDate(date)
      .deploy(rule);

    startProcess(PROCESS_ID);

    assertThatTimerIsWaitingUntil(date);
  }

  @Test
  public void register_subprocess_mock_withTimerDuration() throws Exception {
    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForTimerWithDuration("PT60S")
      .deploy(rule);

    startProcess(PROCESS_ID);

    assertThatTimerIsWaitingUntil(Date.from(Instant.now().plusSeconds(60)));
  }

  @Test(expected = RuntimeException.class)
  public void register_subprocess_mock_withException() throws Exception {
    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionRunIntoError(new Exception("No"))
      .deploy(rule);

    startProcess(PROCESS_ID);
  }

  @Test
  public void register_subprocesses_mocks_withVariables() throws Exception {
    final BpmnModelInstance processWithSubProcess = Bpmn.createExecutableProcess(PROCESS_ID)
      .startEvent("start")
      .callActivity("call_subprocess")
        .camundaOut("foo", "foo")
        .calledElement(SUB_PROCESS_ID)
      .callActivity("call_subprocess2")
        .calledElement(SUB_PROCESS2_ID)
        .camundaOut("bar", "bar")
      .userTask("user_task")
      .endEvent("end")
      .done();

    DeployProcess.INSTANCE.apply(rule, processWithSubProcess, PROCESS_ID);

    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionSetVariables(createVariables().putValue("foo", "bar"))
      .deploy(rule);
    registerCallActivityMock(SUB_PROCESS2_ID)
      .onExecutionSetVariables(createVariables().putValue("bar", "foo"))
      .deploy(rule);

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    assertThat(processInstance).isWaitingAt("user_task");

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = runtimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(2);
    assertThat(variables).containsEntry("foo", "bar");
    assertThat(variables).containsEntry("bar", "foo");
  }

  @Test
  public void register_subprocesses_mocks_withWaitMessage_and_timer_and_setVariable() throws Exception {
    prepareProcessWithOneSubprocess();

    final Date waitUntil = Date.from(Instant.now().plusSeconds(60));
    registerCallActivityMock(SUB_PROCESS_ID)
      .onExecutionWaitForMessage(MESSAGE_DOIT)
      .onExecutionWaitForTimerWithDate(waitUntil)
      .onExecutionSetVariables(createVariables().putValue("foo", "bar"))
      .deploy(rule);

    final ProcessInstance processInstance = startProcess(PROCESS_ID);

    //Message should wait for message
    assertThatProcessIsWaitingForMessage(MESSAGE_DOIT);
    runtimeService().correlateMessage(MESSAGE_DOIT);

    //Message should wait for date
    Job job = assertThatTimerIsWaitingUntil(waitUntil);
    execute(job);

    //Process should wait at user task
    assertThat(processInstance).isWaitingAt("user_task");

    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = runtimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(1);
    assertThat(variables).containsEntry("foo", "bar");
  }

  private void prepareProcessWithOneSubprocess() {
    final BpmnModelInstance processWithSubProcess = Bpmn.createExecutableProcess(PROCESS_ID)
      .startEvent("start")
      .callActivity("call_subprocess")
        .camundaOut("foo", "foo")
        .calledElement(SUB_PROCESS_ID)
      .userTask("user_task")
      .endEvent("end")
      .done();

    DeployProcess.INSTANCE.apply(rule, processWithSubProcess, PROCESS_ID);
  }

  private void assertThatProcessIsWaitingForMessage(String message) {
    final EventSubscription eventSubscription = runtimeService().createEventSubscriptionQuery().singleResult();
    assertThat(eventSubscription).isNotNull();
    assertThat(eventSubscription.getEventName()).isEqualTo(message);
  }

  private Job assertThatTimerIsWaitingUntil(Date date) {
    final List<Job> list = jobQuery().list();
    assertThat(list).hasSize(1);
    final Job timer = list.get(0);
    assertThat(timer).isInstanceOf(TimerEntity.class);
    assertThat(timer.getDuedate()).isEqualToIgnoringMillis(date);
    return timer;
  }

  private ProcessInstance startProcess(final String key) {
    return rule.getRuntimeService().startProcessInstanceByKey(key);
  }
}
