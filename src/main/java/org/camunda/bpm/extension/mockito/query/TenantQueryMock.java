package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.TenantQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class TenantQueryMock extends AbstractQueryMock<TenantQueryMock, TenantQuery, Tenant, IdentityService> {

  public TenantQueryMock() {
    super(TenantQuery.class, IdentityService.class);
   }

}
