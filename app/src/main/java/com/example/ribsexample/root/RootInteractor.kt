package com.example.ribsexample.root

import com.example.ribsexample.entities.UserProfile
import com.example.ribsexample.root.logged_out.LoggedOutInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject
    lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachLoggedOut()
    }

    override fun willResignActive() {
        super.willResignActive()
    }

    inner class LoggedOutListener : LoggedOutInteractor.Listener {
        override fun login(userProfile: UserProfile) {
            router.attachLoggedIn(userProfile)
        }
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter
}
