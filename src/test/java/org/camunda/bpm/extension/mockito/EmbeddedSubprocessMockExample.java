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
import static org.camunda.bpm.extension.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;
import static org.camunda.bpm.extension.mockito.ProcessExpressions.registerEmbeddedSubProcessMock;

public class EmbeddedSubprocessMockExample {

  public static final String PROCESS_ID = "myProcess";
  public static final String SUB_PROCESS_ID = "mySubProcess";
  public static final String SUB_PROCESS2_ID = "mySubProcess2";

  @Rule
  public final ProcessEngineRule rule = new ProcessEngineRule(mostUsefulProcessEngineConfiguration().buildProcessEngine());

  @Before
  public void setUp() throws Exception {
    final BpmnModelInstance processWithSubProcess = Bpmn.createExecutableProcess(PROCESS_ID)
      .startEvent("start")
      .subProcess(SUB_PROCESS_ID)
        .embeddedSubProcess()
        .startEvent()
        .endEvent()
      .subProcessDone()
      .userTask("user_task")
      .endEvent("end")
      .done();

    DeployProcess.INSTANCE.apply(rule, processWithSubProcess, PROCESS_ID);
  }

  @Test
  public void register_embedded_subprocess_mock_addVar() throws Exception {
    registerEmbeddedSubProcessMock(rule, PROCESS_ID, SUB_PROCESS_ID)
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

  private ProcessInstance startProcess(final String key) {
    return rule.getRuntimeService().startProcessInstanceByKey(key);
  }
}
