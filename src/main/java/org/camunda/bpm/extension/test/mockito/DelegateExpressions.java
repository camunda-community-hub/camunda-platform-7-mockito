package org.camunda.bpm.extension.test.mockito;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.extension.test.mockito.mock.FluentExecutionListenerMock;
import org.camunda.bpm.extension.test.mockito.mock.FluentJavaDelegateMock;
import org.camunda.bpm.extension.test.mockito.mock.FluentTaskListenerMock;
import org.camunda.bpm.extension.test.mockito.verify.ExecutionListenerVerification;
import org.camunda.bpm.extension.test.mockito.verify.JavaDelegateVerification;
import org.camunda.bpm.extension.test.mockito.verify.MockitoVerification;
import org.camunda.bpm.extension.test.mockito.verify.TaskListenerVerification;

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

  public static FluentJavaDelegateMock registerJavaDelegateMock(final String name) {
    final FluentJavaDelegateMock delegateMock = new FluentJavaDelegateMock();
    registerInstance(name, delegateMock.getMock());
    return delegateMock;
  }

  public static FluentExecutionListenerMock registerExecutionListenerMock(final String name) {
    final FluentExecutionListenerMock delegateMock = new FluentExecutionListenerMock();
    registerInstance(name, delegateMock.getMock());
    return delegateMock;
  }

  public static FluentTaskListenerMock registerTaskListenerMock(final String name) {
    final FluentTaskListenerMock delegateMock = new FluentTaskListenerMock();
    registerInstance(name, delegateMock.getMock());
    return delegateMock;
  }

  public static JavaDelegate getRegisteredJavaDelegate(final String name) {
    return getRegistered(name);
  }

  /**
   * @param javaDelegateType type to get the name from
   * @return the registered {@link org.camunda.bpm.engine.delegate.JavaDelegate}
   * @see #getRegisteredJavaDelegate(String)
   */
  public static JavaDelegate getRegisteredJavaDelegate(final Class<JavaDelegate> javaDelegateType) {
    return getRegisteredJavaDelegate(juelNameFor(javaDelegateType));
  }

  /**
   * @param executionListenerType type to get the name from
   * @return the registered {@link org.camunda.bpm.engine.delegate.ExecutionListener}
   * @see #getRegisteredExecutionListener(String)
   */
  public static ExecutionListener getRegisteredExecutionListener(final Class<ExecutionListener> executionListenerType) {
    return getRegisteredExecutionListener(juelNameFor(executionListenerType));
  }

  public static ExecutionListener getRegisteredExecutionListener(final String name) {
    return getRegistered(name);
  }

  /**
   * @param taskListenerType type to get the name from
   * @return the registered {@link org.camunda.bpm.engine.delegate.TaskListener}
   * @see #getRegisteredTaskListener(String)
   */
  public static TaskListener getRegisteredTaskListener(final Class<TaskListener> taskListenerType) {
    return getRegistered(juelNameFor(taskListenerType));
  }

  public static TaskListener getRegisteredTaskListener(final String name) {
    return getRegistered(name);
  }

  public static MockitoVerification<DelegateExecution> verifyJavaDelegate(final Class<? extends JavaDelegate> javaDelegateType) {
    return verifyJavaDelegate(juelNameFor(javaDelegateType));
  }

  public static MockitoVerification<DelegateExecution> verifyJavaDelegate(final String name) {
    return verifyJavaDelegate(getRegisteredJavaDelegate(name));
  }

  public static MockitoVerification<DelegateExecution> verifyJavaDelegate(final FluentJavaDelegateMock fluentJavaDelegateMock) {
    return verifyJavaDelegate(fluentJavaDelegateMock.getMock());
  }

  public static MockitoVerification<DelegateExecution> verifyJavaDelegate(final JavaDelegate javaDelegateMock) {
    return new JavaDelegateVerification(javaDelegateMock);
  }

  public static MockitoVerification<DelegateExecution> verifyExecutionListener(final Class<? extends ExecutionListener> executionListenerType) {
    return verifyExecutionListener(getRegisteredExecutionListener(juelNameFor(executionListenerType)));
  }

  public static MockitoVerification<DelegateExecution> verifyExecutionListener(final String name) {
    return verifyExecutionListener(getRegisteredExecutionListener(name));
  }

  public static MockitoVerification<DelegateExecution> verifyExecutionListener(final FluentExecutionListenerMock fluentExecutionListenerMock) {
    return verifyExecutionListener(fluentExecutionListenerMock.getMock());
  }

  public static MockitoVerification<DelegateExecution> verifyExecutionListener(final ExecutionListener executionListenerMock) {
    return new ExecutionListenerVerification(executionListenerMock);
  }

  public static MockitoVerification<DelegateTask> verifyTaskListener(final Class<? extends TaskListener> taskListenerType) {
    return verifyTaskListener(getRegisteredTaskListener(juelNameFor(taskListenerType)));
  }

  public static MockitoVerification<DelegateTask> verifyTaskListener(final String name) {
    return verifyTaskListener(getRegisteredTaskListener(name));
  }

  public static MockitoVerification<DelegateTask> verifyTaskListener(final FluentTaskListenerMock fluentTaskListenerMock) {
    return verifyTaskListener(fluentTaskListenerMock.getMock());
  }

  public static MockitoVerification<DelegateTask> verifyTaskListener(final TaskListener taskListenerMock) {
    return new TaskListenerVerification(taskListenerMock);
  }

}
