package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.repository.CaseDefinitionQuery;

public class CaseDefinitionQueryMock extends AbstractQueryMock<CaseDefinitionQueryMock, CaseDefinitionQuery, CaseDefinition, CaseService>{

  public CaseDefinitionQueryMock() {
    super(CaseDefinitionQuery.class, CaseService.class);
  }

}
