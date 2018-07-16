package org.camunda.bpm.extension.mockito.function;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

import javax.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * Retrieves the juel expression for given type. First guess is the value of a @Named
 * annotation if present, otherwise, the uncapitalized SimpleName of the type is
 * returned.
 */
public enum NameForType implements Function<Class<?>, String> {
  INSTANCE;

  static final String ENHANCER = "$MockitoMock$";

  /**
   * Does instance.getClass() but is mock-aware.
   *
   * @param instance the instance to get the class of
   * @return class name
   */
  static Class<? extends Object> typeOf(Object instance) {
    Class<? extends Object> type = instance.getClass();
    while (type.getSimpleName().contains(ENHANCER)) {
      type = type.getSuperclass();
    }

    return type;
  }

  public static String juelNameFor(final Object instance) {
    if (instance == null) {
      throw new IllegalArgumentException("instance must not be null");
    }
    return juelNameFor(typeOf(instance));
  }

  public static String juelNameFor(final Class<?> type) {
    return INSTANCE.apply(type);
  }

  static Function<Annotation,String> GET_VALUE = new Function<Annotation, String>() {
    @Nullable
    @Override
    public String apply(final Annotation annotation) {
      try {
        return (String) annotation.annotationType().getMethod("value").invoke(annotation);
      } catch (Exception e) {
        return "";
      }
    }
  };

  static Predicate<Annotation> IS_SUPPORTED = new Predicate<Annotation>() {
    private final Set<String> NAME_ANNOTATIONS = new HashSet<>(
      Arrays.asList(
        "javax.inject.Named",
        "org.springframework.stereotype.Component",
        "org.springframework.stereotype.Service")
    );

    @Override
    public boolean test(@Nullable Annotation a) {
      return NAME_ANNOTATIONS.contains(a.annotationType().getName());
    }
  };

  @Override
  public String apply(final Class<?> type) {
    if (type == null) {
      throw new IllegalArgumentException("type must not be null!");
    }
    final Optional<String> value = Stream.of(type.getAnnotations()).filter(IS_SUPPORTED).findFirst().map(GET_VALUE);

    return value.isPresent() && isNotBlank(value.get()) ? value.get() : uncapitalize(type.getSimpleName());
  }


}
