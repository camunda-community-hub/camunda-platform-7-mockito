package org.camunda.bpm.extension.mockito.generator.processor.util;

import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.history.HistoricCaseInstanceQuery;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.history.UserOperationLogQuery;
import org.camunda.bpm.engine.management.MetricsQuery;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentQuery;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.runtime.VariableInstanceQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ExtractResultTypeTest {

  private final Class<?> queryType;
  private final Class<?> expectedResult;

  public static class DummyQuery {

  }


  @Parameterized.Parameters(name = "[{index}] {0}")
  public static List<Object[]> parameters() {
    return Arrays.asList(new Object[][]{
      {TaskQuery.class, Task.class},
      {ExecutionQuery.class, Execution.class},
      {HistoricCaseInstanceQuery.class, HistoricCaseInstance.class},
      {DeploymentQuery.class, Deployment.class},
      {UserOperationLogQuery.class, UserOperationLogEntry.class},
      {VariableInstanceQuery.class, VariableInstance.class},
      {DummyQuery.class, null},

    });
  }

  private final ExtractResultType extractResultType = new ExtractResultType();

  public ExtractResultTypeTest(Class<?> queryType, Class<?> expectedResult) {
    this.queryType = queryType;
    this.expectedResult = expectedResult;
  }


  @Test
  public void evaluate() throws Exception {
    assertThat(extractResultType.apply(queryType)).isEqualTo(expectedResult);
  }
}
