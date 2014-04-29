package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class TaskQueryMock extends AbstractQueryMock<TaskQueryMock, TaskQuery, Task, TaskService> {

  public TaskQueryMock() {
    super(TaskQuery.class, TaskService.class);
  }

}
