package com.example.ribsexample.root

import android.view.ViewGroup
import com.example.ribsexample.entities.UserProfile
import com.example.ribsexample.root.logged_in.LoggedInBuilder
import com.example.ribsexample.root.logged_out.LoggedOutBuilder
import com.uber.rib.core.Router

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val loggedOutBuilder: LoggedOutBuilder,
    private val loggedInBuilder: LoggedInBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {
    var attachedRouter: Router<*, *>? = null

    fun attachLoggedOut() {
        detachCurrent()
        loggedOutBuilder.build(view as ViewGroup).also { router ->
            attachedRouter = router
            attachChild(router)
        }
    }

    fun attachLoggedIn(userProfile: UserProfile) {
        detachCurrent()
        loggedInBuilder.build(view as ViewGroup, LoggedInBuilder.LoggedInInputParam(userProfile))
            .also { router ->
                attachedRouter = router
                attachChild(router)
            }
    }

    private fun detachCurrent() {
        attachedRouter?.let {
            detachChild(attachedRouter)
        }
    }
}
