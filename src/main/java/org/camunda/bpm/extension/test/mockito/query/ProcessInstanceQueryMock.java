package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class ProcessInstanceQueryMock extends AbstractQueryMock<ProcessInstanceQueryMock, ProcessInstanceQuery, ProcessInstance, RuntimeService> {

  public ProcessInstanceQueryMock() {
    super(ProcessInstanceQuery.class, RuntimeService.class);
  }

}
