package org.camunda.bpm.extension.mockito.generator.processor.util;


import com.google.common.base.Function;

import javax.annotation.Nullable;
import java.util.Arrays;

public class ExtractResultType implements Function<Class<?>, Class<?>> {

  static Class<?> getType(Class<?> query, String replacement) {
    Class<?> type = null;
    try {
      type = Class.forName(query.getCanonicalName().replaceAll("Query$", replacement));
    } catch (ClassNotFoundException e) {
      //
    }
    return type;
  }

  @Nullable
  @Override
  public Class<?> apply(@Nullable Class<?> query) {
    Class<?> type = null;
    for (String replacement : Arrays.asList("", "Entry", "Entity")) {
      type = getType(query, replacement);
      if (type != null) {
        break;
      }
    }

    return type;
  }

}
