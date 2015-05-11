package org.camunda.bpm.extension.mockito.function;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.google.common.io.Resources;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.StringWriter;

public class ReadXmlDocumentFromResourceTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final ReadXmlDocumentFromResource fromResource = new ReadXmlDocumentFromResource();

  @Test
  public void returns_document_for_resource() throws TransformerException {
    Document document = fromResource.apply(Resources.getResource("MockProcess.bpmn"));
    assertThat(document).isNotNull();
    String content = ReadXmlDocumentFromResource.TO_STRING.apply(document);
    assertThat(content).contains("#{loadData}");
  }

  @Test
  public void parse_xml() {
    Document document = fromResource.apply(Resources.getResource("MockProcess.bpmn"));

    Element root = document.getDocumentElement();
    final NodeList tasks = root.getElementsByTagNameNS("*", "serviceTask");

    assertThat(tasks.getLength()).isEqualTo(2);

    logger.info(tasks.toString());
    logger.info(tasks.item(1).toString());

  }


}
