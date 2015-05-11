package org.camunda.bpm.extension.mockito.function;

import static com.google.common.base.Throwables.propagate;

import com.google.common.base.Function;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.annotation.Nonnull;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

/**
 * Return DOM document for given resource.
 *
 * @author Jan Galinski, Holisticon AG
 */
public class ReadXmlDocumentFromResource implements Function<URL, Document> {

  public static Function<Document, String> TO_STRING = new Function<Document, String>() {
    @Nonnull
    @Override
    public String apply(final Document document) {
      try {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        final StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        return writer.getBuffer().toString();
      } catch (javax.xml.transform.TransformerException e) {
        throw propagate(e);
      }

    }
  };

  private final DocumentBuilderFactory factory;

  public ReadXmlDocumentFromResource() {
    this.factory =  DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
  }

  @Override
  public Document apply(final URL xmlResource) {
    try {
      final Document document = factory.newDocumentBuilder().parse(xmlResource.openStream());
      document.getDocumentElement().normalize();

      return document;
    } catch (SAXException e) {
      throw propagate(e);
    } catch (IOException e) {
      throw propagate(e);
    } catch (ParserConfigurationException e) {
      throw propagate(e);
    }

  }
}

