package com.example.ribsexample.root.logged_out

import android.view.ViewGroup
import com.example.ribsexample.root.logged_out.sign_in.SignInBuilder
import com.example.ribsexample.root.logged_out.sign_up.SignUpBuilder
import com.uber.rib.core.Router
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LoggedOutBuilder.LoggedOutScope}.
 */
class LoggedOutRouter(
    interactor: LoggedOutInteractor,
    component: LoggedOutBuilder.Component,
    private val containerView: ViewGroup,
    private val signInBuilder: SignInBuilder,
    private val signUpBuilder: SignUpBuilder
) : Router<LoggedOutInteractor, LoggedOutBuilder.Component>(interactor, component) {

    var attachedRouter: Router<*, *>? = null

    override fun willDetach() {
        detachCurrentRouterIfNeeded()
        super.willDetach()
    }

    fun attachSignIn() {
        detachCurrentRouterIfNeeded()
        attach(signInBuilder.build(containerView))
    }

    fun attachSignUp() {
        detachCurrentRouterIfNeeded()
        attach(signUpBuilder.build(containerView))
    }

    override fun handleBackPress(): Boolean {
        return super.handleBackPress()
    }

    private fun attach(router: Router<*, *>) {
        attachedRouter = router
        attachViewIfNeeded(attachedRouter)
        attachChild(attachedRouter)
    }

    private fun attachViewIfNeeded(attachedRouter: Router<*, *>?) {
        if (attachedRouter is ViewRouter<*, *, *>) {
            containerView.addView((attachedRouter).view)
        }
    }

    private fun detachCurrentRouterIfNeeded() {
        attachedRouter?.let { currentRouter ->
            if (currentRouter is ViewRouter<*, *, *>) {
                containerView.removeView(currentRouter.view)
            }
            detachChild(currentRouter)
        }
        attachedRouter = null
    }
}
