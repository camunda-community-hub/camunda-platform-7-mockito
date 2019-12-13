package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;

public class ProcessDefinitionQueryMock extends AbstractQueryMock<ProcessDefinitionQueryMock, ProcessDefinitionQuery, ProcessDefinition, RepositoryService> {

  public ProcessDefinitionQueryMock() {
    super(ProcessDefinitionQuery.class, RepositoryService.class);
   }

}
