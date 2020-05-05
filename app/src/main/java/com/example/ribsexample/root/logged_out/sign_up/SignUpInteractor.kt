package com.example.ribsexample.root.logged_out.sign_up

import com.example.ribsexample.entities.UserProfile
import com.example.ribsexample.root.logged_out.sign_in.SignInInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SignUpScope].
 */
@RibInteractor
class SignUpInteractor : Interactor<SignUpInteractor.SignUpPresenter, SignUpRouter>() {

    @Inject
    lateinit var presenter: SignUpPresenter
    @Inject
    lateinit var ribListener: Listener
    private val disposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        disposable.add(presenter.getUiEvents().subscribe({ uiEvent ->
            when (uiEvent) {
                is SignUpPresenter.UiEvent.CancelEvent -> ribListener.onCancel()
                is SignUpPresenter.UiEvent.LoginEvent -> ribListener.onUserCreated(UserProfile(uiEvent.userName, true))
            }
        }, {}))
    }

    override fun willResignActive() {
        disposable.clear()
        super.willResignActive()
    }

    interface Listener {
        fun onUserCreated(userProfile: UserProfile)
        fun onCancel()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface SignUpPresenter {
        fun getUiEvents(): Observable<UiEvent>


        sealed class UiEvent {
            class CancelEvent : UiEvent()
            data class LoginEvent(val userName: String) : UiEvent()
        }
    }
}
