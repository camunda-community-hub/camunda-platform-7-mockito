package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.runtime.VariableInstanceQuery;

public class VariableInstanceQueryMock extends AbstractQueryMock<VariableInstanceQueryMock, VariableInstanceQuery, VariableInstance, RuntimeService> {

  public VariableInstanceQueryMock() {
    super(VariableInstanceQuery.class, RuntimeService.class);
   }

}
