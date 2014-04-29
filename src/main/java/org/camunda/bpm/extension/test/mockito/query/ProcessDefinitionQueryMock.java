package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.AuthorizationQuery;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class ProcessDefinitionQueryMock extends AbstractQueryMock<ProcessDefinitionQueryMock, ProcessDefinitionQuery, ProcessDefinition, RepositoryService> {

  public ProcessDefinitionQueryMock() {
    super(ProcessDefinitionQuery.class, RepositoryService.class);
  }

}
