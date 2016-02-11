package org.camunda.bpm.extension.mockito;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.extension.mockito.query.ActivityStatisticsQueryMock;
import org.camunda.bpm.extension.mockito.query.AuthorizationQueryMock;
import org.camunda.bpm.extension.mockito.query.CaseDefinitionQueryMock;
import org.camunda.bpm.extension.mockito.query.CaseExecutionQueryMock;
import org.camunda.bpm.extension.mockito.query.CaseInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.DeploymentQueryMock;
import org.camunda.bpm.extension.mockito.query.DeploymentStatisticsQueryMock;
import org.camunda.bpm.extension.mockito.query.EventSubscriptionQueryMock;
import org.camunda.bpm.extension.mockito.query.ExecutionQueryMock;
import org.camunda.bpm.extension.mockito.query.FilterQueryMock;
import org.camunda.bpm.extension.mockito.query.GroupQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricActivityInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricActivityStatisticsQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricCaseActivityInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricCaseInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricDetailQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricIncidentQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricJobLogQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricProcessInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricTaskInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.HistoricVariableInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.IncidentQueryMock;
import org.camunda.bpm.extension.mockito.query.JobDefinitionQueryMock;
import org.camunda.bpm.extension.mockito.query.JobQueryMock;
import org.camunda.bpm.extension.mockito.query.ProcessDefinitionQueryMock;
import org.camunda.bpm.extension.mockito.query.ProcessDefinitionStatisticsQueryMock;
import org.camunda.bpm.extension.mockito.query.ProcessInstanceQueryMock;
import org.camunda.bpm.extension.mockito.query.TaskQueryMock;
import org.camunda.bpm.extension.mockito.query.UserOperationLogQueryMock;
import org.camunda.bpm.extension.mockito.query.UserQueryMock;
import org.camunda.bpm.extension.mockito.query.VariableInstanceQueryMock;

/**
 * Central provider for all mocked Queries.
 *
 * @author Jan Galinski, Holisticon AG
 */
@SuppressWarnings("unused")
public final class QueryMocks {

  /**
   * This is a util class, do not instantiate!
   */
  private QueryMocks() {
    // empty
  }
  
  public static ActivityStatisticsQueryMock mockActivityStatisticsQuery(final ManagementService serviceMock) {
    return new ActivityStatisticsQueryMock().forService(serviceMock);
  }

  public static AuthorizationQueryMock mockAuthorizationQuery(final AuthorizationService serviceMock) {
    return new AuthorizationQueryMock().forService(serviceMock);
  }
  
  public static CaseDefinitionQueryMock mockCaseDefinitionQuery(final CaseService serviceMock) {
    return new CaseDefinitionQueryMock().forService(serviceMock);
  }
  
  public static CaseExecutionQueryMock mockCaseExecutionQuery(final CaseService serviceMock) {
    return new CaseExecutionQueryMock().forService(serviceMock);
  }
  
  public static CaseInstanceQueryMock mockCaseInstanceQuery(final CaseService serviceMock) {
    return new CaseInstanceQueryMock().forService(serviceMock);
  }

  public static TaskQueryMock mockTaskQuery(final TaskService serviceMock) {
    return new TaskQueryMock().forService(serviceMock);
  }

  public static ExecutionQueryMock mockExecutionQuery(final RuntimeService serviceMock) {
    return new ExecutionQueryMock().forService(serviceMock);
  }
  
  public static FilterQueryMock mockFilterQuery(final FilterService serviceMock) {
    return new FilterQueryMock().forService(serviceMock);
  }

  public static ProcessInstanceQueryMock mockProcessInstanceQuery(final RuntimeService serviceMock) {
    return new ProcessInstanceQueryMock().forService(serviceMock);
  }

  public static EventSubscriptionQueryMock mockEventSubscriptionQuery(final RuntimeService serviceMock) {
    return new EventSubscriptionQueryMock().forService(serviceMock);
  }

