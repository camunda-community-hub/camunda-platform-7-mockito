package org.camunda.community.mockito.service;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.variable.VariableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.camunda.bpm.engine.variable.Variables.createVariables;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;


/**
 * Builder to stub the case service behavior regarding variables.
 */
public class CaseServiceStubBuilder {

  private final CaseService caseService;
  private final VariableMap variables;
  private final VariableMap localVariables;
  private final List<VariableFactory<?>> factories = new ArrayList<>();

  /**
   * Constructs the builder.
   *
   * @param caseService case service mocked by mockito.
   * @param variables      variables to use.
   * @param localVariables local variables to use.
   */
  public CaseServiceStubBuilder(CaseService caseService, VariableMap variables, VariableMap localVariables) {
    this.caseService = caseService;
    this.variables = variables;
    this.localVariables = localVariables;
  }

  /**
   * Constructs the builder with no variables.
   *
   * @param caseService case service mocked by mockito.
   */
  public CaseServiceStubBuilder(CaseService caseService) {
    this(caseService, createVariables(), createVariables());
  }

  /**
   * Defines a new variable to watch for.
   *
   * @param variableFactory variable factory for the variable.
   * @param <T>             type of the variable.
   *
   * @return builder.
   */
  public <T> CaseServiceStubBuilder define(VariableFactory<T> variableFactory) {
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
  public <T> CaseServiceStubBuilder defineAndInitialize(VariableFactory<T> variableFactory, T initialValue) {
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
  public <T> CaseServiceStubBuilder defineAndInitializeLocal(VariableFactory<T> variableFactory, T initialValue) {
    define(variableFactory);
    variableFactory.on(localVariables).set(initialValue);
    return this;
  }

  /**
   * Builds the stubs, configuring the mockito behavior on specified case service mock.
   */
  public CaseService build() {

    factories.forEach((factory) -> {
      // getVariable(String, String), setVariable(String, String, Object), getVariables(), getVariables(List<String>)
      doAnswer((invocation) -> factory.from(variables).get()).when(caseService).getVariable(anyString(), eq(factory.getName()));
      doAnswer((invocation) -> variables.put(factory.getName(), invocation.getArguments()[2])).when(caseService).setVariable(anyString(), eq(factory.getName()), any());
      doAnswer((invocation) -> variables).when(caseService).getVariables(anyString());
      doAnswer((invocation) -> {
        @SuppressWarnings("unchecked") final List<String> requestedVariableNames = (List<String>) invocation.getArguments()[1]; // [0]: id, [1]: list of vars
        return variables.entrySet().stream().filter((variable) -> requestedVariableNames.contains(variable.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
      }).when(caseService).getVariables(anyString(), anyList());

      // getVariableLocal(String, String), setVariableLocal(String, String, Object), getVariablesLocal(), getVariablesLocal(List<String>)
      doAnswer((invocation) -> factory.from(localVariables).get()).when(caseService).getVariableLocal(anyString(), eq(factory.getName()));
      doAnswer((invocation) -> localVariables.put(factory.getName(), invocation.getArguments()[2])).when(caseService)
                                                                                                   .setVariableLocal(anyString(), eq(factory.getName()), any());
      doAnswer((invocation) -> localVariables).when(caseService).getVariablesLocal(anyString());
      doAnswer((invocation) -> {
        @SuppressWarnings("unchecked") final List<String> requestedVariableNames = (List<String>) invocation.getArguments()[1]; // [0]: id, [1]: list of vars
        return localVariables.entrySet().stream().filter((variable) -> requestedVariableNames.contains(variable.getKey())).map(Map.Entry::getValue).collect(Collectors.toList());
      }).when(caseService).getVariablesLocal(anyString(), anyList());
    });

    return this.caseService;
  }
}
