package com.example.ribsexample.root.logged_in

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoggedInRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: LoggedInBuilder.Component
  @Mock internal lateinit var interactor: LoggedInInteractor

  private var router: LoggedInRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = LoggedInRouter(interactor, component)
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
