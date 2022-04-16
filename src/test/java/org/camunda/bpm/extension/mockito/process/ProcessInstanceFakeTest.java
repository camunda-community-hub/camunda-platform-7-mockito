package org.camunda.community.mockito.process;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProcessInstanceFakeTest {

  @Test
  public void testEmptyBuilder() {
    ProcessInstanceFake fake = ProcessInstanceFake.builder().build();
    assertThat(fake.isEnded()).isEqualTo(false);
    assertThat(fake.isSuspended()).isEqualTo(false);
  }

  @Test
  public void testStateChange() {
    ProcessInstanceFake fake = ProcessInstanceFake.builder().build();
    assertThat(fake.isEnded()).isEqualTo(false);
    assertThat(fake.isSuspended()).isEqualTo(false);

    fake.setEnded(true);
    assertThat(fake.isEnded()).isEqualTo(true);

    fake.setSuspended(true);
    assertThat(fake.isSuspended()).isEqualTo(true);
  }

  @Test
  public void testFullBuilder() {
    ProcessInstanceFake fake = ProcessInstanceFake.builder()
      .businessKey("businessKey")
      .caseInstanceId("caseInstanceId")
      .ended(true)
      .id("id")
      .processDefinitionId("processDefinitionId")
      .processInstanceId("processInstanceId")
      .suspended(true)
      .tenantId("tenantId")
      .build();

    assertThat(fake.getBusinessKey()).isEqualTo("businessKey");
    assertThat(fake.getCaseInstanceId()).isEqualTo("caseInstanceId");
    assertThat(fake.getId()).isEqualTo("id");
    assertThat(fake.getProcessDefinitionId()).isEqualTo("processDefinitionId");
    assertThat(fake.getProcessInstanceId()).isEqualTo("processInstanceId");
    assertThat(fake.isSuspended()).isEqualTo(true);
    assertThat(fake.isEnded()).isEqualTo(true);


  }

}
