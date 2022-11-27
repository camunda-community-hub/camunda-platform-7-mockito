package org.camunda.community.mockito;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.community.mockito.service.CaseServiceStubBuilder;
import org.camunda.community.mockito.service.RuntimeServiceStubBuilder;
import org.camunda.community.mockito.service.TaskServiceStubBuilder;
import org.camunda.community.mockito.verify.CaseServiceVerification;
import org.camunda.community.mockito.verify.RuntimeServiceVerification;
import org.camunda.community.mockito.verify.TaskServiceVerification;

/**
 * Util class to access the helpers for mocking of Camunda Java Service API.
 */
public class ServiceExpressions {

  /**
   * Constructs a task service variable mock builder.
   *
   * @param taskService task service mock.
   *
   * @return fluent builder.
   */
  public static TaskServiceStubBuilder taskServiceVariableStubBuilder(TaskService taskService) {
    return new TaskServiceStubBuilder(taskService);
  }

  /**
   * Constructs a task service variable mock builder.
   *
   * @param taskService    task service mock.
   * @param variables      variable map to reuse.
   * @param localVariables local variable map to reuse.
   *
   * @return fluent builder.
   */
  public static TaskServiceStubBuilder taskServiceVariableStubBuilder(TaskService taskService, VariableMap variables, VariableMap localVariables) {
    return new TaskServiceStubBuilder(taskService, variables, localVariables);
  }

  /**
   * Creates verification for task service.
   *
   * @param taskService task service mock.
   *
   * @return verification.
   */
  public static TaskServiceVerification taskServiceVerification(TaskService taskService) {
    return new TaskServiceVerification(taskService);
  }

  /**
   * Constructs a runtime service variable mock builder.
   *
   * @param runtimeService runtime service mock.
   *
   * @return fluent builder.
   */
  public static RuntimeServiceStubBuilder runtimeServiceVariableStubBuilder(RuntimeService runtimeService) {
    return new RuntimeServiceStubBuilder(runtimeService);
  }

  /**
   * Constructs a runtime service variable mock builder.
   *
   * @param runtimeService runtime service mock.
   * @param variables      variable map to reuse.
   * @param localVariables local variable map to reuse.
   *
   * @return fluent builder.
   */
  public static RuntimeServiceStubBuilder runtimeServiceVariableStubBuilder(RuntimeService runtimeService, VariableMap variables, VariableMap localVariables) {
    return new RuntimeServiceStubBuilder(runtimeService, variables, localVariables);
  }

  /**
   * Creates verification for runtime service.
   *
   * @param runtimeService runtime service mock.
   *
   * @return verification.
   */
  public static RuntimeServiceVerification runtimeServiceVerification(RuntimeService runtimeService) {
    return new RuntimeServiceVerification(runtimeService);
  }

  /**
   * Constructs a case service variable mock builder.
   *
   * @param caseService case service mock.
   *
   * @return fluent builder.
   */
  public static CaseServiceStubBuilder caseServiceVariableStubBuilder(CaseService caseService) {
    return new CaseServiceStubBuilder(caseService);
  }

  /**
   * Constructs a case service variable mock builder.
   *
   * @param caseService    case service mock.
   * @param variables      variable map to reuse.
   * @param localVariables local variable map to reuse.
   *
   * @return fluent builder.
   */
  public static CaseServiceStubBuilder caseServiceVariableStubBuilder(CaseService caseService, VariableMap variables, VariableMap localVariables) {
    return new CaseServiceStubBuilder(caseService, variables, localVariables);
  }

  /**
   * Creates verification for case service.
   *
   * @param caseService case service mock.
   *
   * @return verification.
   */
  public static CaseServiceVerification caseServiceVerification(CaseService caseService) {
    return new CaseServiceVerification(caseService);
  }

}
