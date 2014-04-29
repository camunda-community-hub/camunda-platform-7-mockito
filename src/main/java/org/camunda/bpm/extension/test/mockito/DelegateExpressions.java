package org.camunda.bpm.extension.test.mockito;

import com.google.common.collect.Multimap;
import com.sun.imageio.plugins.bmp.BMPConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.extension.test.mockito.function.ParseDelegateExpressions;
import org.camunda.bpm.extension.test.mockito.mock.FluentExecutionListenerMock;
import org.camunda.bpm.extension.test.mockito.mock.FluentJavaDelegateMock;
import org.camunda.bpm.extension.test.mockito.mock.FluentTaskListenerMock;
import org.camunda.bpm.extension.test.mockito.verify.ExecutionListenerVerification;
import org.camunda.bpm.extension.test.mockito.verify.JavaDelegateVerification;
import org.camunda.bpm.extension.test.mockito.verify.MockitoVerification;
import org.camunda.bpm.extension.test.mockito.verify.TaskListenerVerification;
import org.camunda.bpm.model.bpmn.impl.BpmnModelConstants;

import java.net.URL;

import static org.camunda.bpm.extension.test.mockito.Expressions.getRegistered;
import static org.camunda.bpm.extension.test.mockito.Expressions.registerInstance;
import static org.camunda.bpm.extension.test.mockito.function.NameForType.juelNameFor;

/**
 * Util class for mocking DelegateExpressions as used in the modeller.
 */
public final class DelegateExpressions {

  /**
   * Hide default constructor
   */
  private DelegateExpressions() {
    // do not instantiate
  }

  /**
   * Takes a BPMN file and registers TaskListener-, ExecutionListener and JavaDelegate-Mocks for every
   * delegateExpression encountered.
   *
   * This is an auto-mock feature that allows the process to run. If you need to modify the behavior of the mock,
   * you can use the getXXX() methods to access it by its name.
   *
   * @param bpmnFile the BPMN resource to parse
   */
  public static void registerDelegateExpressionMocks(final URL bpmnFile) {
    for (String name : ParseDelegateExpressions.EXECUTION_LISTENER.apply(bpmnFile)) {
      registerExecutionListenerMock(name);
    }
    for (String name : ParseDelegateExpressions.TASK_LISTENER.apply(bpmnFile)) {
      registerTaskListenerMock(name);
    }
    for (String name : ParseDelegateExpressions.JAVA_DELEGATE.apply(bpmnFile)) {
      registerJavaDelegateMock(name);
    }
  }

  /**
   * Registers a new FluentJavaDelegateMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.bpm.extension.test.mockito.Expressions#registerInstance(String, Object)
   */
  public static FluentJavaDelegateMock registerJavaDelegateMock(final String name) {
    return registerInstance(name, new FluentJavaDelegateMock());
  }

  /**
   * Registers a new FluentExecutionListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.bpm.extension.test.mockito.Expressions#registerInstance(String, Object)
   */
  public static FluentExecutionListenerMock registerExecutionListenerMock(final String name) {
    return registerInstance(name, new FluentExecutionListenerMock());
  }

  /**
   * Registers a new FluentTaskListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return new fluent-mock instance
   * @see org.camunda.bpm.extension.test.mockito.Expressions#registerInstance(String, Object)
   */
  public static FluentTaskListenerMock registerTaskListenerMock(final String name) {
    return registerInstance(name, new FluentTaskListenerMock());
  }

  /**
   * Returns the registered FluentJavaDelegateMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.bpm.extension.test.mockito.Expressions#getRegistered(String)
   */
  public static FluentJavaDelegateMock getJavaDelegateMock(final String name) {
    return getRegistered(name);
  }

  /**
   * Returns the registered FluentExecutionListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.bpm.extension.test.mockito.Expressions#getRegistered(String)
   */
  public static FluentExecutionListenerMock getExecutionListenerMock(final String name) {
    return getRegistered(name);
  }

  /**
   * Returns the registered FluentTaskListenerMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.bpm.extension.test.mockito.Expressions#getRegistered(String)
   */
  public static FluentTaskListenerMock getTaskListenerMock(final String name) {
    return getRegistered(name);
  }

  /**
   * Gets the registered FluentJavaDelegateMock and creates a verification instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for JavaDelegate
   * @see #verifyJavaDelegateMock(org.camunda.bpm.extension.test.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final String name) {
    return verifyJavaDelegateMock(getJavaDelegateMock(name));
  }

  /**
   * Creates a verification instance for JavaDelegate.
   *
   * @param fluentJavaDelegateMock the fluent-mock instance
   * @return verification for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final FluentJavaDelegateMock fluentJavaDelegateMock) {
    return new JavaDelegateVerification(fluentJavaDelegateMock.getMock());
  }

  /**
   * Gets the registered FluentExecutionListenerMock and creates a verification instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for ExecutionListener
   * @see #verifyJavaDelegateMock(org.camunda.bpm.extension.test.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final String name) {
    return verifyExecutionListenerMock(getExecutionListenerMock(name));
  }

  /**
   * Creates a verification instance for ExecutionListener.
   *
   * @param fluentExecutionListenerMock the fluent-mock instance
   * @return verification for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final FluentExecutionListenerMock fluentExecutionListenerMock) {
    return new ExecutionListenerVerification(fluentExecutionListenerMock.getMock());
  }

  /**
   * Gets the registered FluentTaskListenerMock and creates a verification instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for TaskListener
   * @see #verifyJavaDelegateMock(org.camunda.bpm.extension.test.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final String name) {
    return verifyTaskListenerMock(getTaskListenerMock(name));
  }

  /**
   * Creates a verification instance for TaskListener.
   *
   * @param fluentTaskListenerMock the fluent-mock instance
   * @return verification for TaskListener
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final FluentTaskListenerMock fluentTaskListenerMock) {
    return new TaskListenerVerification(fluentTaskListenerMock.getMock());
  }

}
