package org.camunda.bpm.extension.mockito.process;

import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

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
   * Variant with an ApplicationContext as the parameter. This is usually easier to get access to in the tests
   * (e.g. via autowiring).
   */
  public CallActivityMockForSpringContext(final String processId, final MockedModelConfigurer modelConfigurer,
    final ApplicationContext applicationContext) {
    this(processId, modelConfigurer, getSingletonBeanRegistry(applicationContext));
  }

  /**
   * Constructor that does not allow for customizing of the mocked subprocess model
   */
  public CallActivityMockForSpringContext(final String processId, final SingletonBeanRegistry springBeanRegistry) {
    this(processId, null, springBeanRegistry);
  }

  /**
   * Variant with an ApplicationContext as the parameter. This is usually easier to get access to in the tests
   * (e.g. via autowiring).
   */
  public CallActivityMockForSpringContext(final String processId, final ApplicationContext applicationContext) {
    this(processId, getSingletonBeanRegistry(applicationContext));
  }

  private static SingletonBeanRegistry getSingletonBeanRegistry(ApplicationContext applicationContext) {
    if (!(applicationContext instanceof ConfigurableApplicationContext)) {
      throw new IllegalArgumentException("applicationContext is not an instance of ConfigurableApplicationContext");
    }
    return ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
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
