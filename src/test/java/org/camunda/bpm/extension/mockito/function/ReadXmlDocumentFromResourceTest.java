package org.camunda.bpm.extension.mockito.function;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.transform.TransformerException;

import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class ReadXmlDocumentFromResourceTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final ReadXmlDocumentFromResource fromResource = new ReadXmlDocumentFromResource();

  @Test
  public void returns_document_for_resource() throws TransformerException {
    Document document = fromResource.apply(getResource("/MockProcess.bpmn"));
    assertThat(document).isNotNull();
    String content = ReadXmlDocumentFromResource.TO_STRING.apply(document);
    assertThat(content).contains("#{loadData}");
  }

  @Test
  public void parse_xml() {
    Document document = fromResource.apply(getResource("/MockProcess.bpmn"));

    Element root = document.getDocumentElement();
    final NodeList tasks = root.getElementsByTagNameNS("*", "serviceTask");

    assertThat(tasks.getLength()).isEqualTo(2);

    logger.info(tasks.toString());
    logger.info(tasks.item(1).toString());

  }

  private URL getResource(String name) {
    return this.getClass().getResource(name);
  }

}
