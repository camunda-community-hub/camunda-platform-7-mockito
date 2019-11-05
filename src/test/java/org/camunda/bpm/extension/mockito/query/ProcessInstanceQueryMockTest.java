package org.camunda.bpm.extension.mockito.query;

import org.assertj.core.util.Lists;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ProcessInstanceQueryMockTest {

  @Mock
  private RuntimeService runtimeServiceMock;

  @Test
  public void should_not_throw_stubbing_exception_on_only_single_result() {

    // GIVEN
    CamundaMockito.mockProcessInstanceQuery(runtimeServiceMock)
                  .singleResult(mock(ProcessInstance.class));

    // WHEN
    final ProcessInstance result = runtimeServiceMock.createProcessInstanceQuery()
                                                     .processInstanceId("test")
                                                     .singleResult();
    // THEN
    assertThat(result).isNotNull();
  }

  @Test
  public void should_not_throw_stubbing_exception_on_only_list() {

    // GIVEN
    CamundaMockito.mockProcessInstanceQuery(runtimeServiceMock)
                  .list(Lists.newArrayList(mock(ProcessInstance.class)));

    // WHEN
    final List<ProcessInstance> result = runtimeServiceMock.createProcessInstanceQuery()
                                                           .processInstanceId("test")
                                                           .list();
    // THEN
    assertThat(result).isNotNull();
  }
}
