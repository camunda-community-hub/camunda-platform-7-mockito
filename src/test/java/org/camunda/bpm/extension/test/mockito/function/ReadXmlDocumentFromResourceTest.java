package org.camunda.bpm.extension.test.mockito.function;

import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadXmlDocumentFromResourceTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Test
  public void returns_document_for_resource() throws TransformerException {
    Document document = ReadXmlDocumentFromResource.INSTANCE.apply(Resources.getResource("MockProcess.bpmn"));
    assertThat(document).isNotNull();
   }

}
