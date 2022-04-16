package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.CaseInstanceQuery;

public class CaseInstanceQueryMock extends AbstractQueryMock<CaseInstanceQueryMock, CaseInstanceQuery, CaseInstance, CaseService> {

  public CaseInstanceQueryMock() {
    super(CaseInstanceQuery.class, CaseService.class);
   }

}
