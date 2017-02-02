package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class ProcessInstanceQueryMock extends AbstractQueryMock<ProcessInstanceQueryMock, ProcessInstanceQuery, ProcessInstance, RuntimeService> {

  public ProcessInstanceQueryMock() {
    super(ProcessInstanceQuery.class, RuntimeService.class);
   }

}
