package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.mockito.function.DeployProcess;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareAssertions.assertThat;
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
        execution.setVariable("foo", "bar");
      })
      .deploy(rule);

    final ProcessInstance processInstance = startProcess(PROCESS_ID);
    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = runtimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(1);
    assertThat(variables).containsEntry("foo", "bar");
  }

  private ProcessInstance startProcess(final String key) {
    final ProcessInstance processInstance = rule.getRuntimeService().startProcessInstanceByKey(key);
    assertThat(processInstance).isWaitingAt("user_task");
    return processInstance;
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
    //TODO doesn't work with current camunda-bpm-assert version (1.*) and our assertj version (3.*)
    //assertThat(processInstance).hasVariables("foo", "bar");
    final Map<String, Object> variables = runtimeService().getVariables(processInstance.getId());
    assertThat(variables).hasSize(2);
    assertThat(variables).containsEntry("foo", "bar");
    assertThat(variables).containsEntry("bar", "foo");
  }
}
