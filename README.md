# camunda-bpm-mockito

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.camunda.bpm.extension/camunda-bpm-mockito/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.camunda.bpm.extension/camunda-bpm-mockito)

**simplify process mocking and testing**

camunda-bpm-mockito is a community extension for the Camunda BPM process engine that aims to simplify and 
automate mocking of process applications.

**Features:**

* Fluent mocking of query API - It is now very easy to mock a complex fluent query for the service API without any redundancy.
* Fluent mocking of Listener and Delegate behavior - since delegate and listener methods are void, they can either modify process variables or raise an error. Instead of messing up with mockito's doAnswer() you can use these options with a fluent API.
* Helpers for registering, retrieving and verifying mocks - convenience methods around Mocks.register().
* Automatic mocking of all expressions and delegates in a process - without explicitly registering mocks, all instances are mocked by default, so no process will fail to run because a JUEL expression is using an unknown statement or identifier.

## Release Notes

### 2.2

* add support for asc() and desc() query methods (see #26)

### 2.0

* switch to camunda 7.2
* remove org.camunda.bpm.extension.util.ProcessVariableMaps -> use Variables instead
* remove dependencies to cdi (you will have to add them again to use the extension with cdi/ejb)
* add support for spring expressions 

## Get started

Just include camunda-bpm-mockito in the test scope of your project:

```xml
<dependency>
  <groupId>org.camunda.bpm.extension</groupId>
  <artifactId>camunda-bpm-mockito</artifactId>
  <scope>test</scope>
  <version>2.2</version>
</dependency>
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

Mocking void methods usiong mockito is not very convenient, since you need to use the doAnswer(Answer<T>).when() construct, implement your own answer and pick up the parameter from the invocation context. JavaDelegate and ExecutionListener are providing their basic fuctionality using void methods.  In general, when working with the Delegate and Listener interfaces, there are basically two things they can do from the point of intercation between the process execution: modify process variables and raise errors. We can use this to test bpmn-processes without relying on the delegate implementation. 

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

## Easy register and verify mocks

In addition two of the well-known "Mocks.register()" hook, you now have the possibility to register fluent mocks directly:

     registerJavaDelegateMock("name")
     registerMockInstance(YourDelegate.class)

In the latter case, "YourDelegate" has to be annotated with @Named, @Component or @Service, depending on the injection framework you are using.

To verify the Mock execution, you can use

    verifyJavaDelegateMock("name").executed(times(2));

## Auto mock all delegates and listeners

With the autoMock() feature, you can register all Delegates and Listeners at once, without explicitly adding "register"-statements to your testcase.
If you do need to specify behaviour for the mocks, you can still get the mock via "getRegisteredJavaDelegateMock" (for delegates).

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

**Limitations:**

* though it is possible to use arbitrary beans as expressions (myBean.doSomething()), we solely focus on 
Listeners (notify()) and Delegates (execute()) here, since this is the only way to apply automatic behavior. If you need
to mock custom beans, you still can use some of the tools to register the mock, but can not use the fluent mocking or 
auto mocking feature. Due to the nature of automatic mocking, this is immanent and will not change.
* Currently, only expression-delegates (${myDelegate}) are supported (as you do use with CDI/Spring)) but no FQN class names. 
This might and probably will change with future versions, it just has to be implemented ... 
* while automocking, expressions are only parsed for listeners and delegates, not for process variables.

## Resources

* [Issue Tracker](https://github.com/camunda/camunda-bpm-mockito/issues)
* [Contributing](https://github.com/camunda/camunda-bpm-mockito/blob/master/CONTRIBUTING.md) 
* [openhub](https://www.openhub.net/p/camunda-bpm-mockito)

## Maintainer

* [Jan Galinski](https://github.com/jangalinski)
* [Simon Zambrovski](https://github.com/zambrovski)
* [Christian Lipphardt](https://github.com/hawky-4s-)


## License

* [Apache License, Version 2.0](./LICENSE)

