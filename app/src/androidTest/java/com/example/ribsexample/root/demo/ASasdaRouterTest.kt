package com.example.ribsexample.root.demo

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ASasdaRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: ASasdaBuilder.Component
  @Mock internal lateinit var interactor: ASasdaInteractor
  @Mock internal lateinit var view: ASasdaView

  private var router: ASasdaRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ASasdaRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

