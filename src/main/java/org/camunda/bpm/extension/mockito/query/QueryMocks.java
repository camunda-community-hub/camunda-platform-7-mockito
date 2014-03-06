package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.TaskService;

/**
 * Created by jangalinski on 06.03.14.
 *
 * @author Jan Galinski, Holisticon AG
 */
public final class QueryMocks {

    public static TaskQueryMock mockTaskQuery(TaskService taskServiceMock) {
        return new TaskQueryMock().forService(taskServiceMock);
    }

    private QueryMocks() {
        // empty
    }
}
