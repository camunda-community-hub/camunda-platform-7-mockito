package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.repository.ProcessDefinition;

public class ProcessDefinitionFake implements ProcessDefinition {

  private final String id;
  private final String category;
  private final String name;
  private final String key;
  private final int version;
  private final String resourceName;
  private final String deploymentId;
  private final String diagramResourceName;
  private final String tenantId;
  private final String description;
  private final boolean hasStartForm;
  private final int historyTimeToLive;
  private final String versionTag;
  private final boolean startableInTasklist;

  private boolean suspended;

  public static ProcessDefinitionFakeBuilder builder() {
    return new ProcessDefinitionFakeBuilder();
  }

  ProcessDefinitionFake(String id, String category, String name, String key, int version, String resourceName, String deploymentId,
                        String diagramResourceName, String tenantId, String description, boolean hasStartForm, boolean suspended,
                        int historyTimeToLive, String versionTag, boolean startableInTasklist) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.key = key;
    this.version = version;
    this.resourceName = resourceName;
    this.deploymentId = deploymentId;
    this.diagramResourceName = diagramResourceName;
    this.tenantId = tenantId;
    this.description = description;
    this.hasStartForm = hasStartForm;
    this.suspended = suspended;
    this.historyTimeToLive = historyTimeToLive;
    this.versionTag = versionTag;
    this.startableInTasklist = startableInTasklist;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public int getVersion() {
    return version;
  }

  @Override
  public String getResourceName() {
    return resourceName;
  }

  @Override
  public String getDeploymentId() {
    return deploymentId;
  }

  @Override
  public String getDiagramResourceName() {
    return diagramResourceName;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }

  @Override
  public Integer getHistoryTimeToLive() {
    return historyTimeToLive;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public boolean hasStartFormKey() {
    return hasStartForm;
  }

  @Override
  public boolean isSuspended() {
    return suspended;
  }

  @Override
  public String getVersionTag() {
    return versionTag;
  }

  @Override
  public boolean isStartableInTasklist() {
    return startableInTasklist;
  }

  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }

  @Override
  public String toString() {
    return "ProcessDefinitionFake{" +
      "id='" + id + '\'' +
      ", category='" + category + '\'' +
      ", name='" + name + '\'' +
      ", key='" + key + '\'' +
      ", version=" + version +
      ", resourceName='" + resourceName + '\'' +
      ", deploymentId='" + deploymentId + '\'' +
      ", diagramResourceName='" + diagramResourceName + '\'' +
      ", tenantId='" + tenantId + '\'' +
      ", description='" + description + '\'' +
      ", hasStartForm=" + hasStartForm +
      ", historyTimeToLive=" + historyTimeToLive +
      ", versionTag='" + versionTag + '\'' +
      ", suspended=" + suspended +
      ", startableInTasklist=" + startableInTasklist +
      '}';
  }

  public static class ProcessDefinitionFakeBuilder {
    private String id;
    private String category;
    private String name;
    private String key;
    private int version = 0;
    private String resourceName;
    private String deploymentId;
    private String diagramResourceName;
    private String tenantId;
    private String description;
    private boolean hasStartForm = false;
    private boolean suspended = false;
    private int historyTimeToLive = 0;
    private String versionTag;
    private boolean startableInTasklist;

    public ProcessDefinitionFakeBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ProcessDefinitionFakeBuilder category(String category) {
      this.category = category;
      return this;
    }

    public ProcessDefinitionFakeBuilder name(String name) {
      this.name = name;
      return this;
    }

    public ProcessDefinitionFakeBuilder key(String key) {
      this.key = key;
      return this;
    }

    public ProcessDefinitionFakeBuilder version(int version) {
      this.version = version;
      return this;
    }

    public ProcessDefinitionFakeBuilder resourceName(String resourceName) {
      this.resourceName = resourceName;
      return this;
    }

    public ProcessDefinitionFakeBuilder deploymentId(String deploymentId) {
      this.deploymentId = deploymentId;
      return this;
    }

    public ProcessDefinitionFakeBuilder diagramResourceName(String diagramResourceName) {
      this.diagramResourceName = diagramResourceName;
      return this;
    }

    public ProcessDefinitionFakeBuilder tenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    public ProcessDefinitionFakeBuilder description(String description) {
      this.description = description;
      return this;
    }

    public ProcessDefinitionFakeBuilder hasStartForm(boolean hasStartForm) {
      this.hasStartForm = hasStartForm;
      return this;
    }

    public ProcessDefinitionFakeBuilder suspended(boolean suspended) {
      this.suspended = suspended;
      return this;
    }

    public ProcessDefinitionFakeBuilder historyTimeToLive(int historyTimeToLive) {
      this.historyTimeToLive = historyTimeToLive;
      return this;
    }

    public ProcessDefinitionFakeBuilder versionTag(String versionTag) {
      this.versionTag = versionTag;
      return this;
    }

    @Override
    public String toString() {
      return "ProcessDefinitionFakeBuilder{" +
        "id='" + id + '\'' +
        ", category='" + category + '\'' +
        ", name='" + name + '\'' +
        ", key='" + key + '\'' +
        ", version=" + version +
        ", resourceName='" + resourceName + '\'' +
        ", deploymentId='" + deploymentId + '\'' +
        ", diagramResourceName='" + diagramResourceName + '\'' +
        ", tenantId='" + tenantId + '\'' +
        ", description='" + description + '\'' +
        ", hasStartForm=" + hasStartForm +
        ", suspended=" + suspended +
        ", historyTimeToLive=" + historyTimeToLive +
        ", versionTag='" + versionTag + '\'' +
        '}';
    }

    public ProcessDefinitionFake build() {
      return new ProcessDefinitionFake(
        id, category, name, key, version, resourceName,
        deploymentId, diagramResourceName, tenantId,
        description, hasStartForm, suspended, historyTimeToLive,
        versionTag,
        startableInTasklist);
    }
  }
}
