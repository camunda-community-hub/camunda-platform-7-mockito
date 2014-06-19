package org.camunda.bpm.extension.mockito.function;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.extension.mockito.function.ParseDelegateExpressions.EXECUTION_LISTENER;
import static org.camunda.bpm.extension.mockito.function.ParseDelegateExpressions.JAVA_DELEGATE;
import static org.camunda.bpm.extension.mockito.function.ParseDelegateExpressions.TASK_LISTENER;

import org.junit.Test;

public class ParseDelegateExpressionsTest {

  public static final String BPMN_FILE = "MockProcess.bpmn";


  @Test
  public void never_returns_null() {
    assertThat(JAVA_DELEGATE.apply(getResource(BPMN_FILE))).isNotNull();
    assertThat(EXECUTION_LISTENER.apply(getResource(BPMN_FILE))).isNotNull();
    assertThat(TASK_LISTENER.apply(getResource(BPMN_FILE))).isNotNull();
  }

  @Test
  public void parse_bpmn_file_and_extract_expressions() {
    assertThat(EXECUTION_LISTENER.apply(getResource(BPMN_FILE))).containsOnly("startProcess", "beforeLoadData");
    assertThat(TASK_LISTENER.apply(getResource(BPMN_FILE))).containsExactly("verifyData");
    assertThat(JAVA_DELEGATE.apply(getResource(BPMN_FILE))).containsExactly("loadData");
  }

  @Test
  public void reads_juel_expression_and_extracts_name() {
    assertThat(ParseDelegateExpressions.extractDelegateExpressionName("#{hello}")).isEqualTo("hello");
    assertThat(ParseDelegateExpressions.extractDelegateExpressionName("${hello}")).isEqualTo("hello");
  }

}
