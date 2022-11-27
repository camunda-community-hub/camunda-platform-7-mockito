package org.camunda.community.mockito;

import static org.camunda.community.mockito.Expressions.getRegistered;
import static org.camunda.community.mockito.Expressions.registerInstance;
import static org.camunda.community.mockito.function.NameForType.juelNameFor;

import javax.annotation.Nonnull;

import java.net.URL;

import org.apache.commons.lang3.tuple.Pair;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.community.mockito.function.ParseDelegateExpressions;
import org.camunda.community.mockito.mock.FluentExecutionListenerMock;
import org.camunda.community.mockito.mock.FluentJavaDelegateMock;
import org.camunda.community.mockito.mock.FluentTaskListenerMock;
import org.camunda.community.mockito.verify.ExecutionListenerVerification;
import org.camunda.community.mockito.verify.JavaDelegateVerification;
import org.camunda.community.mockito.verify.MockitoVerification;
import org.camunda.community.mockito.verify.TaskListenerVerification;

/**
 * Util class for mocking DelegateExpressions as used in the modeller.
 */
@SuppressWarnings("unused")
public enum DelegateExpressions {
  ;

  /**
   * Takes a BPMN resource and registers mocks for all delegateExpressions.
   *
   * @param bpmnFileResource the bpm file resource to parse
   * @see #autoMock(java.net.URL)
   */
  public static void autoMock(@Nonnull String bpmnFileResource) {

    URL bpmn = DelegateExpressions.class.getResource(bpmnFileResource);
    if (bpmn == null && !bpmnFileResource.startsWith("/")) {
      autoMock("/" + bpmnFileResource);
      return;
    }

    autoMock(bpmn);
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
    for (Pair<ParseDelegateExpressions.ExpressionType, String> pair : new ParseDelegateExpressions().apply(bpmnFile)) {
      pair.getLeft().registerMock(pair.getRight());
    }
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
    return registerInstance(name, new FluentJavaDelegateMock());
  }

  /**
   * Registers a new FluentJavaDelegateMock instance for name (by type).
   *
   * @param type the type to register
   * @return new fluent-mock instance
   */
  public static FluentJavaDelegateMock registerJavaDelegateMock(final Class<? extends JavaDelegate> type) {
    return registerJavaDelegateMock(juelNameFor(type));
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
    return registerInstance(name, new FluentExecutionListenerMock());
  }

  /**
   * Registers a new FluentExecutionListenerMock instance for name (by type).
   *
   * @param type the type to register
   * @return new fluent-mock instance
   */
  public static FluentExecutionListenerMock registerExecutionListenerMock(final Class<? extends ExecutionListener> type) {
    return registerExecutionListenerMock(juelNameFor(type));
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
    return registerInstance(name, new FluentTaskListenerMock());
  }

  /**
   * Registers a new FluentTaskListenerMock instance for name (by type).
   *
   * @param type the type to register
   * @return new fluent-mock instance
   */
  public static FluentTaskListenerMock registerTaskListenerMock(final Class<? extends TaskListener> type) {
    return registerTaskListenerMock(juelNameFor(type));
  }

  /**
   * Returns the registered FluentJavaDelegateMock instance for name.
   *
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#getRegistered(String)
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
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#getRegistered(String)
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
   * @param name the name under which the instance is registered
   * @return the registered fluent-mock instance
   * @see org.camunda.community.mockito.Expressions#getRegistered(String)
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
   * @param name the name under which the instance is registered
   * @return verification for JavaDelegate
   * @see #verifyJavaDelegateMock(org.camunda.community.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final String name) {
    return verifyJavaDelegateMock(getJavaDelegateMock(name));
  }

  /**
   * Gets the registered FluentJavaDelegateMock and creates a verification
   * instance.
   *
   * @param type the type of the delegate to lookup
   * @return verification for JavaDelegate
   */
  public static MockitoVerification<DelegateExecution> verifyJavaDelegateMock(final Class<?> type) {
    return verifyJavaDelegateMock(getJavaDelegateMock(type));
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
   * Gets the registered FluentExecutionListenerMock and creates a verification
   * instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for ExecutionListener
   * @see #verifyJavaDelegateMock(org.camunda.community.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final String name) {
    return verifyExecutionListenerMock(getExecutionListenerMock(name));
  }

  /**
   * Gets the registered FluentExecutionListenerMock and creates a verification
   * instance.
   *
   * @param type the type of the listener to lookup
   * @return verification for ExecutionListener
   */
  public static MockitoVerification<DelegateExecution> verifyExecutionListenerMock(final Class<?> type) {
    return verifyExecutionListenerMock(getExecutionListenerMock(type));
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
   * Gets the registered FluentTaskListenerMock and creates a verification
   * instance.
   *
   * @param name the name under which the instance is registered
   * @return verification for TaskListener
   * @see #verifyJavaDelegateMock(org.camunda.community.mockito.mock.FluentJavaDelegateMock)
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final String name) {
    return verifyTaskListenerMock(getTaskListenerMock(name));
  }

  /**
   * Gets the registered FluentExecutionListenerMock and creates a verification
   * instance.
   *
   * @param type the type of the listener to lookup
   * @return verification for TaskListener
   */
  public static MockitoVerification<DelegateTask> verifyTaskListenerMock(final Class<?> type) {
    return verifyTaskListenerMock(getTaskListenerMock(type));
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
