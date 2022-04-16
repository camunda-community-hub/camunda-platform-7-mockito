package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.repository.CaseDefinitionQuery;

public class CaseDefinitionQueryMock extends AbstractQueryMock<CaseDefinitionQueryMock, CaseDefinitionQuery, CaseDefinition, RepositoryService> {

  public CaseDefinitionQueryMock() {
    super(CaseDefinitionQuery.class, RepositoryService.class);
   }

}
