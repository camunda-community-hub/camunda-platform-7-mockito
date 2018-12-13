package org.camunda.bpm.extension.mockito.process;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessDefinitionFakeTest {

  @Test
  public void testEmptyBuilder() {
    ProcessDefinitionFake fake = ProcessDefinitionFake.builder().build();
    assertThat(fake.isSuspended()).isFalse();
    assertThat(fake.hasStartFormKey()).isFalse();
    assertThat(fake.getHistoryTimeToLive()).isEqualTo(0);
    assertThat(fake.getVersion()).isEqualTo(0);
  }

  @Test
  public void testStateChange() {
    ProcessDefinitionFake fake = ProcessDefinitionFake.builder().build();
    assertThat(fake.isSuspended()).isFalse();
    fake.setSuspended(true);
    assertThat(fake.isSuspended()).isTrue();
  }


  @Test
  public void testBuilder() {
    ProcessDefinitionFake fake = ProcessDefinitionFake
      .builder()
      .category("cat")
      .deploymentId("deploymentId")
      .description("description")
      .diagramResourceName("diagramResourceName")
      .hasStartForm(true)
      .historyTimeToLive(17)
      .id("id")
      .key("key")
      .name("name")
      .resourceName("resourceName")
      .suspended(true)
      .tenantId("tenantId")
      .versionTag("versionTag")
      .version(16)
      .build();

    assertThat(fake.getCategory()).isEqualTo("cat");
    assertThat(fake.getDeploymentId()).isEqualTo("deploymentId");
    assertThat(fake.getDescription()).isEqualTo("description");
    assertThat(fake.getDiagramResourceName()).isEqualTo("diagramResourceName");
    assertThat(fake.getHistoryTimeToLive()).isEqualTo(17);
    assertThat(fake.hasStartFormKey()).isTrue();
    assertThat(fake.getId()).isEqualTo("id");
    assertThat(fake.getName()).isEqualTo("name");
    assertThat(fake.getResourceName()).isEqualTo("resourceName");
    assertThat(fake.isSuspended()).isTrue();
    assertThat(fake.getTenantId()).isEqualTo("tenantId");
    assertThat(fake.getVersionTag()).isEqualTo("versionTag");
    assertThat(fake.getVersion()).isEqualTo(16);
  }

}
