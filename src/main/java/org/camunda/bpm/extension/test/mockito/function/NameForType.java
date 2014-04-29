package org.camunda.bpm.extension.test.mockito.function;

import com.google.common.base.Function;

import javax.inject.Named;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

/**
 * Retrieves the juel expression for given type. First guess is the value of a @Named
 * annotation if present, otherwise, the uncapitalized SimpleName of the type is
 * returned.
 */
public enum NameForType implements Function<Class<?>, String> {
  INSTANCE;

  public static String juelNameFor(final Object instance) {
    checkArgument(instance != null, "instance must not be null");
    return juelNameFor(instance.getClass());
  }

  public static String juelNameFor(final Class<?> type) {
    return INSTANCE.apply(type);
  }

  @Override
  public String apply(final Class<?> type) {
    checkArgument(type != null, "type must not be null!");

    final Named annotation = type.getAnnotation(Named.class);

    return annotation != null && isNotBlank(annotation.value()) ? annotation.value() : uncapitalize(type.getSimpleName());
  }

}
