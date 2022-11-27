package org.camunda.community.mockito.verify;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Verification of mocked service.
 */
public class TaskServiceVerification {
  private final TaskService taskService;

  /**
   * Constructs the verification.
   *
   * @param taskService service mock.
   */
  public TaskServiceVerification(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * Verifies if the variable has been retrieved from a global scope.
   *
   * @param variableFactory factory defining the variable.
   * @param executionId     execution id.
   * @param mode            mockito verification mode.
   * @param <T>             type of variable.
   */
  public <T> void verifyGet(VariableFactory<T> variableFactory, String executionId, VerificationMode mode) {
    verify(taskService, mode).getVariable(executionId, variableFactory.getName());
  }

  /**
   * Verifies if the variable has been retrieved from a global scope.
   *
   * @param variableFactory factory defining the variable.
   * @param executionId     execution id.
   * @param <T>             type of variable.
   */
  public <T> void verifyGet(VariableFactory<T> variableFactory, String executionId) {
    verifyGet(variableFactory, executionId, times(1));
  }

  /**
   * Verifies if the variable has been retrieved from a local scope.
   *
   * @param variableFactory factory defining the variable.
   * @param executionId     execution id.
   * @param mode            mockito verification mode.
   * @param <T>             type of variable.
   */
  public <T> void verifyGetLocal(VariableFactory<T> variableFactory, String executionId, VerificationMode mode) {
    verify(taskService, mode).getVariableLocal(executionId, variableFactory.getName());
  }

  /**
   * Verifies if the variable has been retrieved from a local scope.
   *
   * @param variableFactory factory defining the variable.
   * @param executionId     execution id.
   * @param <T>             type of variable.
   */
  public <T> void verifyGetLocal(VariableFactory<T> variableFactory, String executionId) {
    verifyGetLocal(variableFactory, executionId, times(1));
  }


  /**
   * Verifies if the variable has been set globally.
   * @param variableFactory factory defining the variable.
   * @param value value to set.
   * @param executionId execution id.
   * @param <T> type of variable.
   * @param mode verification mode.
   */
  public <T> void verifySet(VariableFactory<T> variableFactory, T value, String executionId, VerificationMode mode) {
    verify(taskService, mode).setVariable(executionId, variableFactory.getName(), variableFactory.on(taskService, executionId).getTypedValue(value, false));
  }

  /**
   * Verifies if the variable has been set globally.
   * @param variableFactory factory defining the variable.
   * @param value value to set.
   * @param executionId execution id.
   * @param <T> type of variable.
   */
  public <T> void verifySet(VariableFactory<T> variableFactory, T value, String executionId) {
    verifySet(variableFactory, value, executionId, times(1));
  }

  /**
   * Verifies if the variable has been set locally.
   * @param variableFactory factory defining the variable.
   * @param value value to set.
   * @param executionId execution id.
   * @param <T> type of variable.
   * @param mode verification mode.
   */
  public <T> void verifySetLocal(VariableFactory<T> variableFactory, T value, String executionId, VerificationMode mode) {
    verify(taskService, mode).setVariableLocal(executionId, variableFactory.getName(), variableFactory.on(taskService, executionId).getTypedValue(value, false));
  }

  /**
   * Verifies if the variable has been set locally.
   * @param variableFactory factory defining the variable.
   * @param value value to set.
   * @param executionId execution id.
   * @param <T> type of variable.
   */
  public <T> void verifySetLocal(VariableFactory<T> variableFactory, T value, String executionId) {
    verifySetLocal(variableFactory, value, executionId, times(1));
  }

  /**
   * Verifies if the variable has been removed from a global scope.
   * @param variableFactory factory defining the variable.
   * @param executionId execution id.
   * @param <T> type of variable.
   * @param mode verification mode.
   */
  public <T> void verifyRemove(VariableFactory<T> variableFactory, String executionId, VerificationMode mode) {
    verify(taskService, mode).removeVariable(executionId, variableFactory.getName());
  }

  /**
   * Verifies if the variable has been removed from a global scope.
   * @param variableFactory factory defining the variable.
   * @param executionId execution id.
   * @param <T> type of variable.
   */
  public <T> void verifyRemove(VariableFactory<T> variableFactory, String executionId) {
    verify(taskService).removeVariable(executionId, variableFactory.getName());
  }

  /**
   * Verifies if the variable has been removed from a local scope.
   * @param variableFactory factory defining the variable.
   * @param executionId execution id.
   * @param <T> type of variable.
   * @param mode verification mode.
   */
  public <T> void verifyRemoveLocal(VariableFactory<T> variableFactory, String executionId, VerificationMode mode) {
    verify(taskService, mode).removeVariableLocal(executionId, variableFactory.getName());
  }

  /**
   * Verifies if the variable has been removed from a local scope.
   * @param variableFactory factory defining the variable.
   * @param executionId execution id.
   * @param <T> type of variable.
   */
  public <T> void verifyRemoveLocal(VariableFactory<T> variableFactory, String executionId) {
    verify(taskService).removeVariableLocal(executionId, variableFactory.getName());
  }

  /**
   * Verifies retrieval of all process variables.
   * @param executionId execution id.
   * @param mode verification mode.
   */
  public void verifyGetVariables(String executionId, VerificationMode mode) {
    verify(taskService, mode).getVariables(executionId);
  }

  /**
   * Verifies retrieval of all process variables.
   * @param executionId execution id.
   */
  public void verifyGetVariables(String executionId) {
    verify(taskService).getVariables(executionId);
  }

  /**
   * Verifies if the task has been completed without variables.
   * @param taskId task id.
   */
  public void verifyComplete(String taskId) {
    verify(taskService).complete(taskId);
  }

  /**
   * Verifies if the task has been completed with variables.
   * @param taskId task id.
   * @param variables variables to complete the task.
   */
  public void verifyComplete(VariableMap variables, String taskId) {
    verify(taskService).complete(taskId, variables);
  }
  /**
   * No further interaction with mock.
   */
  public void verifyNoMoreInteractions() {
    Mockito.verifyNoMoreInteractions(taskService);
  }

}
