package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.CaseInstanceQuery;
import javax.annotation.Generated;

@Generated(value="org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor", date="Tue Nov 08 16:00:04 CET 2016")
public class CaseInstanceQueryMock extends AbstractQueryMock<CaseInstanceQueryMock, CaseInstanceQuery, CaseInstance, CaseService> {

  public CaseInstanceQueryMock() {
    super(CaseInstanceQuery.class, CaseService.class);
   }

}
