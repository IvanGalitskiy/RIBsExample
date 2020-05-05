package com.example.ribsexample.demo.child

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ChildRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: ChildBuilder.Component
  @Mock internal lateinit var interactor: ChildInteractor
  @Mock internal lateinit var view: ChildView

  private var router: ChildRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ChildRouter(view, interactor, component)
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

