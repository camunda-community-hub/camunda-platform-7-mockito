package org.camunda.community.mockito.query;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.EventSubscriptionQuery;

public class EventSubscriptionQueryMock extends AbstractQueryMock<EventSubscriptionQueryMock, EventSubscriptionQuery, EventSubscription, RuntimeService> {

  public EventSubscriptionQueryMock() {
    super(EventSubscriptionQuery.class, RuntimeService.class);
   }

}
