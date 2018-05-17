package org.camunda.bpm.extension.mockito.delegate;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class DelegateCaseExecutionFakeTest {

  private final DelegateCaseExecutionFake fake = CamundaMockito.delegateCaseExecutionFake();

  @Test
  public void withId() {
    assertThat(fake.getId()).isNull();
    fake.withId("1");
    assertThat(fake.getId()).isEqualTo("1");
  }

  @Test
  public void withCaseInstanceId() {
    assertThat(fake.getCaseInstanceId()).isNull();
    fake.withCaseInstanceId("1");
    assertThat(fake.getCaseInstanceId()).isEqualTo("1");
  }

  @Test
  public void withEventName() {
    assertThat(fake.getEventName()).isNull();
    fake.withEventName("create");
    assertThat(fake.getEventName()).isEqualTo("create");
  }

  @Test
  public void withBusinessKey() {
    assertThat(fake.getBusinessKey()).isNull();
    fake.withBusinessKey("1");
    assertThat(fake.getBusinessKey()).isEqualTo("1");
  }

  @Test
  public void withCaseBusinessKey() {
    assertThat(fake.getCaseBusinessKey()).isNull();
    fake.withCaseBusinessKey("1");
    assertThat(fake.getCaseBusinessKey()).isEqualTo("1");
  }

  @Test
  public void withCaseDefinitionId() {
    assertThat(fake.getCaseDefinitionId()).isNull();
    fake.withCaseDefinitionId("1");
    assertThat(fake.getCaseDefinitionId()).isEqualTo("1");
  }

  @Test
  public void withParentId() {
    assertThat(fake.getParentId()).isNull();
    fake.withParentId("1");
    assertThat(fake.getParentId()).isEqualTo("1");
  }

  @Test
  public void withActivityId() {
    assertThat(fake.getActivityId()).isNull();
    fake.withActivityId("task");
    assertThat(fake.getActivityId()).isEqualTo("task");
  }


  @Test
  public void withActivityName() {
    assertThat(fake.getActivityName()).isNull();
    fake.withActivityName("The Task");
    assertThat(fake.getActivityName()).isEqualTo("The Task");
  }

  @Test
  public void withTenantId() {
    assertThat(fake.getTenantId()).isNull();
    fake.withTenantId("1");
    assertThat(fake.getTenantId()).isEqualTo("1");
  }

  @Test
  public void withProcessEngineServices() {
    ProcessEngineServices services = mock(ProcessEngineServices.class);
    assertThat(fake.getProcessEngineServices()).isNull();
    fake.withProcessEngineServices(services);
    assertThat(fake.getProcessEngineServices()).isEqualTo(services);
  }

  @Test
  public void withOutState() {
    assertThat(fake.isActive()).isFalse();
    assertThat(fake.isAvailable()).isFalse();
    assertThat(fake.isClosed()).isFalse();
    assertThat(fake.isCompleted()).isFalse();
    assertThat(fake.isDisabled()).isFalse();
    assertThat(fake.isEnabled()).isFalse();
    assertThat(fake.isFailed()).isFalse();
    assertThat(fake.isSuspended()).isFalse();
    assertThat(fake.isTerminated()).isFalse();
  }

  @Test
  public void withState_active() {
    fake.withCaseExecutionState(CaseExecutionState.ACTIVE);
    assertThat(fake.isActive()).isTrue();
  }
  @Test
  public void withState_available() {
    fake.withCaseExecutionState(CaseExecutionState.AVAILABLE);
    assertThat(fake.isAvailable()).isTrue();
  }
  @Test
  public void withState_closed() {
    fake.withCaseExecutionState(CaseExecutionState.CLOSED);
    assertThat(fake.isClosed()).isTrue();
  }
  @Test
  public void withState_completed() {
    fake.withCaseExecutionState(CaseExecutionState.COMPLETED);
    assertThat(fake.isCompleted()).isTrue();
  }
  @Test
  public void withState_disabled() {
    fake.withCaseExecutionState(CaseExecutionState.DISABLED);
    assertThat(fake.isDisabled()).isTrue();
  }
  @Test
  public void withState_enabled() {
    fake.withCaseExecutionState(CaseExecutionState.ENABLED);
    assertThat(fake.isEnabled()).isTrue();
  }
  @Test
  public void withState_failed() {
    fake.withCaseExecutionState(CaseExecutionState.FAILED);
    assertThat(fake.isFailed()).isTrue();
  }
  @Test
  public void withState_suspended() {
    fake.withCaseExecutionState(CaseExecutionState.SUSPENDED);
    assertThat(fake.isSuspended()).isTrue();
  }
  @Test
  public void withState_terminated() {
    fake.withCaseExecutionState(CaseExecutionState.TERMINATED);
    assertThat(fake.isTerminated()).isTrue();
  }

  @Test
  public void modelInstance_not_supported() {
    assertThatThrownBy(() -> fake.getCmmnModelInstance()).isInstanceOf(UnsupportedOperationException.class);
    assertThatThrownBy(() -> fake.getCmmnModelElementInstance()).isInstanceOf(UnsupportedOperationException.class);
  }
}
