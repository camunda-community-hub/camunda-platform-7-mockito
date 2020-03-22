package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * Implementation that registers the delegates mocks for the mocked subprocess to the gived spring bean context.
 */
public class CallActivityMockForSpringContext extends CallActivityMock {

  /** Registry where all the mocks will be placed to */
  private SingletonBeanRegistry springBeanRegistry;

  /**
   * @param processId Process definition key of the subprocess to mock. This should be the value of the 'called element' attribute of the
   *   mocked CallActivity element.
   * @param modelConfigurer Object that allows to customize the mock process model for the subprocess
   * @param springBeanRegistry Spring context to place the implementations of the activities in the mocked process to
   */
  public CallActivityMockForSpringContext(final String processId, final MockedModelConfigurer modelConfigurer,
      final SingletonBeanRegistry springBeanRegistry) {
    super(processId, modelConfigurer);
    this.springBeanRegistry = springBeanRegistry;
  }

  /**
   * Constructor that does not allow for customizing of the mocked subprocess model
   */
  public CallActivityMockForSpringContext(final String processId, final SingletonBeanRegistry springBeanRegistry) {
    this(processId, null, springBeanRegistry);
  }

  public SingletonBeanRegistry getSpringBeanRegistry() {
    return springBeanRegistry;
  }

  @Override
  protected void registerJavaDelegateMock(String delegateReferenceName, JavaDelegate delegate) {
    // Since mock delegate names are generated as random strings, we don't have to unregister an existing bean
    springBeanRegistry.registerSingleton(delegateReferenceName, delegate);
  }

}
