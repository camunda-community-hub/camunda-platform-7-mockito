package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;


/**
 * Builder to stub the runtime service behavior regarding variables.
 */
public class RuntimeServiceStubBuilder {

  private final RuntimeService runtimeService;
  private final VariableMap variables;
  private final VariableMap localVariables;
  private final List<VariableFactory<?>> factories = new ArrayList<>();

  /**
   * Constructs the builder.
   *
   * @param runtimeService runtime service mocked by mockito.
   * @param variables      variables to use.
   * @param localVariables local variables to use.
   */
  public RuntimeServiceStubBuilder(RuntimeService runtimeService, VariableMap variables, VariableMap localVariables) {
    this.runtimeService = runtimeService;
    this.variables = variables;
    this.localVariables = localVariables;
  }

  /**
   * Constructs the builder with no variables.
   *
   * @param runtimeService runtime service mocked by mockito.
   */
  public RuntimeServiceStubBuilder(RuntimeService runtimeService) {
    this(runtimeService, createVariables(), createVariables());
  }

  /**
   * Defines a new variable to watch for.
   *
   * @param variableFactory variable factory for the variable.
   * @param <T>             type of the variable.
   *
   * @return builder.
   */
  public <T> RuntimeServiceStubBuilder define(VariableFactory<T> variableFactory) {
    this.factories.add(variableFactory);
    return this;
  }

  /**
   * Defines a new variable to watch for and sets initial value.
   *
   * @param variableFactory variable factory for the variable.
   * @param initialValue    initial value.
   * @param <T>             type of the variable.
   *
   * @return builder.
   */
  public <T> RuntimeServiceStubBuilder defineAndInitialize(VariableFactory<T> variableFactory, T initialValue) {
    define(variableFactory);
    variableFactory.on(variables).set(initialValue);
    return this;
  }

  /**
   * Defines a new variable to watch for and sets initial value to local variable.
   *
   * @param variableFactory variable factory for the variable.
   * @param initialValue    initial value.
   * @param <T>             type of the variable.
   *
   * @return builder.
   */
  public <T> RuntimeServiceStubBuilder defineAndInitializeLocal(VariableFactory<T> variableFactory, T initialValue) {
    define(variableFactory);
    variableFactory.on(localVariables).set(initialValue);
    return this;
  }

  /**
   * Builds the stubs, configuring the mockito behavior on specified runtime service mock.
   */
  public RuntimeService build() {

    factories.forEach((factory) -> {
      // getVariable(String, String), setVariable(String, String, Object), getVariables(), getVariables(List<String>)
      doAnswer((invocation) -> factory.from(variables).get()).when(runtimeService).getVariable(anyString(), eq(factory.getName()));
      doAnswer((invocation) -> variables.put(factory.getName(), invocation.getArguments()[2])).when(runtimeService).setVariable(anyString(), eq(factory.getName()), any());
      doAnswer((invocation) -> variables).when(runtimeService).getVariables(anyString());
      doAnswer((invocation) -> {
        @SuppressWarnings("unchecked") final List<String> requestedVariableNames = (List<String>) invocation.getArguments()[1]; // [0]: id, [1]: list of vars
        return variables.entrySet().stream().filter((variable) -> requestedVariableNames.contains(variable.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
      }).when(runtimeService).getVariables(anyString(), anyList());

      // getVariableLocal(String, String), setVariableLocal(String, String, Object), getVariablesLocal(), getVariablesLocal(List<String>)
      doAnswer((invocation) -> factory.from(localVariables).get()).when(runtimeService).getVariableLocal(anyString(), eq(factory.getName()));
      doAnswer((invocation) -> localVariables.put(factory.getName(), invocation.getArguments()[2])).when(runtimeService)
                                                                                                   .setVariableLocal(anyString(), eq(factory.getName()), any());
      doAnswer((invocation) -> localVariables).when(runtimeService).getVariablesLocal(anyString());
      doAnswer((invocation) -> {
        @SuppressWarnings("unchecked") final List<String> requestedVariableNames = (List<String>) invocation.getArguments()[1]; // [0]: id, [1]: list of vars
        return localVariables.entrySet().stream().filter((variable) -> requestedVariableNames.contains(variable.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
      }).when(runtimeService).getVariablesLocal(anyString(), anyList());
    });

    return this.runtimeService;
  }
}
