package org.camunda.community.mockito;


import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.community.mockito.delegate.DelegateCaseExecutionFake;
import org.camunda.community.mockito.delegate.DelegateCaseVariableInstanceFake;
import org.camunda.community.mockito.delegate.DelegateExecutionFake;
import org.camunda.community.mockito.delegate.DelegateTaskFake;
import org.camunda.community.mockito.delegate.VariableScopeFake;
import org.camunda.community.mockito.function.NameForType;
import org.camunda.community.mockito.mock.FluentExecutionListenerMock;
import org.camunda.community.mockito.mock.FluentJavaDelegateMock;
import org.camunda.community.mockito.mock.FluentTaskListenerMock;
import org.camunda.community.mockito.process.CallActivityMock;
import org.camunda.community.mockito.query.ActivityStatisticsQueryMock;
import org.camunda.community.mockito.query.AuthorizationQueryMock;
import org.camunda.community.mockito.query.BatchQueryMock;
import org.camunda.community.mockito.query.BatchStatisticsQueryMock;
import org.camunda.community.mockito.query.CaseDefinitionQueryMock;
import org.camunda.community.mockito.query.CaseExecutionQueryMock;
import org.camunda.community.mockito.query.CaseInstanceQueryMock;
import org.camunda.community.mockito.query.DecisionDefinitionQueryMock;
import org.camunda.community.mockito.query.DeploymentQueryMock;
import org.camunda.community.mockito.query.DeploymentStatisticsQueryMock;
import org.camunda.community.mockito.query.EventSubscriptionQueryMock;
import org.camunda.community.mockito.query.ExecutionQueryMock;
import org.camunda.community.mockito.query.ExternalTaskQueryMock;
import org.camunda.community.mockito.query.FilterQueryMock;
import org.camunda.community.mockito.query.GroupQueryMock;
import org.camunda.community.mockito.query.HistoricActivityStatisticsQueryMock;
import org.camunda.community.mockito.query.HistoricBatchQueryMock;
import org.camunda.community.mockito.query.HistoricCaseActivityInstanceQueryMock;
import org.camunda.community.mockito.query.HistoricCaseInstanceQueryMock;
import org.camunda.community.mockito.query.HistoricDecisionInstanceQueryMock;
import org.camunda.community.mockito.query.HistoricDetailQueryMock;
import org.camunda.community.mockito.query.HistoricIdentityLinkLogQueryMock;
import org.camunda.community.mockito.query.HistoricIncidentQueryMock;
import org.camunda.community.mockito.query.HistoricJobLogQueryMock;
import org.camunda.community.mockito.query.HistoricProcessInstanceQueryMock;
import org.camunda.community.mockito.query.HistoricTaskInstanceQueryMock;
import org.camunda.community.mockito.query.HistoricVariableInstanceQueryMock;
import org.camunda.community.mockito.query.IncidentQueryMock;
import org.camunda.community.mockito.query.JobDefinitionQueryMock;
import org.camunda.community.mockito.query.JobQueryMock;
import org.camunda.community.mockito.query.ProcessDefinitionQueryMock;
import org.camunda.community.mockito.query.ProcessDefinitionStatisticsQueryMock;
import org.camunda.community.mockito.query.ProcessInstanceQueryMock;
import org.camunda.community.mockito.query.TaskQueryMock;
import org.camunda.community.mockito.query.TenantQueryMock;
import org.camunda.community.mockito.query.UserOperationLogQueryMock;
import org.camunda.community.mockito.query.UserQueryMock;
import org.camunda.community.mockito.query.VariableInstanceQueryMock;
import org.camunda.community.mockito.service.RuntimeServiceFluentMock;
import org.camunda.community.mockito.verify.MockitoVerification;

import javax.annotation.Nonnull;
import java.net.URL;
import java.util.Collection;
import java.util.Set;

import static org.camunda.community.mockito.function.NameForType.juelNameFor;

@SuppressWarnings("unused")
public enum CamundaMockito {
  ;

  /**
   * Takes a BPMN resource and registers mocks for all delegateExpressions.
   *
   * @param bpmnFileResource the bpm file resource to parse
   * @see #autoMock(java.net.URL)
   */
  public static void autoMock(final @Nonnull String bpmnFileResource) {
    DelegateExpressions.autoMock(bpmnFileResource);
  }

