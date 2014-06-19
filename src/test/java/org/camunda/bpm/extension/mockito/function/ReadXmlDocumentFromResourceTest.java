package org.camunda.bpm.extension.mockito.function;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.google.common.io.Resources;

public class ReadXmlDocumentFromResourceTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Test
  public void returns_document_for_resource() throws TransformerException {
    Document document = ReadXmlDocumentFromResource.INSTANCE.apply(Resources.getResource("MockProcess.bpmn"));
    assertThat(document).isNotNull();
   }

}
