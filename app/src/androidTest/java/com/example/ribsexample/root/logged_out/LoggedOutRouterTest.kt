package com.example.ribsexample.root.logged_out

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoggedOutRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: LoggedOutBuilder.Component
  @Mock internal lateinit var interactor: LoggedOutInteractor

  private var router: LoggedOutRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = LoggedOutRouter(interactor, component)
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