  /**
   * Takes a BPMN file and registers TaskListener-, ExecutionListener and
   * JavaDelegate-Mocks for every delegateExpression encountered.
   * <p>
   * This is an auto-mock feature that allows the process to run. If you need to
   * modify the behavior of the mock, you can use the getXXX() methods to access
   * it by its name.
   *
   * @param bpmnFile the BPMN resource to parse
   */
  @SuppressWarnings("ConstantConditions")
  public static void autoMock(final @Nonnull URL bpmnFile) {
    DelegateExpressions.autoMock(bpmnFile);
  }

  /**
   * Registers a new FluentJavaDelegateMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#registerInstance(String,
   * Object)
   */
  public static FluentJavaDelegateMock registerJavaDelegateMock(final String name) {
    return DelegateExpressions.registerJavaDelegateMock(name);
  }

  /**
   * Registers a new FluentJavaDelegateMock instance for name (by type).
   *
   * @param type the type to register
   * @return new fluent-mock instance
   */
  public static FluentJavaDelegateMock registerJavaDelegateMock(final Class<? extends JavaDelegate> type) {
    return DelegateExpressions.registerJavaDelegateMock(type);
  }

  /**
   * Registers a new FluentExecutionListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#registerInstance(String,
   * Object)
   */
  public static FluentExecutionListenerMock registerExecutionListenerMock(final String name) {
    return Expressions.registerInstance(name, new FluentExecutionListenerMock());
  }

  /**
   * Registers a new FluentExecutionListenerMock instance for name (by type).
   *
   * @param type the type to register
   * @return new fluent-mock instance
   */
  public static FluentExecutionListenerMock registerExecutionListenerMock(final Class<? extends ExecutionListener> type) {
    return DelegateExpressions.registerExecutionListenerMock(juelNameFor(type));
  }

  /**
   * Registers a new FluentTaskListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#registerInstance(String,
   * Object)
   */
  public static FluentTaskListenerMock registerTaskListenerMock(final String name) {
    return Expressions.registerInstance(name, new FluentTaskListenerMock());
  }

  /**
   * Registers a new FluentTaskListenerMock instance for name (by type).
   *
   * @param type the type to register
   * @return new fluent-mock instance
   */
  public static FluentTaskListenerMock registerTaskListenerMock(final Class<? extends TaskListener> type) {
    return DelegateExpressions.registerTaskListenerMock(juelNameFor(type));
  }

  /**
   * Returns the registered FluentJavaDelegateMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#getRegistered(String)
   */
  public static FluentJavaDelegateMock getJavaDelegateMock(final String name) {
    return Expressions.getRegistered(name);
  }


  public static FluentJavaDelegateMock getJavaDelegateMock(final Class<?> type) {
    return Expressions.getRegistered(type);
  }

  /**
   * Returns the registered FluentExecutionListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#getRegistered(String)
   */
  public static FluentExecutionListenerMock getExecutionListenerMock(final String name) {
    return Expressions.getRegistered(name);
  }

  public static FluentExecutionListenerMock getExecutionListenerMock(final Class<?> type) {
    return Expressions.getRegistered(type);
  }

  /**
   * Returns the registered FluentTaskListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#getRegistered(String)
   */
  public static FluentTaskListenerMock getTaskListenerMock(final String name) {
    return Expressions.getRegistered(name);
  }

  public static FluentTaskListenerMock getTaskListenerMock(final Class<?> type) {
    return Expressions.getRegistered(type);
  }

