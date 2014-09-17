package org.camunda.bpm.extension.mockito.function;

import static com.google.common.base.Throwables.propagate;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.base.Function;

/**
 * Return DOM document for given resource.
 *
 * @author Jan Galinski, Holisticon AG
 */
public enum ReadXmlDocumentFromResource implements Function<URL, Document> {
  INSTANCE;

  private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

  @Override
  public Document apply(URL xmlResource) {
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
