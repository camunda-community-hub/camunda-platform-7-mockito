package org.camunda.bpm.extension.test.mockito.function;

import com.google.common.base.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

import static com.google.common.base.Throwables.propagate;

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