  /**
   * Gets the registered FluentJavaDelegateMock and creates a verification
   * instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for JavaDelegate
   * @see #verifyJavaDelegateMock(org.camunda.community.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final String name) {
    return DelegateExpressions.verifyJavaDelegateMock(name);
  }

  /**
   * Gets the registered FluentJavaDelegateMock and creates a verification
   * instance.
   *
   * @param type the type of the delegate to lookup
   * @return verifcation for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final Class<?> type) {
    return DelegateExpressions.verifyJavaDelegateMock(type);
  }

  /**
   * Creates a verification instance for JavaDelegate.
   *
   * @param fluentJavaDelegateMock the fluent-mock instance
   * @return verification for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final FluentJavaDelegateMock fluentJavaDelegateMock) {
    return DelegateExpressions.verifyJavaDelegateMock(fluentJavaDelegateMock);
  }

  /**
   * Gets the registered FluentExecutionListenerMock and creates a verification
   * instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for ExecutionListener
   * @see #verifyJavaDelegateMock(org.camunda.community.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final String name) {
    return DelegateExpressions.verifyExecutionListenerMock(name);
  }

  /**
   * Gets the registered FluentExecutionListenerMock and creates a verification
   * instance.
   *
   * @param type the type of the listener to lookup
   * @return verification for ExecutionListener
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final Class<?> type) {
    return DelegateExpressions.verifyExecutionListenerMock(type);
  }

  /**
   * Creates a verification instance for ExecutionListener.
   *
   * @param fluentExecutionListenerMock the fluent-mock instance
   * @return verification for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final FluentExecutionListenerMock fluentExecutionListenerMock) {
    return DelegateExpressions.verifyExecutionListenerMock(fluentExecutionListenerMock);
  }

  /**
   * Gets the registered FluentTaskListenerMock and creates a verification
   * instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for TaskListener
   * @see #verifyJavaDelegateMock(org.camunda.community.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final String name) {
    return DelegateExpressions.verifyTaskListenerMock(name);
  }

  /**
   * Gets the registered FluentExecutionListenerMock and creates a verification
   * instance.
   *
   * @param type the type of the listener to lookup
   * @return verification for TaskListener
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final Class<?> type) {
    return DelegateExpressions.verifyTaskListenerMock(type);
  }

  /**
   * Creates a verification instance for TaskListener.
   *
   * @param fluentTaskListenerMock the fluent-mock instance
   * @return verification for TaskListener
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final FluentTaskListenerMock fluentTaskListenerMock) {
    return DelegateExpressions.verifyTaskListenerMock(fluentTaskListenerMock);
  }


  /**
   * Registers mock instances for every public static nested class found in
   * parentClass.
   *
   * @param parentClass the parentClass to scan for nested public static types
   */
  public static void registerMockInstancesForNestedTypes(final Class<?> parentClass) {
    Expressions.registerMockInstancesForNestedTypes(parentClass);
  }

  /**
   * Registers mocks via
   * {@link org.camunda.bpm.engine.test.mock.Mocks#register(String, Object)} for
   * all attributes with Named-types.
   *
   * @param instance instance who's fields are registered (maybe Junit test or jbehave
   *                 steps).
   */
  public static void registerInstancesForFields(final Object instance) {
    Expressions.registerInstancesForFields(instance);
  }

  /**
   * Registers new instances for every public static nested class found in
   * parentClass.
   *
   * @param parentClass the parentClass to scan for nested public static types
   */
  public static void registerNewInstancesForNestedTypes(final Class<?> parentClass) {
    Expressions.registerMockInstancesForNestedTypes(parentClass);
  }

  /**
   * Creates and registers mock instance for every given type.
   *
   * @param types collection of types to mock and register
   * @see #registerMockInstances(java.util.Collection)
   */
  public static void registerMockInstances(final Class<?>... types) {
    Expressions.registerMockInstances(types);
  }

  /**
   * Creates and registers mock instance for every given type.
   *
   * @param types collection of types to mock and register
   */
  public static void registerMockInstances(final Collection<Class<?>> types) {
    Expressions.registerMockInstances(types);
  }

  /**
   * Creates a mock for the given type and registers it.
   *
   * @param name the juel name under which the mock is registered
   * @param type the type of the mock to create
   * @return the registered mock instance
   */
  public static <T> T registerMockInstance(final String name, final Class<T> type) {
    return Expressions.registerMockInstance(name, type);
  }

  /**
   * Creates a mock for the given type and registers it.
   *
   * @param type the type of the mock to create
   * @return the registered mock instance
   */
  public static <T> T registerMockInstance(final Class<T> type) {
    return Expressions.registerMockInstance(type);
  }

  /**
   * Creates a new instance for the given type and registers it under the given
   * name.
   *
   * @param name the name for the registered instance
   * @param type the type of the instance to create
   * @return the registered instance
   */
  public static <T> T registerNewInstance(final String name, final Class<T> type) {
    return Expressions.registerNewInstance(name, type);
  }

  /**
   * Creates a new instance for the given type using the default constructor and
   * registers it.
   *
   * @param type the type of the instance to create
   * @return the registered instance
   * @see #registerNewInstance(String, Class)
   */
  public static <T> T registerNewInstance(final Class<T> type) {
    return Expressions.registerNewInstance(type);
  }

  /**
   * If you already have the instance, register it directly. Name is guessed via
   * {@link NameForType}.
   *
   * @param instance the instance or mock to register
   * @return the registered instance
   */
  public static <T> T registerInstance(final T instance) {
    return Expressions.registerInstance(instance);
  }

