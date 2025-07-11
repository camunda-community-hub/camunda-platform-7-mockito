package org.camunda.community.mockito.function;

import org.apache.commons.lang3.tuple.Pair;
import org.camunda.community.mockito.DelegateExpressions;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.camunda.bpm.model.bpmn.impl.BpmnModelConstants.*;

/**
 * Parses a given BPMN File and returns a Set of all delegateExpression names.
 *
 * @author Jan Galinski, Holisticon AG
 */
public class ParseDelegateExpressions implements Function<URL, List<Pair<ParseDelegateExpressions.ExpressionType, String>>> {

  public enum ExpressionType {
    EXECUTION_LISTENER(CAMUNDA_ELEMENT_EXECUTION_LISTENER) {
      @Override
      public void registerMock(final String name) {
        DelegateExpressions.registerExecutionListenerMock(name);
      }
    }, TASK_LISTENER(CAMUNDA_ELEMENT_TASK_LISTENER) {
      @Override
      public void registerMock(final String name) {
        DelegateExpressions.registerTaskListenerMock(name);
      }
    }, JAVA_DELEGATE(Set.of(BPMN_ELEMENT_SERVICE_TASK, BPMN_ELEMENT_MESSAGE_EVENT_DEFINITION)) {
      @Override
      public void registerMock(final String name) {
        DelegateExpressions.registerJavaDelegateMock(name);
      }
    };

    private final Set<String> elements;

    ExpressionType(final String element) {
      this.elements = Set.of(element);
    }

    ExpressionType(final Set<String> elements) {
      this.elements = elements;
    }

    public abstract void registerMock(final String name);
  }

  /**
   * Regex to extract the delegateExpression name (#{hello} -> hello).
   */
  static final String PATTERN_DELEGATE_EXPRESSION = "[#$]\\{([^}]+)}";

  public static String extractDelegateExpressionName(final String delegateExpression) {
    return isNotBlank(delegateExpression) ? delegateExpression.replaceAll(PATTERN_DELEGATE_EXPRESSION, "$1") : null;
  }

  static Pair<ExpressionType, String> extractDelegateExpressionName(final Pair<ExpressionType, Node> pair) {
    final String delegateExpression = pair.getRight().getTextContent();
    return Pair.of(
      pair.getLeft(),
      isNotBlank(delegateExpression) ? delegateExpression.replaceAll(PATTERN_DELEGATE_EXPRESSION, "$1") : null
    );
  }

  @Override
  public List<Pair<ExpressionType, String>> apply(final URL bpmnResource) {
    final Element root = new ReadXmlDocumentFromResource().apply(bpmnResource).getDocumentElement();

    final List<Pair<ExpressionType, NodeList>> nodeListsPerType = Stream.of(ExpressionType.values())
      .flatMap(type -> type.elements.stream().map(it -> root.getElementsByTagNameNS("*", it))
        .map(it -> Pair.of(type, it)))
      .toList();

    return nodeListsPerType.stream()
      .flatMap(typeToNodeList -> streamOf(typeToNodeList.getRight()).map(node -> {
        final NamedNodeMap attributes = node.getAttributes();
        // TODO: this is not nice, but I cannot get getNamedItemNS("*", ...) to work properly
        Node delegateExpression = Optional
          .ofNullable(attributes.getNamedItem(CAMUNDA_ATTRIBUTE_DELEGATE_EXPRESSION))
          .orElse(attributes.getNamedItem("camunda:" + CAMUNDA_ATTRIBUTE_DELEGATE_EXPRESSION));
        return Pair.of(typeToNodeList.getLeft(), delegateExpression);
      })).filter(it -> it.getRight() != null)
      .map(ParseDelegateExpressions::extractDelegateExpressionName)
      .toList();
  }

  private static Stream<Node> streamOf(final NodeList nodeList) {
    final long length = nodeList.getLength();
    final Iterator<Node> iterator = new Iterator<>() {
      private final AtomicInteger index = new AtomicInteger(0);

      @Override
      public boolean hasNext() {
        return index.get() < length;
      }

      @Override
      public Node next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return nodeList.item(index.getAndIncrement());
      }
    };
    final Spliterator<Node> spliterator = Spliterators.spliterator(iterator, length, Spliterator.ORDERED);

    return StreamSupport.stream(spliterator, false);
  }
}
