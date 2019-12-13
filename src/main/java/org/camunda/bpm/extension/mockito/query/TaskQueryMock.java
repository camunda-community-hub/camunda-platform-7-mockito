package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

public class TaskQueryMock extends AbstractQueryMock<TaskQueryMock, TaskQuery, Task, TaskService> {

  public TaskQueryMock() {
    super(TaskQuery.class, TaskService.class);
   }

}
