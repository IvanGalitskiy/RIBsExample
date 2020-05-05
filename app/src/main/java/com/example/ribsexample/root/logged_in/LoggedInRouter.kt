package com.example.ribsexample.root.logged_in

import android.view.ViewGroup
import com.example.ribsexample.root.logged_in.main.MainBuilder
import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoggedInRouter(
    interactor: LoggedInInteractor,
    component: LoggedInBuilder.Component,
    private val viewContainer: ViewGroup,
    private val mainBuilder: MainBuilder
) : Router<LoggedInInteractor, LoggedInBuilder.Component>(interactor, component) {
    fun attachMain() {
        val router = mainBuilder.build(viewContainer)
        viewContainer.addView(router.view)
        attachChild(router)
    }
}
