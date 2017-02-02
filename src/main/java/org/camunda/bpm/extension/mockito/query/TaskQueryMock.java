package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class TaskQueryMock extends AbstractQueryMock<TaskQueryMock, TaskQuery, Task, TaskService> {

  public TaskQueryMock() {
    super(TaskQuery.class, TaskService.class);
   }

}
