package org.camunda.bpm.extension.mockito.cases;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CaseInstanceFakeTest {

  @Test
  public void testEmptyBuilder() {
    CaseInstanceFake fake = CaseInstanceFake.builder().build();
    assertThat(fake.isActive()).isEqualTo(false);
    assertThat(fake.isAvailable()).isEqualTo(false);
    assertThat(fake.isCompleted()).isEqualTo(false);
    assertThat(fake.isDisabled()).isEqualTo(false);
    assertThat(fake.isEnabled()).isEqualTo(false);
    assertThat(fake.isRequired()).isEqualTo(false);
    assertThat(fake.isTerminated()).isEqualTo(false);
  }

  @Test
  public void testStateChange() {
    CaseInstanceFake fake = CaseInstanceFake.builder().build();

    fake.setActive(true);
    assertThat(fake.isActive()).isEqualTo(true);

    fake.setAvailable(true);
    assertThat(fake.isAvailable()).isEqualTo(true);

    fake.setCompleted(true);
    assertThat(fake.isCompleted()).isEqualTo(true);

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
    CaseInstanceFake fake = CaseInstanceFake.builder()
      .businessKey("businessKey")
      .active(true)
      .available(true)
      .completed(true)
      .disabled(true)
      .enabled(true)
      .required(true)
      .terminated(true)
      .activityDescription("activityDescription")
      .activityId("activityId")
      .activityName("activityName")
      .activityType("activityType")
      .businessKey("businessKey")
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
    assertThat(fake.getBusinessKey()).isEqualTo("businessKey");
    assertThat(fake.getBusinessKey()).isEqualTo("businessKey");
    assertThat(fake.getCaseInstanceId()).isEqualTo("caseInstanceId");
    assertThat(fake.getCaseDefinitionId()).isEqualTo("caseDefinitionId");

    assertThat(fake.isActive()).isEqualTo(true);
    assertThat(fake.isAvailable()).isEqualTo(true);
    assertThat(fake.isCompleted()).isEqualTo(true);
    assertThat(fake.isDisabled()).isEqualTo(true);
    assertThat(fake.isEnabled()).isEqualTo(true);
    assertThat(fake.isRequired()).isEqualTo(true);
    assertThat(fake.isTerminated()).isEqualTo(true);


  }


  @Test
  public void prepare_with_service_mock() {
    CaseService caseService = mock(CaseService.class);
    String uuid = UUID.randomUUID().toString();
    String businessKey = "12";

    AtomicReference<CaseInstanceFake> reference = CaseInstanceFake.prepareMock(caseService, uuid);

    assertThat(reference.get()).isNull();

    CaseInstance instance = caseService.createCaseInstanceByKey("case", businessKey, Variables.putValue("foo", "bar"));
    assertThat(reference.get()).isNotNull();
    assertThat(instance.getCaseInstanceId()).isEqualTo(uuid);

    assertThat(caseService.getVariable(uuid, "foo")).isEqualTo("bar");

    caseService.setVariable(uuid, "hello", 456);
    assertThat(caseService.getVariable(uuid, "hello")).isEqualTo(456);

    assertThat(caseService.createCaseInstanceQuery().singleResult()).isEqualTo(reference.get());
  }
}