  /**
   * Delegates to
   * {@link org.camunda.bpm.engine.test.mock.Mocks#register(String, Object)}
   *
   * @param name     the juel name for the registered instance
   * @param instance the instance to register
   * @return the registered instance
   */
  public static <T> T registerInstance(final String name, final T instance) {
    return Expressions.registerInstance(name, instance);
  }

  /**
   * @param name juel name of the registered instance or mock
   * @return registered instance or mock of type
   */
  @SuppressWarnings("unchecked")
  public static <T> T getRegistered(final String name) {
    return Expressions.getRegistered(name);
  }

  /**
   * @param type the type of the registered instance or mock
   * @return registered instance or mock for type
   */
  public static <T> T getRegistered(final Class<?> type) {
    return Expressions.getRegistered(type);
  }

  /**
   * @see org.camunda.bpm.engine.test.mock.Mocks#reset()
   */
  public static void reset() {
    Mocks.reset();
  }


  public static FilterQueryMock mockFilterQuery(final FilterService serviceMock) {
    return QueryMocks.mockFilterQuery(serviceMock);
  }

  public static TaskQueryMock mockTaskQuery(final TaskService serviceMock) {
    return QueryMocks.mockTaskQuery(serviceMock);
  }

  public static CaseInstanceQueryMock mockCaseInstanceQuery(final CaseService serviceMock) {
    return QueryMocks.mockCaseInstanceQuery(serviceMock);
  }

  public static CaseExecutionQueryMock mockCaseExecutionQuery(final CaseService serviceMock) {
    return QueryMocks.mockCaseExecutionQuery(serviceMock);
  }

  public static ExecutionQueryMock mockExecutionQuery(final RuntimeService serviceMock) {
    return QueryMocks.mockExecutionQuery(serviceMock);
  }

  public static ProcessInstanceQueryMock mockProcessInstanceQuery(final RuntimeService serviceMock) {
    return QueryMocks.mockProcessInstanceQuery(serviceMock);
  }

  public static IncidentQueryMock mockIncidentQuery(final RuntimeService serviceMock) {
    return QueryMocks.mockIncidentQuery(serviceMock);
  }

  public static EventSubscriptionQueryMock mockEventSubscriptionQuery(final RuntimeService serviceMock) {
    return QueryMocks.mockEventSubscriptionQuery(serviceMock);
  }

  public static VariableInstanceQueryMock mockVariableInstanceQuery(final RuntimeService serviceMock) {
    return QueryMocks.mockVariableInstanceQuery(serviceMock);
  }

  public static ProcessDefinitionQueryMock mockProcessDefinitionQuery(final RepositoryService serviceMock) {
    return QueryMocks.mockProcessDefinitionQuery(serviceMock);
  }

  public static CaseDefinitionQueryMock mockCaseDefinitionQuery(final RepositoryService serviceMock) {
    return QueryMocks.mockCaseDefinitionQuery(serviceMock);
  }

  public static DecisionDefinitionQueryMock mockDecisionDefinitionQuery(final RepositoryService serviceMock) {
    return QueryMocks.mockDecisionDefinitionQuery(serviceMock);
  }

  public static DeploymentQueryMock mockDeploymentQuery(final RepositoryService serviceMock) {
    return QueryMocks.mockDeploymentQuery(serviceMock);
  }

