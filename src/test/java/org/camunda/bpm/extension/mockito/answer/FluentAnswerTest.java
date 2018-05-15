package org.camunda.bpm.extension.mockito.answer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FluentAnswerTest {

  @Mock
  private InvocationOnMock invocationOnMock;

  @Rule
  public ExpectedException expected = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void shouldReturnFluentAnserOrNull() {

    String string = "Bar";
    FluentBuilder superType = new FluentBuilder("superType");
    FluentBuilder superTypeInterface = new FluentBuilder("superInterface");

    // given
    FluentBuilder mock = FluentAnswer.createMock(FluentBuilder.class);

    // the fluent mock will not be returned on super type / interface or a different type
    // stub three methods
    when(mock.string()).thenReturn(string);
    when(mock.superType()).thenReturn(superType);
    when(mock.superTypeInterface()).thenReturn(superTypeInterface);


    // when -> then
    assertThat(mock.typeMatch()).isEqualTo(mock);
    assertThat(mock.typeMatch("")).isEqualTo(mock);
    assertThat(mock.string()).isEqualTo(string);
    assertThat(mock.superType()).isEqualTo(superType);
    assertThat(mock.superTypeInterface()).isEqualTo(superTypeInterface);
    // not stubbed method -> the result is not from the answer, but is null
    assertThat(mock.notStubbed()).isNull();
  }

  @Test
  public void shouldThrowClassCastExceptionUsingSubtypes() {

    expected.expect(ClassCastException.class);

    FluentBuilder mock = FluentAnswer.createMock(FluentBuilder.class);
    when(mock.subType()).thenReturn(new FluentBuilderExtension());
  }

  interface SomeAspect {
  }

  static class FluentBuilder implements SomeAspect {
    private final String value;

    public FluentBuilder(String value) {
      this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      final FluentBuilder that = (FluentBuilder) o;
      return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }

    // object is a supertype of all objects
    Object superType() {
      return this;
    }

    // supertype
    SomeAspect superTypeInterface() {
      return this;
    }


    // full match
    FluentBuilder typeMatch() {
      return this;
    }

    // full match
    FluentBuilder typeMatch(String value) {
      return this;
    }

    // subclass of current
    FluentBuilderExtension subType() {
      return new FluentBuilderExtension();
    }

    String notStubbed() {
      return "Zee";
    }


    // not in type hierarchy
    String string() {
      return "Hello";
    }

  }

  static class FluentBuilderExtension extends FluentBuilder {
    FluentBuilderExtension() {
      super("Extension");
    }
  }
}
