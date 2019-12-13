package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentQuery;

public class DeploymentQueryMock extends AbstractQueryMock<DeploymentQueryMock, DeploymentQuery, Deployment, RepositoryService> {

  public DeploymentQueryMock() {
    super(DeploymentQuery.class, RepositoryService.class);
   }

}
