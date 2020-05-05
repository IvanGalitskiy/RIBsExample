package com.example.ribsexample.root.logged_out.sign_up

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SignUpBuilder.SignUpScope}.
 */
class SignUpRouter(
    view: SignUpView,
    interactor: SignUpInteractor,
    component: SignUpBuilder.Component
) : ViewRouter<SignUpView, SignUpInteractor, SignUpBuilder.Component>(view, interactor, component)
