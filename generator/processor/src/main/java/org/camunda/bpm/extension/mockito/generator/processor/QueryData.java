package org.camunda.bpm.extension.mockito.generator.processor;


import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.query.Query;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;

import javax.annotation.Nullable;
import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class QueryData {

  public static final String PACKAGE = "org.camunda.bpm.extension.mockito.query";




  public String getClassname() {
    return getQueryType().getSimpleName() + "Mock";
  }

  public Map<String, String> getMap() {
    return ImmutableMap.<String, String>builder()
      .put("package", PACKAGE)
      .put("serviceFqn", getServiceType().getCanonicalName())
      .put("resultFqn", getResultType().getCanonicalName())
      .put("queryFqn", getQueryType().getCanonicalName())
      .put("className", getClassname())
      .put("queryName", getQueryType().getSimpleName())
      .put("resultName", getResultType().getSimpleName())
      .put("serviceName", getServiceType().getSimpleName())
      .put("generator", GenerateQueryMocksProcessor.class.getCanonicalName())
      .put("date", new Date().toString())
      .build();
  }

  private final Class<?> serviceType;
  private final Class<?> queryType;
  private final Class<?> resultType;

  public QueryData(Class<?> serviceType, Class<?> queryType, Class<?> resultType) {
    this.serviceType = serviceType;
    this.queryType = queryType;
    this.resultType = resultType;
  }

  public Class<?> getServiceType() {
    return serviceType;
  }

  public Class<?> getQueryType() {
    return queryType;
  }

  public Class<?> getResultType() {
    return resultType;
  }

  @Override
  public String toString() {
    return "QueryData{" +
      "serviceType=" + (serviceType != null ? serviceType.getSimpleName() : null) +
      ", queryType=" + (queryType != null ? queryType.getSimpleName() : null) +
      ", resultType=" + (resultType != null ? resultType.getSimpleName() : null) +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QueryData queryData = (QueryData) o;
    return Objects.equal(serviceType, queryData.serviceType) &&
      Objects.equal(queryType, queryData.queryType) &&
      Objects.equal(resultType, queryData.resultType);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(serviceType, queryType, resultType);
  }

  public String generateClass() {
    return new StrSubstitutor(getMap()).replace(GenerateQueryMocksProcessor.TEMPLATE);
  }
}
