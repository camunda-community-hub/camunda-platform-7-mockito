# camunda-platform-7-mockito

[![Camunda 7](https://img.shields.io/badge/Community%20Extension-An%20open%20source%20community%20maintained%20project-FF4700)](https://github.com/camunda-community-hub/community)
[![camunda platform 7]](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)
[![](https://img.shields.io/badge/Lifecycle-Stable-brightgreen)](https://github.com/Camunda-Community-Hub/community/blob/main/extension-lifecycle.md#stable-)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.camunda.community.mockito/camunda-platform-7-mockito/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.camunda.community.mockito/camunda-platform-7-mockito)
[![codecov](https://codecov.io/gh/camunda/camunda-platform-7-mockito/branch/master/graph/badge.svg)](https://codecov.io/gh/camunda/camunda-platform-7-mockito) 
[![Apache License V.2](https://img.shields.io/badge/license-Apache%20V.2-blue.svg)](./LICENSE)

| Important                                                                                                                                                                            |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| With camunda 8 released, we renamed this extension to clarify that it is only supposed to work with the Camunda BPM 7 platform, beginning with 6.17.x the maven coordinates changed!  This will also require that you change your imports to `org.camunda.community.mockito`. We are sorry for the inconvenience. |


<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [Get started](#get-started)
- [Mocking of queries](#mocking-of-queries)
- [Mock Listener and Delegate behavior](#mock-listener-and-delegate-behavior)
  - [Possible Actions](#possible-actions)
  - [Easy register and verify mocks](#easy-register-and-verify-mocks)
  - [Auto mock all delegates and listeners](#auto-mock-all-delegates-and-listeners)
- [Delegate[Task|Execution]Fake](#delegatetaskexecutionfake)
- [Mocking of external subprocesses](#mocking-of-external-subprocesses)
- [Mocking of message correlation builder](#mocking-of-message-correlation-builder)
- [Stubbing and verifying access to Camunda Java API services to access process variables](#stubbing-and-verifying-access-to-camunda-java-api-services-to-access-process-variables)
- [Release Notes](#release-notes)
- [Limitations](#limitations)
- [Resources](#resources)
- [Maintainer](#maintainer)
- [License](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

**simplify process mocking and testing**

camunda-platform-7-mockito is a community extension for the Camunda BPM process engine that aims to simplify and 
automate mocking of process applications.

**Features:**

* Fluent mocking of query API - It is now very easy to mock a complex fluent query for the service API without any redundancy.
* Fluent mocking of Listener and Delegate behavior - since delegate and listener methods are void, they can either modify process variables or raise an error. Instead of messing up with mockito's doAnswer() you can use these options with a fluent API.
* Fluent mocking of Sub Processes - Sub process is able to wait for timer, set variable, wait for message, send message, throw exception or just do anything what you want.
* Helpers for registering, retrieving and verifying mocks - convenience methods around Mocks.register().
* Automatic mocking of all expressions and delegates in a process - without explicitly registering mocks, all instances are mocked by default, so no process will fail to run because a JUEL expression is using an unknown statement or identifier.


## Get started

Just include camunda-platform-7-mockito in the test scope of your project:

```xml
<dependency>
  <groupId>org.camunda.community.mockito</groupId>
  <artifactId>camunda-platform-7-mockito</artifactId>
  <scope>test</scope>
  <version>6.18.0</version>
</dependency>
```

gradle (kts):

```kotlin
   testImplementation("org.camunda.community.mockito:camunda-platform-7-mockito:6.18.0")
```


## Mocking of queries

Sometimes you want to test a Bean that uses the query API. Since the API is fluent, you would have to mock every single parameter call and let your service return the mocked query.

With the QueryMocks extension, you can do all this in just one line of code, see [QueryMocksExample.java](src/test/java/org/camunda/bpm/extension/mockito/QueryMocksExample.java).

```java
  public class QueryMocksExample {
    private final TaskService taskService = mock(TaskService.class);
    private final Task task = mock(Task.class);
    
    @Test
    public void mock_taskQuery() {
        // bind query-mock to service-mock and set result to task.
        final TaskQuery taskQuery = QueryMocks.mockTaskQuery(taskService).singleResult(task);

        final Task result = taskService.createTaskQuery().active().activityInstanceIdIn("foo").excludeSubtasks().singleResult();

        assertThat(result).isEqualTo(task);

        verify(taskQuery).active();
        verify(taskQuery).activityInstanceIdIn("foo");
        verify(taskQuery).excludeSubtasks();
    }
}
```

## Mock Listener and Delegate behavior

Mocking void methods using mockito is not very convenient, since you need to 
use the `doAnswer(Answer<>).when()` construct, implement your own answer and pick up the parameter from the 
invocation context. JavaDelegate and ExecutionListener are providing their basic functionality using void methods.  
In general, when working with the Delegate and Listener interfaces, there are basically two things they can do from the 
point of interaction between the process execution: modify process variables and raise errors. 

We can use this to test bpmn-processes without relying on the delegate implementation. 

```java
public class FluentJavaDelegateMockTest {

  private static final String BEAN_NAME = "foo";
  private static final String MESSAGE = "message";

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Test
  public void shouldThrowBpmnError() throws Exception {

    // expect exception
    thrown.expect(BpmnError.class);
    thrown.expectMessage(MESSAGE);

    DelegateExpressions.registerJavaDelegateMock(BEAN_NAME).onExecutionThrowBpmnError("code", MESSAGE);

    final JavaDelegate registeredDelegate = DelegateExpressions.getJavaDelegateMock(BEAN_NAME);

    // test succeeds when exception is thrown
    registeredDelegate.execute(mock(DelegateExecution.class));
  }
}
```

### Possible Actions

* Set single variable

You can set a single variable on execution with: 

```java
DelegateExpressions.registerJavaDelegateMock(BEAN_NAME/BEAN_CLASS)
  .onExecutionSetVariable("key", "value");
```

* Set multiple varibales

You can set multiple variables on execution with:

```java
DelegateExpressions.registerJavaDelegateMock(BEAN_NAME/BEAN_CLASS)
  .onExecutionSetVariables(createVariables()
    .putValue("foo", "bar")
    .putValue("foo2", "bar2")
  );
```

* Throw bpmn error

You can throw an error on execution with:

```java
DelegateExpressions.registerJavaDelegateMock(BEAN_NAME/BEAN_CLASS)
  .onExecutionThrowBpmnError("code", MESSAGE);
```

### Easy register and verify mocks

In addition to the well-known "Mocks.register()" hook, you now have the possibility to register fluent mocks directly:

     registerJavaDelegateMock("name")
     registerMockInstance(YourDelegate.class)

In the latter case, "YourDelegate" has to be annotated with @Named, @Component or @Service, depending on the injection framework you are using.

To verify the Mock execution, you can use

    verifyJavaDelegateMock("name").executed(times(2));

### Auto mock all delegates and listeners

With the autoMock() feature, you can register all Delegates and Listeners at once, without explicitly adding "register"-statements to your testcase.
If you do need to specify behaviour for the mocks, you can still get the mock via `getJavaDelegateMock` for delegates. 
And `getExecutionListenerMock` / `getTaskListenerMock` for listeners.

```java
@Test
@Deployment(resources = "MockProcess.bpmn")
  public void register_mocks_for_all_listeners_and_delegates() throws Exception {
    autoMock("MockProcess.bpmn");

    final ProcessInstance processInstance = processEngineRule.getRuntimeService().startProcessInstanceByKey("process_mock_dummy");

    assertThat(processEngineRule.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult()).isNotNull();

    verifyTaskListenerMock("verifyData").executed();
    verifyExecutionListenerMock("startProcess").executed();
    verifyJavaDelegateMock("loadData").executed();
    verifyExecutionListenerMock("beforeLoadData").executed();
  }
```

## Delegate[Task|Execution]Fake

Unit-testing listeners and JavaDelegates can be difficult, because the methods are void and only
 white-box testing via verify is possible. But most of the time, you just want to 
 confirm that a certain variable was set (or a dueDate, a candidate, ...).
  
In these cases, use the Delegate fakes. The implement the interfaces DelegateTask and DelegateExecution,
but are implemented as plain, fluent-styled Pojos.

So to test if your TaskListener

```java
TaskListener taskListener = task -> {
  if (EVENTNAME_CREATE.equals(task.getEventName()) && "the_task".equals(task.getTaskDefinitionKey())) {
    task.addCandidateGroup((String) task.getVariableLocal("nextGroup"));
  }
};
```
actually adds a candidateGroup that is read from a taskLocal variable on create, you can write a Test like this one:

```java
@Test
public void taskListenerSetsCandidateGroup() throws Exception {
  // given a delegateTask 
  DelegateTask delegateTask = delegateTaskFake()
    .withTaskDefinitionKey("the_task")
    .withEventName(EVENTNAME_CREATE)
    .withVariableLocal("nextGroup", "foo");

  // when
  taskListener.notify(delegateTask);

  // then the candidate group was set
  assertThat(candidateGroupIds(delegateTask)).containsOnly("foo");

}
 
```

## Mocking of external subprocesses

With ProcessExpressions.registerCallActivityMock() you can easily register a mocked process which is able to act with the following behaviours:

* onExecutionAddVariable ... the MockProcess will add the given process variable
* onExecutionWaitForTimerWithDate ... the MockProcess will wait for the given date
* onExecutionWaitForTimerWithDuration ... the MockProcess will wait until the given duration is reached
* onExecutionSendMessage ... the MockProcess will correlate the given message (to all or a single process)
* onExecutionWaitForMessage ... the MockProcess will wait for the given message
* onExecutionRunIntoError ... the MockProcess will throw the given Throwable as RuntimeException
* onExecutionDo ... the MockProcess will execute the given consumer
* onExecutionThrowEscalation ... the MockProcess will throw escalation with the given code at the end event

All of those methods could be combined on the fluent sub process mock builder. 

The following example will e.g. register a process mock which does the following:

1) Wait until the given message `SomeMessage` gets correlated to the mock
2) Then wait until the given date `waitUntilDate` is reached
3) After this, a process variable `foo` is set with a value of `bar

```java
ProcessExpressions
  .registerSubProcessMock(SUB_PROCESS_ID)
  .onExecutionWaitForMessage("SomeMessage")
  .onExecutionWaitForTimerWithDate(waitUntilDate)
  .onExecutionSetVariables(createVariables().putValue("foo", "bar"))
  .deploy(rule);
```

More examples could be found in the following class [`CallActivityMockExampleTest`](src/test/java/org/camunda/bpm/extension/mockito/CallActivityMockExampleTest.java).

## Mocking of message correlation builder

Sometimes you have services or delegates responsible for the execution of message correlation 
with your process engine. Camunda provides a fluent builder API for creation a message correlation
and running it.

``` java
class MyCorrelator {

  private final RuntimeService runtimeService;
  private final String value;
  private final String businessKey;

  MyCorrelator(RuntimeService runtimeService, String businessKey, String value) {
    this.runtimeService = runtimeService;
    this.value = value;
    this.businessKey = businessKey;
  }

  void correlate() {
    this.runtimeService
      .createMessageCorrelation("MESSAGE_NAME")
      .processDefinitionId("some_process_id")
      .processInstanceBusinessKey(businessKey)
      .setVariable("myVar1", value)
      .correlate();
  }
}
 
```

In order to test those, you can use the following helper:

```java
package org.camunda.community.mockito;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MessageCorrelationMockExample {


  @Test
  public void mock_messageCorrelation() {

    // setup mock
    final RuntimeService runtimeService = mock(RuntimeService.class);
    final MessageCorrelationBuilder correlation = ProcessExpressions.mockMessageCorrelation(runtimeService, "MESSAGE_NAME");
    final MyCorrelator serviceUnderTest = new MyCorrelator(runtimeService, "my-business-key", "value-1");

    // execute correlation, e.g. in a class under test (service, delegate, whatever)
    serviceUnderTest.correlate();

    // verify
    verify(correlation).correlate();
    verify(correlation).processDefinitionId("some_process_id");
    verify(correlation).processInstanceBusinessKey("my-business-key");
    verify(correlation).setVariable("myVar1", "value-1");

    verify(runtimeService).createMessageCorrelation("MESSAGE_NAME");

    verifyNoMoreInteractions(correlation);
    verifyNoMoreInteractions(runtimeService);
  }
}
```

## Stubbing and verifying access to Camunda Java API services to access process variables 

If you use [camunda-bpm-data](https://github.com/holunda-io/camunda-bpm-data/) library to access process variables, you might 
want to test that access. If you are testing `DelegateTask` or `DelegateExecution` code, the examples above already gives you
possibilities to do so. If your code relies on direct access to Camunda Java API services (`RuntimeService`, `TaskService` and
`CaseService`) you might need to stub them and verify with the help of `ServiceExpressions` helper:

```java
package org.camunda.community.mockito;

import io.holunda.camunda.bpm.data.factory.VariableFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.community.mockito.ServiceExpressions;
import org.junit.Test;

import java.util.UUID;

import static io.holunda.camunda.bpm.data.CamundaBpmData.booleanVariable;
import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class RuntimeServiceAwareServiceTest {

  private static final VariableFactory<String> ORDER_ID = stringVariable("orderId");
  private static final VariableFactory<Boolean> ORDER_FLAG = booleanVariable("orderFlag");

  @Test
  public void check_stubbed_access() {

    // setup mock
    final RuntimeService runtimeService = mock(RuntimeService.class);
    // stub access
    ServiceExpressions.runtimeServiceVariableStubBuilder(runtimeService)
                      .defineAndInitializeLocal(ORDER_ID, "initial-Value")
                      .define(ORDER_FLAG)
                      .build();
    // setup service
    final RuntimeServiceAwareService serviceUnderTest = new RuntimeServiceAwareService(runtimeService);
    // setup verifier
    final RuntimeServiceVerification verifier = ServiceExpressions.runtimeServiceVerification(runtimeService);

    String executionId = UUID.randomUUID().toString();

    // execute service calls and check results
    serviceUnderTest.writeLocalId(executionId, "4712");
    String orderId = serviceUnderTest.readLocalId(executionId);
    assertThat(orderId).isEqualTo("4712");

    assertThat(serviceUnderTest.flagExists(executionId)).isFalse();
    serviceUnderTest.writeFlag(executionId, true);
    assertThat(serviceUnderTest.flagExists(executionId)).isTrue();
    Boolean orderFlag = serviceUnderTest.readFlag(executionId);
    assertThat(orderFlag).isEqualTo(true);

    // verify service access
    verifier.verifySetLocal(ORDER_ID, "4712", executionId );
    verifier.verifyGetLocal(ORDER_ID, executionId);
    verifier.verifyGetVariables(executionId, times(2));
    verifier.verifySet(ORDER_FLAG, true, executionId);
    verifier.verifyGet(ORDER_FLAG, executionId);
    verifier.verifyNoMoreInteractions();

  }
}
```



## Release Notes

see https://camunda.github.io/camunda-platform-7-mockito/release-notes/

## Limitations

* Though it is possible to use arbitrary beans as expressions (myBean.doSomething()), we solely focus on 
Listeners (notify()) and Delegates (execute()) here, since this is the only way to apply automatic behavior. If you need
to mock custom beans, you still can use some other tools to register the mock, but can not use the fluent mocking or 
auto mocking feature. Due to the nature of automatic mocking, this is immanent and will not change.
* Currently, only expression-delegates (${myDelegate}) are supported (as you do use with CDI/Spring)) but no FQN class names. 
This might and probably will change with future versions, it just has to be implemented ... 
* while automocking, expressions are only parsed for listeners and delegates, not for process variables.

## Resources

* [Issue Tracker](https://github.com/camunda/camunda-platform-7-mockito/issues)
* [Contributing](https://github.com/camunda/camunda-platform-7-mockito/blob/master/CONTRIBUTING.md) 
* [openhub](https://www.openhub.net/p/camunda-bpm-mockito)

## Maintainer

* [Jan Galinski](https://github.com/jangalinski)
* [Simon Zambrovski](https://github.com/zambrovski)
* ??
  
## License

* [Apache License, Version 2.0](./LICENSE)

