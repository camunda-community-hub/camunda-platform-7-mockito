package org.camunda.community.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ProcessEngineServicesAwareFakeTest {


  private final ProcessEngineServicesAwareFake fake = new ProcessEngineServicesAwareFake();


  @Test
  public void initially_not_set() {
    assertThat(fake.getProcessEngine()).isNull();
    assertThat(fake.getProcessEngineServices()).isNull();
  }

  @Test
  public void with_processEngineServices() {
    ProcessEngineServices processEngineServices = mock(ProcessEngineServices.class);

    fake.withProcessEngineServices(processEngineServices);

    assertThat(fake.getProcessEngine()).isNull();
    assertThat(fake.getProcessEngineServices()).isNotNull();
  }

  @Test
  public void with_processEngine_sets_both() {
    ProcessEngine processEngine = mock(ProcessEngine.class);

    fake.withProcessEngine(processEngine);

    assertThat(fake.getProcessEngine()).isNotNull().isEqualTo(processEngine);
    assertThat(fake.getProcessEngineServices()).isNotNull().isEqualTo(processEngine);
  }
}
