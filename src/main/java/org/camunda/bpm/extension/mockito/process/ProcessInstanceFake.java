package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.runtime.ProcessInstance;

public class ProcessInstanceFake implements ProcessInstance {

  private final String id;
  private final String businessKey;
  private final String processInstanceId;
  private final String processDefinitionId;
  private final String tenantId;
  private final String caseInstanceId;

  private boolean ended;
  private boolean suspended;

  public static ProcessInstanceFakeBuilder builder() {
    return new ProcessInstanceFakeBuilder();
  }

  ProcessInstanceFake(String id, String businessKey, String processInstanceId, String processDefinitionId,
                      String tenantId, String caseInstanceId, boolean ended, boolean suspended) {
    this.id = id;
    this.businessKey = businessKey;
    this.processInstanceId = processInstanceId;
    this.processDefinitionId = processDefinitionId;
    this.tenantId = tenantId;
    this.caseInstanceId = caseInstanceId;
    this.ended = ended;
    this.suspended = suspended;
  }


  @Override
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  @Override
  public String getBusinessKey() {
    return businessKey;
  }

  @Override
  public String getCaseInstanceId() {
    return caseInstanceId;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public boolean isSuspended() {
    return suspended;
  }

  @Override
  public boolean isEnded() {
    return ended;
  }

  public void setEnded(boolean ended) {
    this.ended = ended;
  }

  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }

  @Override
  public String getProcessInstanceId() {
    return processInstanceId;
  }

  @Override
  public String getTenantId() {
    return tenantId;
  }

  static class ProcessInstanceFakeBuilder {
    private String id;
    private String businessKey;
    private String processInstanceId;
    private String processDefinitionId;
    private String tenantId;
    private String caseInstanceId;
    private boolean ended = false;
    private boolean suspended = false;

    public ProcessInstanceFakeBuilder id(String id) {
      this.id = id;
      return this;
    }

    public ProcessInstanceFakeBuilder businessKey(String businessKey) {
      this.businessKey = businessKey;
      return this;
    }

    public ProcessInstanceFakeBuilder processInstanceId(String processInstanceId) {
      this.processInstanceId = processInstanceId;
      return this;
    }

    public ProcessInstanceFakeBuilder processDefinitionId(String processDefinitionId) {
      this.processDefinitionId = processDefinitionId;
      return this;
    }

    public ProcessInstanceFakeBuilder tenantId(String tenantId) {
      this.tenantId = tenantId;
      return this;
    }

    public ProcessInstanceFakeBuilder caseInstanceId(String caseInstanceId) {
      this.caseInstanceId = caseInstanceId;
      return this;
    }

    public ProcessInstanceFakeBuilder ended(boolean ended) {
      this.ended = ended;
      return this;
    }

    public ProcessInstanceFakeBuilder suspended(boolean suspended) {
      this.suspended = suspended;
      return this;
    }

    @Override
    public String toString() {
      return "ProcessInstanceFakeBuilder{" +
        "id='" + id + '\'' +
        ", businessKey='" + businessKey + '\'' +
        ", processInstanceId='" + processInstanceId + '\'' +
        ", processDefinitionId='" + processDefinitionId + '\'' +
        ", tenantId='" + tenantId + '\'' +
        ", caseInstanceId='" + caseInstanceId + '\'' +
        ", ended=" + ended +
        ", suspended=" + suspended +
        '}';
    }

    public ProcessInstanceFake build() {
      return new ProcessInstanceFake(
        id,
        businessKey,
        processInstanceId,
        processDefinitionId,
        tenantId,
        caseInstanceId,
        ended,
        suspended
      );
    }

  }
}
