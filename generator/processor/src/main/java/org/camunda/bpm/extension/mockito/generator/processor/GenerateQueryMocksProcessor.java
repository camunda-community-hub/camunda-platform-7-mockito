package org.camunda.bpm.extension.mockito.generator.processor;

import com.google.auto.service.AutoService;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.io.Files;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.camunda.bpm.extension.mockito.generator.annotation.GenerateQueryMocks;
import org.camunda.bpm.extension.mockito.generator.processor.template.ResourceLoader;

import javax.annotation.Nullable;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes({GenerateQueryMocks.FQN})
//@AutoService(Process.class)
public class GenerateQueryMocksProcessor extends AbstractProcessor {

  public static final String TEMPLATE = ResourceLoader.load("/QueryMockTemplate.txt");
  public static final String[] QUERY_MOCKS = ResourceLoader.load("/QueryMocksTemplate.txt").split("###");

  private boolean processed = false;

  @Override
  public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
    if (processed) {
      info("already processed");
      return true;
    }
    if (!getTypeElement(roundEnv)) {
      return true;
    }

    Set<QueryData> queryDatas = QueryDataSupplier.INSTANCE.get();

    StringBuilder queryMocks = new StringBuilder(QUERY_MOCKS[0]);

    for (QueryData data : queryDatas) {
      info("Generating %s", data);

      write(QueryData.PACKAGE, data.getClassname(), data.generateClass());
      queryMocks.append(new StrSubstitutor(data.getMap()).replace(QUERY_MOCKS[1]));
    }
    queryMocks.append(QUERY_MOCKS[2]);

    info(queryMocks.toString());

    write("org.camunda.bpm.extension.mockito", "QueryMocks", queryMocks.toString());

    processed = true;
    return true;
  }



  private void write(String packageName, String className, String code) {
    Writer writer = null;
    try {
      JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(packageName + "." + className);
      writer = fileObject.openWriter();
      writer.append(code);
    } catch (IOException e) {
      info("could not write %s %s %s", className, code, e.getMessage());
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          //
        }
      }
    }
  }

  private boolean getTypeElement(RoundEnvironment roundEnv) {
    TypeElement result = null;
    for (Element e : roundEnv.getElementsAnnotatedWith(GenerateQueryMocks.class)) {
      if (e.getKind() == ElementKind.CLASS && e instanceof TypeElement) {
        if (result != null) {
          error("exactly one class must be annotated with GenerateQueryMocks.");
          return false;
        }
        result = (TypeElement) e;
      }
    }
    return result != null;
  }

  protected void error(String pattern, Object... args) {
    printMessage(Diagnostic.Kind.ERROR, String.format(pattern, args));
  }

  protected void info(String pattern, Object... args) {
    printMessage(Diagnostic.Kind.NOTE, String.format(pattern, args));
  }

  protected void printMessage(Diagnostic.Kind kind, String pattern, Object... args) {
    processingEnv.getMessager().printMessage(kind, String.format(pattern, args));
  }


}
