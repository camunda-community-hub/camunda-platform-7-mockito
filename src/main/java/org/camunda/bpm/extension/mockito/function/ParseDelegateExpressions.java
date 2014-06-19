package org.camunda.bpm.extension.mockito.function;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.camunda.bpm.model.bpmn.impl.BpmnModelConstants.CAMUNDA_ATTRIBUTE_DELEGATE_EXPRESSION;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.collect.Sets;

/**
 * Parses a given BPMN File and returns a Set of all  delegateExpression names.
 *
 * @author Jan Galinski, Holisticon AG
 */
public enum ParseDelegateExpressions implements Function<URL, Set<String>> {

  EXECUTION_LISTENER,
  TASK_LISTENER,
  JAVA_DELEGATE;

  /**
   * Caches the results for each BPMN resource, so the document has to be parsed only once.
   */
  private static final Map<URL, Map<ParseDelegateExpressions, Set<String>>> cache = new HashMap<URL, Map<ParseDelegateExpressions, Set<String>>>();

  /**
   * Regex to extract the delegateExpression name (#{hello} -> hello).
   */
  @VisibleForTesting
  static final String PATTERN_DELEGATE_EXPRESSION = "[#$]\\{([^}]+)}";

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final ReadXmlDocumentFromResource readXmlDocumentFromResource = ReadXmlDocumentFromResource.INSTANCE;


  @Override
  public Set<String> apply(URL bpmnResource) {
    if (!cache.containsKey(bpmnResource)) {
      final Map<ParseDelegateExpressions, Set<String>> result = initEmptyMap(bpmnResource);

      final Element root = readXmlDocumentFromResource.apply(bpmnResource).getDocumentElement();
      result.get(EXECUTION_LISTENER).addAll(parseExpressions(root.getElementsByTagName("camunda:executionListener")));
      result.get(TASK_LISTENER).addAll(parseExpressions(root.getElementsByTagName("camunda:taskListener")));
      result.get(JAVA_DELEGATE).addAll(parseExpressions(root.getElementsByTagName("bpmn2:serviceTask")));
    }
    return cache.get(bpmnResource).get(this);
  }


  private static Map<ParseDelegateExpressions, Set<String>> initEmptyMap(final URL bpmnResource) {
    final Map<ParseDelegateExpressions, Set<String>> map = new HashMap<ParseDelegateExpressions, Set<String>>() {{
      put(EXECUTION_LISTENER, new HashSet<String>());
      put(JAVA_DELEGATE, new HashSet<String>());
      put(TASK_LISTENER, new HashSet<String>());
    }};

    cache.put(bpmnResource, map);

    return map;
  }

  private Set<String> parseExpressions(NodeList nodeList) {
    final Set<String> expressions = Sets.newHashSet();
    for (int i = 0; i < nodeList.getLength(); i++) {
      final NamedNodeMap attributes = nodeList.item(i).getAttributes();

      Node delegateExpression = attributes.getNamedItem(CAMUNDA_ATTRIBUTE_DELEGATE_EXPRESSION);
      if (delegateExpression == null) {
       delegateExpression =  attributes.getNamedItem("camunda:"+CAMUNDA_ATTRIBUTE_DELEGATE_EXPRESSION);
      }

      if (delegateExpression != null) {
        expressions.add(extractDelegateExpressionName(delegateExpression.getTextContent()));
      }

    }
    return expressions;
  }


  public static String extractDelegateExpressionName(String delegateExpression) {
    return isNotBlank(delegateExpression) ? delegateExpression.replaceAll(PATTERN_DELEGATE_EXPRESSION,"$1") : null;
  }
}
