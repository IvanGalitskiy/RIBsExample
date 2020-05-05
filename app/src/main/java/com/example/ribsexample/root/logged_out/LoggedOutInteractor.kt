package com.example.ribsexample.root.logged_out

import com.example.ribsexample.entities.UserProfile
import com.example.ribsexample.root.logged_out.sign_in.SignInInteractor
import com.example.ribsexample.root.logged_out.sign_up.SignUpInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedOutScope].
 */
@RibInteractor
class LoggedOutInteractor : Interactor<EmptyPresenter, LoggedOutRouter>() {
    @Inject
    lateinit var ribListener: Listener

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachSignIn()
    }

    override fun willResignActive() {
        super.willResignActive()
    }

    inner class SignInListener : SignInInteractor.Listener {
        override fun onLoggedIn(userProfile: UserProfile) {
            ribListener.login(userProfile)
        }

        override fun onSignUpRequested() {
            router.attachSignUp()
        }
    }

    inner class SignUpListener : SignUpInteractor.Listener {
        override fun onUserCreated(userProfile: UserProfile) {
            ribListener.login(userProfile)
        }

        override fun onCancel() {
            router.attachSignIn()
        }
    }

    interface Listener {
        fun login(userProfile: UserProfile)
    }
}
