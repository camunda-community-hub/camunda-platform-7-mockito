# camunda-bpm-mockito

camunda-bpm-mockito is a community extension for the camunda bpm process engine that aims to simplify and 
automate mocking of process applications.

**Features:**

* Fluent mocking of query API - It is now very easy to mock a complex fluent query against the service api without any redundancy
* Fluent mocking of Listener and Delegate behavior - since delegates and listener methods are void, they only can modify process variables or raise an error. Instead of messing with mockitos doAnswer() you can use these options with a fluent api
* Helpers for registering, retrieving and verifying mocks - convenience methods around Mocks.register()
* Auto Mocking all expressions and delegates in a process - without explicitly registering mocks, all instances are mocked by default, so no process will fail to run because an expression is unknown.

**Limitations:**

* though it is possible to use arbitrary beans as expressions (myBean.doSomething()), we solely focus on 
Listeners (notify()) and Delegates (execute()) here, since this is the only way to apply automatic behavior. If you need
to mock custom beans, you still can use some of the tools to register the mock, but can nit use the fluent mocking or 
auto mocking feature. Do to the nature of automatic mocking, this is immanent and will not change.
* Currently, only expression-delegates (${myDelegate}) are supported (as you do use with CDI/Spring)) but no FQN class names. 
This may and probably will change with future versions, it just has to be implemented ... 
* expressions are only parsed for listeners and delegates, not for process variables.

But enough of "what we cannot do", lets move on to "what we can do":

## Mocking of queries

TBD

## Mock Listener and Delegate behavior

TBD

## Easy register and verify mocks

TBD

## Auto mock all delegates and listeners

TBD




