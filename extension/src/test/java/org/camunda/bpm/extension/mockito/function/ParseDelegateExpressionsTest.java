package org.camunda.bpm.extension.mockito.function;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class ParseDelegateExpressionsTest {

  public static final URL BPMN_FILE = getResource("MockProcess.bpmn");
  public static final URL BPMN_FILE_NONS = getResource("MockProcess_withoutNS.bpmn");

  private final ParseDelegateExpressions function = new ParseDelegateExpressions();

  @Test
  public void gets_pairs_for_mockProcess() {
    final List<Pair<ParseDelegateExpressions.ExpressionType, String>> pairs = function.apply(BPMN_FILE);

    assertThat(pairs).isNotEmpty();
  }


}
