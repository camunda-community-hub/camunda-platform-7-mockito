package org.camunda.community.mockito.verify;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Verification of mocked service.
 */
public class RuntimeServiceVerification {
  private final RuntimeService runtimeService;

  /**
   * Constructs the verification.
   *
   * @param runtimeService service mock.
   */
  public RuntimeServiceVerification(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
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
    verify(runtimeService, mode).getVariable(executionId, variableFactory.getName());
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
    verify(runtimeService, mode).getVariableLocal(executionId, variableFactory.getName());
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
    verify(runtimeService, mode).setVariable(executionId, variableFactory.getName(), variableFactory.on(runtimeService, executionId).getTypedValue(value, false));
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
    verify(runtimeService, mode).setVariableLocal(executionId, variableFactory.getName(), variableFactory.on(runtimeService, executionId).getTypedValue(value, false));
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
   * No further interaction with mock.
   */
  public void verifyNoMoreInteractions() {
    Mockito.verifyNoMoreInteractions(runtimeService);
  }

}
