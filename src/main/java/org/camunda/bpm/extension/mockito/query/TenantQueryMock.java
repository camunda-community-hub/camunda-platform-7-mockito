package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.TenantQuery;

public class TenantQueryMock extends AbstractQueryMock<TenantQueryMock, TenantQuery, Tenant, IdentityService> {

  public TenantQueryMock() {
    super(TenantQuery.class, IdentityService.class);
   }

}
