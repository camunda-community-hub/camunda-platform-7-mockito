package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.DecisionDefinition;
import org.camunda.bpm.engine.repository.DecisionDefinitionQuery;

public class DecisionDefinitionQueryMock extends AbstractQueryMock<DecisionDefinitionQueryMock, DecisionDefinitionQuery, DecisionDefinition, RepositoryService> {

  public DecisionDefinitionQueryMock() {
    super(DecisionDefinitionQuery.class, RepositoryService.class);
   }

}
