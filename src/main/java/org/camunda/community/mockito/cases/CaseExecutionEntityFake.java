package org.camunda.community.mockito.cases;


import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState;

import java.util.concurrent.atomic.AtomicInteger;

import static org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState.DISABLED;
import static org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState.ENABLED;

public class CaseExecutionEntityFake extends CaseExecutionEntity {

  private static final AtomicInteger count = new AtomicInteger();

  public CaseExecutionEntityFake(String activityId) {
    id = String.valueOf(count.getAndIncrement());
    this.activityId = activityId;
  }

  public CaseExecutionEntityFake setState(CaseExecutionState state) {
    currentState = state.getStateCode();
    return this;
  }

  @Override
  public void enable() {
    setState(ENABLED);
  }

  @Override
  public void disable() {
    setState(DISABLED);
  }

  @Override
  public void reenable() {
    setState(ENABLED);
  }

  @Override
  public String toString() {
    return "CaseExecutionEntityFake{" +
      "id='" + id + '\'' +
      ", state='" + getCurrentState() + '\'' +
      '}';
  }
}
