package org.camunda.bpm.extension.mockito.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.extension.mockito.QueryMocks;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class TaskQueryMockTest {

  private final TaskService taskService = mock(TaskService.class);

  private final Task singleResult = mock(Task.class);

  @Test
  public void should_mock_query_and_return_singleResult() {
    final TaskQuery taskQuery = QueryMocks.mockTaskQuery(taskService).singleResult(singleResult);
    assertThat(taskService.createTaskQuery().singleResult()).isEqualTo(singleResult);

    Mockito.verify(taskQuery).singleResult();
  }

  @Test
  public void singleResult_for_activityName() {
    QueryMocks.mockTaskQuery(taskService).singleResult(singleResult);
    assertThat(taskService.createTaskQuery().taskDefinitionKey("").singleResult()).isEqualTo(singleResult);
  }

  @Test
  public void singleResult_for_everything() {
    final TaskQuery taskQuery = QueryMocks.mockTaskQuery(taskService).singleResult(singleResult);
    // @formatter:off
    assertThat(
      taskService.createTaskQuery()
        .taskDefinitionKey("")
        .processInstanceBusinessKey("")
        .taskDefinitionKey("")
        .taskId("")
        .taskUnassigned()
        .processInstanceId("pid")
        .active()
        .activityInstanceIdIn("")
        .dueAfter(new Date())
        .dueBefore(new Date())
        .dueDate(new Date())
        .excludeSubtasks()
        .executionId("")
        .processDefinitionId("")
        .processDefinitionKey("")
        .asc()
        .desc()
        .singleResult()).isEqualTo(singleResult);
    // @formatter:on

    verify(taskQuery).processInstanceId("pid");
  }
}
