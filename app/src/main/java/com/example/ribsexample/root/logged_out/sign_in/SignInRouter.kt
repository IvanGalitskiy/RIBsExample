package com.example.ribsexample.root.logged_out.sign_in

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SignInBuilder.SignInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SignInRouter(
    view: SignInView,
    interactor: SignInInteractor,
    component: SignInBuilder.Component) : ViewRouter<SignInView, SignInInteractor, SignInBuilder.Component>(view, interactor, component)
