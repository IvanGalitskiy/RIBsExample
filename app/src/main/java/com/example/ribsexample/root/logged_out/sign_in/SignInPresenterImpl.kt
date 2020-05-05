package com.example.ribsexample.root.logged_out.sign_in

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import kotlinx.android.synthetic.main.rib_sign_in.view.*
import kotlinx.android.synthetic.main.rib_sign_in.view.vProfileName
import javax.inject.Inject

class SignInPresenterImpl @Inject constructor(private val view: SignInView) :
    SignInInteractor.SignInPresenter {

    init {
        view.vLoginBtn.setOnClickListener {
            uiEventsRelay.accept(SignInInteractor.SignInPresenter.UiEvent.LoginEvent(view.vProfileName.text.toString()))
        }
        view.vCreateAccountBtn.setOnClickListener {
            uiEventsRelay.accept(SignInInteractor.SignInPresenter.UiEvent.CreateAccount())
        }
    }

    private val uiEventsRelay = PublishRelay.create<SignInInteractor.SignInPresenter.UiEvent>()

    override fun getUiEvents(): Observable<SignInInteractor.SignInPresenter.UiEvent> = uiEventsRelay
}