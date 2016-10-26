package org.camunda.bpm.extension.mockito;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.test.mock.MockExpressionManager;

import com.google.common.base.Supplier;

/**
 * Configuration that makes the standard camunda.cfg.xml obsolete by setting the
 * history, schema and job-executor settings.
 * 
 * @deprecated this class also exists in camunda-bpm-needle and should be
 *             centralized.
 */
@Deprecated
public class MostUsefulProcessEngineConfiguration extends StandaloneInMemProcessEngineConfiguration {

  public static MostUsefulProcessEngineConfiguration mostUsefulProcessEngineConfiguration() {
    return new MostUsefulProcessEngineConfiguration();
  }

  public static final Supplier<ProcessEngineConfiguration> SUPPLIER = new Supplier<ProcessEngineConfiguration>() {
    @Override
    public ProcessEngineConfiguration get() {
      return mostUsefulProcessEngineConfiguration();
    }
  };

  public MostUsefulProcessEngineConfiguration() {
    this.history = HISTORY_FULL;
    this.databaseSchemaUpdate = DB_SCHEMA_UPDATE_TRUE;
    this.jobExecutorActivate = false;
    this.expressionManager = new MockExpressionManager();
    this.setCustomPostBPMNParseListeners(new ArrayList<BpmnParseListener>());
    this.setCustomJobHandlers(new ArrayList<JobHandler>());
  }

  public void addCustomJobHandler(final JobHandler jobHandler) {
    checkArgument(jobHandler != null, "jobHandler must not be null!");
    getCustomJobHandlers().add(jobHandler);
  }

  public void addCustomPostBpmnParseListener(final BpmnParseListener bpmnParseListener) {
    checkArgument(bpmnParseListener != null, "bpmnParseListener must not be null!");
    getCustomPostBPMNParseListeners().add(bpmnParseListener);
  }
}
