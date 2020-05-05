package com.example.ribsexample.root.logged_out.sign_up

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SignUpInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: SignUpInteractor.SignUpPresenter
  @Mock internal lateinit var router: SignUpRouter

  private var interactor: SignUpInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestSignUpInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<SignUpInteractor.SignUpPresenter, SignUpRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}