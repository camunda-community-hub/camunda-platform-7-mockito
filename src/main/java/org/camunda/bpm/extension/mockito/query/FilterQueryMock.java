package org.camunda.bpm.extension.mockito.query;

import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.filter.Filter;
import org.camunda.bpm.engine.filter.FilterQuery;

public class FilterQueryMock extends AbstractQueryMock<FilterQueryMock, FilterQuery, Filter, FilterService> {

  public FilterQueryMock() {
    super(FilterQuery.class, FilterService.class);
  }

}
