package org.camunda.bpm.extension.mockito.generator.processor;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.extension.mockito.generator.annotation.GenerateQueryMocks;
import org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor;
import org.junit.Test;

import javax.tools.ForwardingJavaFileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

import java.util.Iterator;
import java.util.List;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static javax.tools.StandardLocation.SOURCE_OUTPUT;

public class GenerateQueryMocksProcessorTest {

  @Test
  public void generates_all_querymocks() throws Exception {
    List<String> expected = Resources.readLines(Resources.getResource("750_queries.txt"), Charsets.UTF_8);

    CompileTester.GeneratedPredicateClause<CompileTester.SuccessfulCompilationClause> predicateClause = assert_().about(javaSource())
      .that(JavaFileObjects.forSourceLines("AnnotatedClass", "package annotated;\n" +
        "import " + GenerateQueryMocks.FQN + ";\n" +
        "@GenerateQueryMocks\n" +
        "public class AnnotatedClass {}\n"))
      .processedWith(new GenerateQueryMocksProcessor())
      .compilesWithoutError()
      .and();

    Iterator<String> it = expected.iterator();



    while (it.hasNext()) {
      String name = it.next();
      if (StringUtils.isBlank(name) || name.startsWith("#")) {
        continue;
      }
      name = name.trim().replace(".java", "Mock.java");
      if (it.hasNext()) {
        predicateClause.generatesFileNamed(SOURCE_OUTPUT, QueryData.PACKAGE, name).and();
      } else {
        predicateClause.generatesFileNamed(SOURCE_OUTPUT, QueryData.PACKAGE, name);
      }
    }
  }

  @Test
  public void fails_with_duplicate_annotation() throws Exception {
    assert_().about(javaSource())
      .that(JavaFileObjects.forSourceLines("AnnotatedClass", "package annotated;\n" +
        "import " + GenerateQueryMocks.FQN + ";\n" +
        "@GenerateQueryMocks\n" +
        "public class AnnotatedClass {\n" +
        "} @GenerateQueryMocks class B{}"))
      .processedWith(new GenerateQueryMocksProcessor())
      .failsToCompile();
  }
}

