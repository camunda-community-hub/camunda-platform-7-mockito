package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.extension.test.mockito.QueryMocks;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by jangalinski on 06.03.14.
 *
 * @author Jan Galinski, Holisticon AG
 */
public class TaskQueryMockTest {

  private final TaskService taskService = Mockito.mock(TaskService.class);


  private final Task singleResult = Mockito.mock(Task.class);

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
    assertThat(taskService.createTaskQuery()
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
            .singleResult())
            .isEqualTo(singleResult);
    // @formatter:on

    verify(taskQuery).processInstanceId("pid");
  }
}
