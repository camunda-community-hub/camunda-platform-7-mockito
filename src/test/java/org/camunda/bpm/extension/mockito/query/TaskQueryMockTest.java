package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

//import org.camunda.bpm.extension.mockito.QueryMocks1;

@RunWith(MockitoJUnitRunner.class)
public class TaskQueryMockTest {

  @Mock
  private TaskService taskService;

  @Mock
  private Task singleResult;

  @Test
  public void should_mock_query_and_return_singleResult() {
    final TaskQuery taskQuery = CamundaMockito.mockTaskQuery(taskService).singleResult(singleResult);
    assertThat(taskService.createTaskQuery().singleResult()).isEqualTo(singleResult);

    Mockito.verify(taskQuery).singleResult();
  }

  @Test
  public void singleResult_for_activityName() {
    CamundaMockito.mockTaskQuery(taskService).singleResult(singleResult);
    assertThat(taskService.createTaskQuery().taskDefinitionKey("").singleResult()).isEqualTo(singleResult);
  }

  @Test
  public void singleResult_for_everything() {
    final TaskQuery taskQuery = new TaskQueryMock().forService(taskService).singleResult(singleResult);
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

  @Test
  public void count_on_taskQuery() throws Exception {
    final TaskQuery taskQuery = new TaskQueryMock().forService(taskService).count(5);

    assertThat(taskService.createTaskQuery().active().processDefinitionKey("foo").count()).isEqualTo(5);
  }
}
