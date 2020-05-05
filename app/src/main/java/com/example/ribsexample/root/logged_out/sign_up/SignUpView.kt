package com.example.ribsexample.root.logged_out.sign_up

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import kotlinx.android.synthetic.main.rib_sign_up.view.*

/**
 * Top level view for {@link SignUpBuilder.SignUpScope}.
 */
class SignUpView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), SignUpInteractor.SignUpPresenter {
    private val uiEventsRelay = PublishRelay.create<SignUpInteractor.SignUpPresenter.UiEvent>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        vCreateAccountBtn.setOnClickListener {
            uiEventsRelay.accept(SignUpInteractor.SignUpPresenter.UiEvent.LoginEvent(vProfileName.text.toString()))
        }
        vCancelBtn.setOnClickListener {
            uiEventsRelay.accept(SignUpInteractor.SignUpPresenter.UiEvent.CancelEvent())
        }
    }

    override fun getUiEvents(): Observable<SignUpInteractor.SignUpPresenter.UiEvent> = uiEventsRelay
}
