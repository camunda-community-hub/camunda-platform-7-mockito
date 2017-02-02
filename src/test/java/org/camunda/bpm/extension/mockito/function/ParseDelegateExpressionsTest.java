package org.camunda.bpm.extension.mockito.function;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class ParseDelegateExpressionsTest {


  private final ParseDelegateExpressions function = new ParseDelegateExpressions();

  @Test
  public void gets_pairs_for_mockProcess() {
    final URL bpmnFile = this.getClass().getResource("/MockProcess.bpmn");
    final List<Pair<ParseDelegateExpressions.ExpressionType, String>> pairs = function.apply(bpmnFile);

    assertThat(pairs).isNotEmpty();
  }


}