  public static HistoricIdentityLinkLogQueryMock mockHistoricIdentityLinkLogQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricIdentityLinkLogQuery(serviceMock);
  }

  public static HistoricProcessInstanceQueryMock mockHistoricProcessInstanceQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricProcessInstanceQuery(serviceMock);
  }

  public static HistoricActivityStatisticsQueryMock mockHistoricActivityStatisticsQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricActivityStatisticsQuery(serviceMock);
  }

  public static HistoricVariableInstanceQueryMock mockHistoricVariableInstanceQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricVariableInstanceQuery(serviceMock);
  }

  public static HistoricCaseActivityInstanceQueryMock mockHistoricCaseActivityInstanceQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricCaseActivityInstanceQuery(serviceMock);
  }

  public static HistoricDecisionInstanceQueryMock mockHistoricDecisionInstanceQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricDecisionInstanceQuery(serviceMock);
  }

  public static HistoricTaskInstanceQueryMock mockHistoricTaskInstanceQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricTaskInstanceQuery(serviceMock);
  }

  public static HistoricDetailQueryMock mockHistoricDetailQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricDetailQuery(serviceMock);
  }

  public static UserOperationLogQueryMock mockUserOperationLogQuery(final HistoryService serviceMock) {
    return QueryMocks.mockUserOperationLogQuery(serviceMock);
  }

  public static HistoricIncidentQueryMock mockHistoricIncidentQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricIncidentQuery(serviceMock);
  }

  public static HistoricCaseInstanceQueryMock mockHistoricCaseInstanceQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricCaseInstanceQuery(serviceMock);
  }

  public static HistoricJobLogQueryMock mockHistoricJobLogQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricJobLogQuery(serviceMock);
  }

  public static HistoricBatchQueryMock mockHistoricBatchQuery(final HistoryService serviceMock) {
    return QueryMocks.mockHistoricBatchQuery(serviceMock);
  }

  public static UserQueryMock mockUserQuery(final IdentityService serviceMock) {
    return QueryMocks.mockUserQuery(serviceMock);
  }

  public static GroupQueryMock mockGroupQuery(final IdentityService serviceMock) {
    return QueryMocks.mockGroupQuery(serviceMock);
  }

  public static TenantQueryMock mockTenantQuery(final IdentityService serviceMock) {
    return QueryMocks.mockTenantQuery(serviceMock);
  }

  public static JobQueryMock mockJobQuery(final ManagementService serviceMock) {
    return QueryMocks.mockJobQuery(serviceMock);
  }

  public static BatchQueryMock mockBatchQuery(final ManagementService serviceMock) {
    return QueryMocks.mockBatchQuery(serviceMock);
  }

  public static ProcessDefinitionStatisticsQueryMock mockProcessDefinitionStatisticsQuery(final ManagementService serviceMock) {
    return QueryMocks.mockProcessDefinitionStatisticsQuery(serviceMock);
  }

  public static JobDefinitionQueryMock mockJobDefinitionQuery(final ManagementService serviceMock) {
    return QueryMocks.mockJobDefinitionQuery(serviceMock);
  }

  public static DeploymentStatisticsQueryMock mockDeploymentStatisticsQuery(final ManagementService serviceMock) {
    return QueryMocks.mockDeploymentStatisticsQuery(serviceMock);
  }

  public static ActivityStatisticsQueryMock mockActivityStatisticsQuery(final ManagementService serviceMock) {
    return QueryMocks.mockActivityStatisticsQuery(serviceMock);
  }

  public static BatchStatisticsQueryMock mockBatchStatisticsQuery(final ManagementService serviceMock) {
    return QueryMocks.mockBatchStatisticsQuery(serviceMock);
  }

  public static AuthorizationQueryMock mockAuthorizationQuery(final AuthorizationService serviceMock) {
    return QueryMocks.mockAuthorizationQuery(serviceMock);
  }

  public static ExternalTaskQueryMock mockExternalTaskQuery(final ExternalTaskService serviceMock) {
    return QueryMocks.mockExternalTaskQuery(serviceMock);
  }

  public static VariableScopeFake variableScopeFake() {
    return new VariableScopeFake(){};
  }

  public static DelegateExecutionFake delegateExecutionFake() {
    return new DelegateExecutionFake();
  }

  public static DelegateCaseExecutionFake delegateCaseExecutionFake() { return new DelegateCaseExecutionFake(); }

  public static DelegateCaseVariableInstanceFake delegateCaseVariableInstanceFake() { return new DelegateCaseVariableInstanceFake(); }

  public static DelegateTaskFake delegateTaskFake() {
    return new DelegateTaskFake();
  }

  public static Set<String> candidateUserIds(DelegateTask task) {
    return DelegateTaskFake.candidateUserIds(task);
  }

  public static Set<String> candidateGroupIds(DelegateTask task) {
    return DelegateTaskFake.candidateGroupIds(task);
  }

  public static CallActivityMock registerCallActivityMock(final String processDefinitionKey) {
    return ProcessExpressions.registerCallActivityMock(processDefinitionKey);
  }

  public static RuntimeServiceFluentMock runtimeServiceFluentMock() {
    return new RuntimeServiceFluentMock();
  }

  public static RuntimeServiceFluentMock runtimeServiceFluentMock(RuntimeService runtimeServiceMock) {
    return new RuntimeServiceFluentMock(runtimeServiceMock);
  }
}
