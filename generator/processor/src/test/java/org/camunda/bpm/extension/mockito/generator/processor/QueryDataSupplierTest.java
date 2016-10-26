package org.camunda.bpm.extension.mockito.generator.processor;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.batch.history.HistoricBatch;
import org.camunda.bpm.engine.batch.history.HistoricBatchQuery;
import org.camunda.bpm.engine.history.*;
import org.camunda.bpm.engine.runtime.*;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryDataSupplierTest {

  @Test
  public void forRuntimeService() throws Exception {

    Set<QueryData> set = QueryDataSupplier.forService(RuntimeService.class);

    assertThat(set).hasSize(5);
    assertThat(set).containsOnly(
      new QueryData(RuntimeService.class, EventSubscriptionQuery.class, EventSubscription.class),
      new QueryData(RuntimeService.class, ExecutionQuery.class, Execution.class),
      new QueryData(RuntimeService.class, IncidentQuery.class, Incident.class),
      new QueryData(RuntimeService.class, ProcessInstanceQuery.class, ProcessInstance.class),
      new QueryData(RuntimeService.class, VariableInstanceQuery.class, VariableInstance.class));
  }

  @Test
  public void forHistoryService() throws Exception {

    Set<QueryData> set = QueryDataSupplier.forService(HistoryService.class);
    assertThat(set).hasSize(14);
    assertThat(set).containsOnly(
      new QueryData(HistoryService.class, HistoricActivityInstanceQuery.class, HistoricActivityInstance.class),
      new QueryData(HistoryService.class, HistoricActivityStatisticsQuery.class, HistoricActivityStatistics.class),
      new QueryData(HistoryService.class, HistoricBatchQuery.class, HistoricBatch.class),
      new QueryData(HistoryService.class, HistoricCaseActivityInstanceQuery.class, HistoricCaseActivityInstance.class),
      new QueryData(HistoryService.class, HistoricCaseInstanceQuery.class, HistoricCaseInstance.class),
      new QueryData(HistoryService.class, HistoricDecisionInstanceQuery.class, HistoricDecisionInstance.class),
      new QueryData(HistoryService.class, HistoricDetailQuery.class, HistoricDetail.class),
      new QueryData(HistoryService.class, HistoricIdentityLinkLogQuery.class, HistoricIdentityLinkLog.class),
      new QueryData(HistoryService.class, HistoricIncidentQuery.class, HistoricIncident.class),
      new QueryData(HistoryService.class, HistoricJobLogQuery.class, HistoricJobLog.class),
      new QueryData(HistoryService.class, HistoricProcessInstanceQuery.class, HistoricProcessInstance.class),
      new QueryData(HistoryService.class, HistoricTaskInstanceQuery.class, HistoricTaskInstance.class),
      new QueryData(HistoryService.class, HistoricVariableInstanceQuery.class, HistoricVariableInstance.class),
      new QueryData(HistoryService.class, UserOperationLogQuery.class, UserOperationLogEntry.class)
      );
  }
}
