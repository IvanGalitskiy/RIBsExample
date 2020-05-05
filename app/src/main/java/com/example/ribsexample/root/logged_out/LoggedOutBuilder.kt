package com.example.ribsexample.root.logged_out

import android.view.ViewGroup
import com.example.ribsexample.root.logged_out.sign_in.SignInBuilder
import com.example.ribsexample.root.logged_out.sign_in.SignInInteractor
import com.example.ribsexample.root.logged_out.sign_up.SignUpBuilder
import com.example.ribsexample.root.logged_out.sign_up.SignUpInteractor
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

class LoggedOutBuilder(dependency: ParentComponent) :
    Builder<LoggedOutRouter, LoggedOutBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [LoggedOutRouter].
     *
     * @return a new [LoggedOutRouter].
     */
    fun build(containerView: ViewGroup): LoggedOutRouter {
        val interactor = LoggedOutInteractor()
        val component = DaggerLoggedOutBuilder_Component.builder()
            .parentComponent(dependency)
            .containerView(containerView)
            .interactor(interactor)
            .build()

        return component.loggedoutRouter()
    }

    interface ParentComponent {
        fun ribListener(): LoggedOutInteractor.Listener
    }

    @dagger.Module
    abstract class Module {

        @dagger.Module
        companion object {

            @LoggedOutScope
            @Provides
            @JvmStatic
            internal fun presenter(): EmptyPresenter {
                return EmptyPresenter()
            }

            @LoggedOutScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                interactor: LoggedOutInteractor,
                containerView: ViewGroup
            ): LoggedOutRouter {
                return LoggedOutRouter(
                    interactor,
                    component,
                    containerView,
                    SignInBuilder(component),
                    SignUpBuilder(component)
                )
            }

            @LoggedOutScope
            @Provides
            @JvmStatic
            internal fun signInListener(interactor: LoggedOutInteractor): SignInInteractor.Listener {
                return interactor.SignInListener()
            }

            @LoggedOutScope
            @Provides
            @JvmStatic
            internal fun signUpListener(interactor: LoggedOutInteractor): SignUpInteractor.Listener {
                return interactor.SignUpListener()
            }
        }
    }


    @LoggedOutScope
    @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<LoggedOutInteractor>, BuilderComponent,
        SignInBuilder.ParentComponent, SignUpBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: LoggedOutInteractor): Builder
            @BindsInstance
            fun containerView(view: ViewGroup): Builder
            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

    }

    interface BuilderComponent {
        fun loggedoutRouter(): LoggedOutRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class LoggedOutScope


    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class LoggedOutInternal
}
