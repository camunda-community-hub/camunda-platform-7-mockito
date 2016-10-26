package org.camunda.bpm.extension.mockito.generator.processor;

import com.squareup.javapoet.JavaFile;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.history.HistoricCaseInstanceQuery;
import org.camunda.bpm.engine.query.Query;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentQuery;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(Enclosed.class)
public class QueryDataTest {



  public static class Init {

    @Test
    public void find_all_camunda_queries() throws Exception {
      Set<QueryData> data = QueryDataSupplier.INSTANCE.get();
      assertThat(data).hasSize(39);

      for (QueryData d : data) {
        assertThat(Query.class.isAssignableFrom(d.getQueryType())).as(d + ": not a query").isTrue();
        assertThat(d.getQueryType().getMethod("singleResult")).as(d + ": has no singleResult method").isNotNull();
        assertThat(d.getResultType()).as(d + ": result is null").isNotNull();
      }
    }
  }


  public static class GenerateMockClass {

    @Test
    public void generate() throws IOException {
      QueryData d = new QueryData(TaskService.class, TaskQuery.class, Task.class);

      System.out.println(d.generateClass());
    }

  }
}
