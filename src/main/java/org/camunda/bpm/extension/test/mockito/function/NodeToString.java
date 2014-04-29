package org.camunda.bpm.extension.test.mockito.function;

import com.google.common.base.Function;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static com.google.common.base.Throwables.propagate;

/**
 * Returns String representation of given DOM node.
 *
 * @author Jan Galinski, Holisticon AG
 */
class NodeToString implements Function<Node, String> {
  public static final NodeToString INSTANCE = new NodeToString();

  private final Transformer transformer;

  private NodeToString() {
    try {
      transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    } catch (TransformerConfigurationException e) {
      throw propagate(e);
    }
  }

  @Override
  public String apply(final Node document) {
    final StringWriter writer = new StringWriter();
    try {
      transformer.transform(new DOMSource(document), new StreamResult(writer));

      return writer.toString();
    } catch (TransformerException e) {
      throw propagate(e);
    }
  }
}
