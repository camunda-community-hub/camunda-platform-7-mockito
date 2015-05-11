package org.camunda.bpm.extension.mockito.function;


import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.extension.mockito.function.NameForType.juelNameFor;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Named;

public class NameForExpressionTypeTest {

  public static class PojoBean {
  }

  @Named
  public static class NamedBean {
  }

  @Named("bar")
  public static class BarNamedBean {
  }

  @Component
  public static class ComponentBean {
  }

  @Component("bar")
  public static class BarComponentBean {
  }

  @Service
  public static class ServiceBean {
  }

  @Service("bar")
  public static class BarServiceBean {
  }


  @Test
  public void resolves_pojo() {
    assertThat(juelNameFor(PojoBean.class)).isEqualTo("pojoBean");
  }

  @Test
  public void resolves_named_default() {
    assertThat(juelNameFor(NamedBean.class)).isEqualTo("namedBean");
  }

  @Test
  public void resolves_named_bar() {
    assertThat(juelNameFor(BarNamedBean.class)).isEqualTo("bar");
  }

  @Test
  public void resolves_component() {
    assertThat(juelNameFor(ComponentBean.class)).isEqualTo("componentBean");
  }

  @Test
  public void resolves_named_component() {
    assertThat(juelNameFor(BarComponentBean.class)).isEqualTo("bar");
  }

  @Test
  public void resolves_service() {
    assertThat(juelNameFor(ServiceBean.class)).isEqualTo("serviceBean");
  }

  @Test
  public void resolves_named_service() {
    assertThat(juelNameFor(BarServiceBean.class)).isEqualTo("bar");
  }

  @Test
      public void gets_value_from_component() {
          assertThat(NameForType.GET_VALUE.apply(BarComponentBean.class.getAnnotation(Component.class))).isEqualTo("bar");
          assertThat(NameForType.GET_VALUE.apply(ComponentBean.class.getAnnotation(Component.class))).isEqualTo("");
      }
  @Test
      public void gets_value_from_service() {
          assertThat(NameForType.GET_VALUE.apply(BarServiceBean.class.getAnnotation(Service.class))).isEqualTo("bar");
          assertThat(NameForType.GET_VALUE.apply(ServiceBean.class.getAnnotation(Service.class))).isEqualTo("");
      }
}
