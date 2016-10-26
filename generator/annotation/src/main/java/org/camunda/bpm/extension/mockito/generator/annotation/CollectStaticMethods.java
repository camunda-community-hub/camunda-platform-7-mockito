package org.camunda.bpm.extension.mockito.generator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Target( TYPE )
@Retention( CLASS )
public @interface CollectStaticMethods {

  String FQN = "org.camunda.bpm.extension.mockito.generator.annotation.GenerateQueryMocks";
}
