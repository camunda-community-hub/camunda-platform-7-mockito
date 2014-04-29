package org.camunda.bpm.extension.test.mockito.query;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentQuery;

/**
 * @author Jan Galinski, Holisticon AG
 */
public class DeploymentQueryMock extends AbstractQueryMock<DeploymentQueryMock, DeploymentQuery, Deployment, RepositoryService> {

  public DeploymentQueryMock() {
    super(DeploymentQuery.class, RepositoryService.class);
  }

}
