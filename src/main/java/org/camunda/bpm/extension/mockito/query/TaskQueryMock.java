package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class TaskQueryMock extends AbstractQueryMock<TaskQueryMock, TaskQuery, Task, TaskService> {

  public TaskQueryMock() {
    super(TaskQuery.class, TaskService.class);
  }

}
