package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
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
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.jobQuery;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.camunda.bpm.extension.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;
import static org.camunda.bpm.extension.mockito.ProcessExpressions.registerSubProcessMock;

public class SubprocessMockExample {

  public static final String PROCESS_ID = "myProcess";
  public static final String SUB_PROCESS_ID = "mySubProcess";
  public static final String SUB_PROCESS2_ID = "mySubProcess2";

  @Rule
  public final ProcessEngineRule rule = new ProcessEngineRule(mostUsefulProcessEngineConfiguration().buildProcessEngine());

  @Before
  public void setUp() throws Exception {
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

  @Test
  public void register_subprocess_mock_addVar() throws Exception {
    registerSubProcessMock(SUB_PROCESS_ID)
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
    registerSubProcessMock(SUB_PROCESS_ID)
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
  public void register_subprocess_mock_withTimerDate() throws Exception {
    final Date date = Date.from(Instant.now().plusSeconds(60));

    registerSubProcessMock(SUB_PROCESS_ID)
      .onExecutionWaitForTimerWithDate(date)
      .deploy(rule);

    startProcess(PROCESS_ID);

    final List<Job> list = jobQuery().list();
    assertThat(list).hasSize(1);
    final Job timer = list.get(0);
    assertThat(timer).isInstanceOf(TimerEntity.class);
    assertThat(timer.getDuedate()).isEqualToIgnoringMillis(date);
  }

  @Test
  public void register_subprocess_mock_withTimerDuration() throws Exception {
    registerSubProcessMock(SUB_PROCESS_ID)
      .onExecutionWaitForTimerWithDuration("PT60S")
      .deploy(rule);

    startProcess(PROCESS_ID);

    final List<Job> list = jobQuery().list();
    assertThat(list).hasSize(1);
    final Job timer = list.get(0);
    assertThat(timer).isInstanceOf(TimerEntity.class);
    assertThat(timer.getDuedate()).isEqualToIgnoringMillis(Date.from(Instant.now().plusSeconds(60)));
  }

  @Test(expected = RuntimeException.class)
  public void register_subprocess_mock_withException() throws Exception {
    registerSubProcessMock(SUB_PROCESS_ID)
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

    registerSubProcessMock(SUB_PROCESS_ID)
      .onExecutionSetVariables(createVariables().putValue("foo", "bar"))
      .deploy(rule);
    registerSubProcessMock(SUB_PROCESS2_ID)
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

  private ProcessInstance startProcess(final String key) {
    return rule.getRuntimeService().startProcessInstanceByKey(key);
  }
}
