package com.example.ribsexample.root.demo

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ASasdaInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: ASasdaInteractor.ASasdaPresenter
  @Mock internal lateinit var router: ASasdaRouter

  private var interactor: ASasdaInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestASasdaInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<ASasdaInteractor.ASasdaPresenter, ASasdaRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}