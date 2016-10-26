package org.camunda.bpm.extension.mockito.generator.processor;


import com.google.common.base.Function;
import com.google.common.base.Supplier;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.query.Query;
import org.camunda.bpm.extension.mockito.generator.processor.util.ExtractResultType;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public enum QueryDataSupplier implements Supplier<Set<QueryData>>{
  INSTANCE;

  private final Set<QueryData> data;



  static Set<QueryData> forService(Class<?> service) {
    ExtractResultType extractResultType = new ExtractResultType();
    Set<QueryData> list = new LinkedHashSet<QueryData>();
    for (Method method : service.getDeclaredMethods()) {
      if (method.getName().startsWith("create") && method.getName().endsWith("Query")) {
        Class<?> query = method.getReturnType();
        if (Query.class.isAssignableFrom(query)) {
          Class<?> result = extractResultType.apply(query);
          if (result != null) {
            list.add(new QueryData(service, query, result));
          }
        }
      }
    }
    return list;
  }


  static Set<QueryData> init() {
    Set<QueryData> list = new LinkedHashSet<QueryData>();

    for (Method getService : ProcessEngineServices.class.getDeclaredMethods()) {
      Class<?> service = getService.getReturnType();
      list.addAll(forService(service));
    }

    return list;
  }

  QueryDataSupplier() {
    this.data = init();
  }

  @Override
  public Set<QueryData> get() {
    return data;
  }
}
