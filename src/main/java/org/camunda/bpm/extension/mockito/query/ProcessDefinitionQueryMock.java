package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class ProcessDefinitionQueryMock extends AbstractQueryMock<ProcessDefinitionQueryMock, ProcessDefinitionQuery, ProcessDefinition, RepositoryService> {

  public ProcessDefinitionQueryMock() {
    super(ProcessDefinitionQuery.class, RepositoryService.class);
   }

}
