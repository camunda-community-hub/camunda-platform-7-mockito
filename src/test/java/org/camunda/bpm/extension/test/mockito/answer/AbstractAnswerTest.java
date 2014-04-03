package org.camunda.bpm.extension.test.mockito.answer;

import org.camunda.bpm.engine.delegate.VariableScope;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AbstractAnswerTest {

  private AbstractAnswer<VariableScope> answer = spy(new AbstractAnswer<VariableScope>() {

    @Override
    protected void answer(final VariableScope parameter) {

    }
  });

  @Mock
  private VariableScope variableScope;

  @Mock
  private InvocationOnMock invocationOnMock;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
  }

  @Test
  public void shouldDelegateToGenericAnswer() throws Throwable {
    when(invocationOnMock.getArguments()).thenReturn(new Object[]{variableScope});
    answer.answer(invocationOnMock);
    verify(answer).answer(variableScope);
  }

}
