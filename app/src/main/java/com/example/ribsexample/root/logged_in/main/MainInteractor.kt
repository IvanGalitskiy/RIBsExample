package com.example.ribsexample.root.logged_in.main

import com.example.ribsexample.entities.UserProfile
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.core.lifecycle.InteractorEvent
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MainScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class MainInteractor : Interactor<MainInteractor.MainPresenter, MainRouter>() {

    @Inject
    lateinit var presenter: MainPresenter
    @Inject
    lateinit var currentUser: UserProfile

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.displayUser(currentUser)
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface MainPresenter {
        fun displayUser(user: UserProfile)
    }
}
