package com.example.ribsexample.root.logged_in.main

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MainBuilder.MainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainRouter(
    view: MainView,
    interactor: MainInteractor,
    component: MainBuilder.Component) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component)
