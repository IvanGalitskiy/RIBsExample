package com.example.ribsexample.root.logged_out.sign_in

import com.example.ribsexample.entities.UserProfile
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SignInScope].
 */
@RibInteractor
class SignInInteractor : Interactor<SignInInteractor.SignInPresenter, SignInRouter>() {

    @Inject
    lateinit var presenter: SignInPresenter
    @Inject
    lateinit var ribListener: Listener

    private val disposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        disposable.add(presenter.getUiEvents().subscribe({ uiEvent ->
            when (uiEvent) {
                is SignInPresenter.UiEvent.CreateAccount -> ribListener.onSignUpRequested()
                is SignInPresenter.UiEvent.LoginEvent -> ribListener.onLoggedIn(UserProfile(uiEvent.userName, false))
            }
        }, {}))
    }

    override fun willResignActive() {
        disposable.clear()
        super.willResignActive()
    }

    interface Listener {
        fun onLoggedIn(userProfile: UserProfile)
        fun onSignUpRequested()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface SignInPresenter {
        fun getUiEvents(): Observable<UiEvent>


        sealed class UiEvent {
            class CreateAccount : UiEvent()
            data class LoginEvent(val userName: String) : UiEvent()
        }

    }
}
