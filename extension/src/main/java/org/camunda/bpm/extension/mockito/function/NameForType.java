package org.camunda.bpm.extension.mockito.function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Sets;

/**
 * Retrieves the juel expression for given type. First guess is the value of a @Named
 * annotation if present, otherwise, the uncapitalized SimpleName of the type is
 * returned.
 */
public enum NameForType implements Function<Class<?>, String> {
  INSTANCE;
  
  static final String ENHANCER = "$$EnhancerByMockitoWithCGLIB$$";
  
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
    checkArgument(instance != null, "instance must not be null");
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
    private final Set<String> NAME_ANNOTATIONS = Sets.newHashSet("javax.inject.Named", "org.springframework.stereotype.Component", "org.springframework.stereotype.Service");
    @Override
    public boolean apply(@Nullable Annotation a) {
      return NAME_ANNOTATIONS.contains(a.annotationType().getName());
    }
  };

  @Override
  public String apply(final Class<?> type) {
    checkArgument(type != null, "type must not be null!");
    final Optional<String> value = FluentIterable.from(asList(type.getAnnotations())).firstMatch(IS_SUPPORTED).transform(GET_VALUE);

    return value.isPresent() && isNotBlank(value.get()) ? value.get() : uncapitalize(type.getSimpleName());
  }


}
