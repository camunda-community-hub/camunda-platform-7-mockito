package org.camunda.bpm.extension.mockito.generator.processor.template;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.camunda.bpm.extension.mockito.generator.processor.GenerateQueryMocksProcessor;

import java.io.IOException;
import java.net.URL;

public class ResourceLoader {

  public static String load(final String resourceName) {
    try {
      URL resource = Resources.getResource(GenerateQueryMocksProcessor.class, resourceName);
      return Resources.toString(resource, Charsets.UTF_8);
    } catch (IOException e) {
      System.err.println("could not load " + resourceName + ": " + e.getMessage());
      return "";
    }
  }

}
