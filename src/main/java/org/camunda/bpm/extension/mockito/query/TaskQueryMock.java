package org.camunda.bpm.extension.mockito.query;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.google.common.base.Supplier;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class TaskQueryMock extends AbstractQueryMock<TaskQueryMock,TaskQuery,Task,TaskService> {



    public TaskQueryMock() {
        super(TaskQuery.class);
    }

    void pa() {
        final ProcessEngine mock = mock(ProcessEngine.class);
        mock.getHistoryService().createHistoricActivityInstanceQuery();
        mock.getHistoryService().createHistoricDetailQuery();
        mock.getHistoryService().createHistoricProcessInstanceQuery();
        mock.getHistoryService().createHistoricVariableInstanceQuery();
        mock.getRepositoryService().createDeploymentQuery();
        mock.getRepositoryService().createProcessDefinitionQuery();
        mock.getRuntimeService().createEventSubscriptionQuery();
        mock.getRuntimeService().createProcessInstanceQuery();
        mock.getRuntimeService().createIncidentQuery();
        mock.getRuntimeService().createExecutionQuery();
        mock.getAuthorizationService().createAuthorizationQuery();
        mock.getIdentityService().createGroupQuery();
        mock.getIdentityService().createUserQuery();
        mock.getManagementService().createJobQuery();
        mock.getTaskService().createTaskQuery();

    }


    @Override
    public TaskQueryMock forService(final TaskService service) {
        when(service.createTaskQuery()).thenReturn(get());
        return this;
    }
}
