package org.camunda.community.mockito;

import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.community.mockito.process.CallActivityMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.community.mockito.MostUsefulProcessEngineConfiguration.mostUsefulProcessEngineConfiguration;

public class CallActivityMockBindingDeploymentTest {

  public static final String KEY = "process_with_callActivity_binding_deployment";
  public static final String KEY_MOCK = "do_stuff_mock";

  @Rule
  public final ProcessEngineRule camunda = new ProcessEngineRule(mostUsefulProcessEngineConfiguration().buildProcessEngine());

  @Before
  public void setUp() {
    DeploymentBuilder deploymentBuilder = camunda.getRepositoryService().createDeployment();
    deploymentBuilder.addClasspathResource("process_with_callActivity_binding_deployment.bpmn");
    CallActivityMock mock = CamundaMockito.registerCallActivityMock(KEY_MOCK).onExecutionAddVariable("foo", "bar");
    mock.addToDeployment(deploymentBuilder);

    camunda.manageDeployment(deploymentBuilder.deploy());
  }

  @Test
  public void mock_runs_with_binding_deployment() {
    ProcessInstance processInstance = camunda.getRuntimeService().startProcessInstanceByKey(KEY);

    // instance waits in endEvent
    ProcessInstance found = camunda.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstance.getId())
      .activityIdIn("endEvent")
      .singleResult();
    assertThat(found).isNotNull();

    // subProcess set variable foo
    assertThat(camunda.getRuntimeService().getVariable(found.getProcessInstanceId(), "foo")).isEqualTo("bar");
  }
}
