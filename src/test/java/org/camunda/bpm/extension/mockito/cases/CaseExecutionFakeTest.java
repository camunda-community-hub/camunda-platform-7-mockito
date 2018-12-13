package org.camunda.bpm.extension.mockito.cases;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CaseExecutionFakeTest {

  @Test
  public void testEmptyBuilder() {
    CaseExecutionFake fake = CaseExecutionFake.builder().build();
    assertThat(fake.isActive()).isEqualTo(false);
    assertThat(fake.isAvailable()).isEqualTo(false);
    assertThat(fake.isDisabled()).isEqualTo(false);
    assertThat(fake.isEnabled()).isEqualTo(false);
    assertThat(fake.isRequired()).isEqualTo(false);
    assertThat(fake.isTerminated()).isEqualTo(false);
  }

  @Test
  public void testStateChange() {
    CaseExecutionFake fake = CaseExecutionFake.builder().build();

    fake.setActive(true);
    assertThat(fake.isActive()).isEqualTo(true);

    fake.setAvailable(true);
    assertThat(fake.isAvailable()).isEqualTo(true);

    fake.setDisabled(true);
    assertThat(fake.isDisabled()).isEqualTo(true);

    fake.setEnabled(true);
    assertThat(fake.isEnabled()).isEqualTo(true);

    fake.setRequired(true);
    assertThat(fake.isRequired()).isEqualTo(true);

    fake.setTerminated(true);
    assertThat(fake.isTerminated()).isEqualTo(true);

  }

  @Test
  public void testFullBuilder() {
    CaseExecutionFake fake = CaseExecutionFake.builder()
      .active(true)
      .available(true)
      .disabled(true)
      .enabled(true)
      .required(true)
      .terminated(true)
      .activityDescription("activityDescription")
      .activityId("activityId")
      .activityName("activityName")
      .activityType("activityType")
      .caseDefinitionId("caseDefinitionId")
      .caseInstanceId("caseInstanceId")
      .id("id")
      .parentId("parentId")
      .tenantId("tenantId")
      .build();

    assertThat(fake.getId()).isEqualTo("id");
    assertThat(fake.getParentId()).isEqualTo("parentId");
    assertThat(fake.getTenantId()).isEqualTo("tenantId");
    assertThat(fake.getActivityDescription()).isEqualTo("activityDescription");
    assertThat(fake.getActivityName()).isEqualTo("activityName");
    assertThat(fake.getActivityType()).isEqualTo("activityType");
    assertThat(fake.getActivityId()).isEqualTo("activityId");
    assertThat(fake.getCaseInstanceId()).isEqualTo("caseInstanceId");
    assertThat(fake.getCaseDefinitionId()).isEqualTo("caseDefinitionId");

    assertThat(fake.isActive()).isEqualTo(true);
    assertThat(fake.isAvailable()).isEqualTo(true);
    assertThat(fake.isDisabled()).isEqualTo(true);
    assertThat(fake.isEnabled()).isEqualTo(true);
    assertThat(fake.isRequired()).isEqualTo(true);
    assertThat(fake.isTerminated()).isEqualTo(true);


  }

}
