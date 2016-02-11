package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.runtime.VariableInstanceQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class VariableInstanceQueryMock extends AbstractQueryMock<VariableInstanceQueryMock, VariableInstanceQuery, VariableInstance, RuntimeService> {

  public VariableInstanceQueryMock() {
    super(VariableInstanceQuery.class, RuntimeService.class);
  }

}
