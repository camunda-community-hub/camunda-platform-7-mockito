package org.camunda.bpm.extension.mockito;

import static com.google.common.io.Resources.getResource;
import static org.camunda.bpm.extension.mockito.Expressions.getRegistered;
import static org.camunda.bpm.extension.mockito.Expressions.registerInstance;

import java.net.URL;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.Pair;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.extension.mockito.function.ParseDelegateExpressions;
import org.camunda.bpm.extension.mockito.mock.FluentExecutionListenerMock;
import org.camunda.bpm.extension.mockito.mock.FluentJavaDelegateMock;
import org.camunda.bpm.extension.mockito.mock.FluentTaskListenerMock;
import org.camunda.bpm.extension.mockito.verify.ExecutionListenerVerification;
import org.camunda.bpm.extension.mockito.verify.JavaDelegateVerification;
import org.camunda.bpm.extension.mockito.verify.MockitoVerification;
import org.camunda.bpm.extension.mockito.verify.TaskListenerVerification;

/**
 * Util class for mocking DelegateExpressions as used in the modeller.
 */
@SuppressWarnings("unused")
public final class DelegateExpressions {

  /**
   * Hide default constructor
   */
  private DelegateExpressions() {
    // do not instantiate
  }

    /**
     * Takes a BPMN resource and registers mocks for all delegateExpressions.
     *
     * @see #autoMock(java.net.URL)
     *
     * @param bpmnFileResource the bpm file resource to parse
     */
  public static void autoMock(final @Nonnull String bpmnFileResource) {
    autoMock(getResource(bpmnFileResource));
  }

  /**
   * Takes a BPMN file and registers TaskListener-, ExecutionListener and
   * JavaDelegate-Mocks for every delegateExpression encountered.
   *
   * This is an auto-mock feature that allows the process to run. If you need to
   * modify the behavior of the mock, you can use the getXXX() methods to access
   * it by its name.
   *
   * @param bpmnFile
   *          the BPMN resource to parse
   */
  @SuppressWarnings("ConstantConditions")
  public static void autoMock(final @Nonnull URL bpmnFile) {
    for (Pair<ParseDelegateExpressions.ExpressionType, String> pair : new ParseDelegateExpressions().apply(bpmnFile)) {
      pair.getLeft().registerMock(pair.getRight());
    }
  }

  /**
   * Registers a new FluentJavaDelegateMock instance for name.
   *
   * @param name
   *          the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.bpm.extension.mockito.Expressions#registerInstance(String,
   *      Object)
   */
  public static FluentJavaDelegateMock registerJavaDelegateMock(final String name) {
    return registerInstance(name, new FluentJavaDelegateMock());
  }

  /**
   * Registers a new FluentExecutionListenerMock instance for name.
   *
   * @param name
   *          the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.bpm.extension.mockito.Expressions#registerInstance(String,
   *      Object)
   */
  public static FluentExecutionListenerMock registerExecutionListenerMock(final String name) {
    return registerInstance(name, new FluentExecutionListenerMock());
  }

  /**
   * Registers a new FluentTaskListenerMock instance for name.
   *
   * @param name
   *          the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.bpm.extension.mockito.Expressions#registerInstance(String,
   *      Object)
   */
  public static FluentTaskListenerMock registerTaskListenerMock(final String name) {
    return registerInstance(name, new FluentTaskListenerMock());
  }

  /**
   * Returns the registered FluentJavaDelegateMock instance for name.
   *
   * @param name
   *          the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.bpm.extension.mockito.Expressions#getRegistered(String)
   */
  public static FluentJavaDelegateMock getJavaDelegateMock(final String name) {
    return getRegistered(name);
  }


  public static FluentJavaDelegateMock getJavaDelegateMock(final Class<?> type) {
    return getRegistered(type);
  }

  /**
   * Returns the registered FluentExecutionListenerMock instance for name.
   *
   * @param name
   *          the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.bpm.extension.mockito.Expressions#getRegistered(String)
   */
  public static FluentExecutionListenerMock getExecutionListenerMock(final String name) {
    return getRegistered(name);
  }

  public static FluentExecutionListenerMock getExecutionListenerMock(final Class<?> type) {
    return getRegistered(type);
  }

  /**
   * Returns the registered FluentTaskListenerMock instance for name.
   *
   * @param name
   *          the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.bpm.extension.mockito.Expressions#getRegistered(String)
   */
  public static FluentTaskListenerMock getTaskListenerMock(final String name) {
    return getRegistered(name);
  }

  public static FluentTaskListenerMock getTaskListenerMock(final Class<?> type) {
    return getRegistered(type);
  }

  /**
   * Gets the registered FluentJavaDelegateMock and creates a verification
   * instance.
   *
   * @param name
   *          the name under which the instance is registered
   * @return verification for JavaDelegate
   * @see #verifyJavaDelegateMock(org.camunda.bpm.extension.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final String name) {
    return verifyJavaDelegateMock(getJavaDelegateMock(name));
  }
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final Class<?> type) {
    return verifyJavaDelegateMock(getJavaDelegateMock(type));
  }

  /**
   * Creates a verification instance for JavaDelegate.
   *
   * @param fluentJavaDelegateMock
   *          the fluent-mock instance
   * @return verification for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final FluentJavaDelegateMock fluentJavaDelegateMock) {
    return new JavaDelegateVerification(fluentJavaDelegateMock.getMock());
  }

  /**
   * Gets the registered FluentExecutionListenerMock and creates a verification
   * instance.
   *
   * @param name
   *          the name under which the instance is registered
   * @return verification for ExecutionListener
   * @see #verifyJavaDelegateMock(org.camunda.bpm.extension.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final String name) {
    return verifyExecutionListenerMock(getExecutionListenerMock(name));
  }

  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final Class<?> type) {
    return verifyExecutionListenerMock(getExecutionListenerMock(type));
  }

  /**
   * Creates a verification instance for ExecutionListener.
   *
   * @param fluentExecutionListenerMock
   *          the fluent-mock instance
   * @return verification for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final FluentExecutionListenerMock fluentExecutionListenerMock) {
    return new ExecutionListenerVerification(fluentExecutionListenerMock.getMock());
  }

  /**
   * Gets the registered FluentTaskListenerMock and creates a verification
   * instance.
   *
   * @param name
   *          the name under which the instance is registered
   * @return verification for TaskListener
   * @see #verifyJavaDelegateMock(org.camunda.bpm.extension.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final String name) {
    return verifyTaskListenerMock(getTaskListenerMock(name));
  }

  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final Class<?> type) {
    return verifyTaskListenerMock(getTaskListenerMock(type));
  }

  /**
   * Creates a verification instance for TaskListener.
   *
   * @param fluentTaskListenerMock
   *          the fluent-mock instance
   * @return verification for TaskListener
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final FluentTaskListenerMock fluentTaskListenerMock) {
    return new TaskListenerVerification(fluentTaskListenerMock.getMock());
  }

}
