package org.camunda.community.mockito;

import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.camunda.community.mockito.DelegateExpressions.getExecutionListenerMock;
import static org.camunda.community.mockito.DelegateExpressions.getJavaDelegateMock;
import static org.camunda.community.mockito.DelegateExpressions.getTaskListenerMock;
import static org.camunda.community.mockito.DelegateExpressions.registerExecutionListenerMock;
import static org.camunda.community.mockito.DelegateExpressions.registerJavaDelegateMock;
import static org.camunda.community.mockito.DelegateExpressions.registerTaskListenerMock;
import static org.camunda.community.mockito.DelegateExpressions.verifyExecutionListenerMock;
import static org.camunda.community.mockito.DelegateExpressions.verifyJavaDelegateMock;
import static org.camunda.community.mockito.DelegateExpressions.verifyTaskListenerMock;
import static org.camunda.community.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;
import static org.junit.Assert.assertThat;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.community.mockito.mock.FluentJavaDelegateMock;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class ManualMockProcessTest {

  private final ProcessEngineConfiguration configuration = mostUsefulProcessEngineConfiguration();

  @Rule
  public final ProcessEngineRule processEngineRule = new ProcessEngineRule(mostUsefulProcessEngineConfiguration().buildProcessEngine());

  @Test
  @Deployment(resources = "MockProcess.bpmn")
  public void deploy_and_run_process_with_manually_registered_mocks() {
    registerExecutionListenerMock("startProcess");
    final FluentJavaDelegateMock loadData = registerJavaDelegateMock("loadData");
    registerTaskListenerMock("verifyData").onExecutionSetVariables(createVariables().putValue("foo", "bar"));
    registerExecutionListenerMock("beforeLoadData");

    final ProcessInstance processInstance = processEngineRule.getRuntimeService().startProcessInstanceByKey("process_mock_dummy");

    assertThat(processEngineRule.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult(), CoreMatchers.notNullValue());

    assertThat((String) processEngineRule.getRuntimeService().getVariable(processInstance.getId(), "foo"), CoreMatchers.is("bar"));

    verifyJavaDelegateMock(loadData).executed();
    verifyExecutionListenerMock("startProcess").executed();
    verifyTaskListenerMock("verifyData").executed();

    // See if we can get the registered instances and modify their behavior.
    getJavaDelegateMock("loadData").onExecutionThrowBpmnError("error");
    getTaskListenerMock("verifyData").onExecutionThrowBpmnError("error");
    getExecutionListenerMock("startProcess").onExecutionThrowBpmnError("error");
  }
}