  public static IncidentQueryMock mockIncidentQuery(final RuntimeService serviceMock) {
    return new IncidentQueryMock().forService(serviceMock);
  }

  public static ProcessDefinitionQueryMock mockProcessDefinitionQuery(final RepositoryService serviceMock) {
    return new ProcessDefinitionQueryMock().forService(serviceMock);
  }

  public static DeploymentQueryMock mockDeploymentQuery(final RepositoryService serviceMock) {
    return new DeploymentQueryMock().forService(serviceMock);
  }

  public static GroupQueryMock mockGroupQuery(final IdentityService serviceMock) {
    return new GroupQueryMock().forService(serviceMock);
  }

  public static UserQueryMock mockUserQuery(final IdentityService serviceMock) {
    return new UserQueryMock().forService(serviceMock);
  }

  public static JobQueryMock mockJobQuery(final ManagementService serviceMock) {
    return new JobQueryMock().forService(serviceMock);
  }

  public static JobDefinitionQueryMock mockJobDefinitionQuery(final ManagementService serviceMock) {
    return new JobDefinitionQueryMock().forService(serviceMock);
  }

  public static ProcessDefinitionStatisticsQueryMock mockProcessDefinitionStatisticsQuery(final ManagementService serviceMock) {
    return new ProcessDefinitionStatisticsQueryMock().forService(serviceMock);
  }

  public static DeploymentStatisticsQueryMock mockDeploymentStatisticsQuery(final ManagementService serviceMock) {
    return new DeploymentStatisticsQueryMock().forService(serviceMock);
  }

  public static HistoricProcessInstanceQueryMock mockHistoricProcessInstanceQuery(final HistoryService serviceMock) {
    return new HistoricProcessInstanceQueryMock().forService(serviceMock);
  }

  public static HistoricTaskInstanceQueryMock mockHistoricTaskInstanceQuery(final HistoryService serviceMock) {
    return new HistoricTaskInstanceQueryMock().forService(serviceMock);
  }

  public static HistoricActivityInstanceQueryMock mockHistoricActivityInstanceQuery(final HistoryService serviceMock) {
    return new HistoricActivityInstanceQueryMock().forService(serviceMock);
  }
  
  public static HistoricActivityStatisticsQueryMock mockHistoricActivityStatisticsQuery(final HistoryService serviceMock) {
    return new HistoricActivityStatisticsQueryMock().forService(serviceMock);
  }
  
  public static HistoricCaseActivityInstanceQueryMock mockHistoricCaseActivityInstanceQuery(final HistoryService serviceMock) {
    return new HistoricCaseActivityInstanceQueryMock().forService(serviceMock);
  }
  
  public static HistoricCaseInstanceQueryMock mockHistoricCaseInstanceQuery(final HistoryService serviceMock) {
    return new HistoricCaseInstanceQueryMock().forService(serviceMock);
  }

  public static HistoricVariableInstanceQueryMock mockHistoricVariableInstanceQuery(final HistoryService serviceMock) {
    return new HistoricVariableInstanceQueryMock().forService(serviceMock);
  }
  
  public static HistoricJobLogQueryMock mockHistoricJobLogQuery(final HistoryService serviceMock) {
    return new HistoricJobLogQueryMock().forService(serviceMock);
  }

  public static HistoricDetailQueryMock mockHistoricDetailQuery(final HistoryService serviceMock) {
    return new HistoricDetailQueryMock().forService(serviceMock);
  }

  public static HistoricIncidentQueryMock mockHistoricIncidentQuery(final HistoryService serviceMock) {
    return new HistoricIncidentQueryMock().forService(serviceMock);
  }

  public static UserOperationLogQueryMock mockUserOperationLogQuery(final HistoryService serviceMock) {
    return new UserOperationLogQueryMock().forService(serviceMock);
  }
  
  public static VariableInstanceQueryMock mockVariableInstanceQuery(final RuntimeService serviceMock) {
    return new VariableInstanceQueryMock().forService(serviceMock);
  }

}
