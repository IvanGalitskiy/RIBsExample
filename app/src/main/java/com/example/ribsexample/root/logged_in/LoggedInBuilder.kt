package com.example.ribsexample.root.logged_in

import android.view.ViewGroup
import com.example.ribsexample.entities.UserProfile
import com.example.ribsexample.root.logged_in.main.MainBuilder
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

class LoggedInBuilder(dependency: ParentComponent) :
    Builder<LoggedInRouter, LoggedInBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [LoggedInRouter].
     * @return a new [LoggedInRouter].
     */
    fun build(containerView: ViewGroup, param: LoggedInInputParam): LoggedInRouter {
        val interactor = LoggedInInteractor()
        val component = DaggerLoggedInBuilder_Component.builder()
            .parentComponent(dependency)
            .interactor(interactor)
            .containerView(containerView)
            .inputParams(param)
            .build()

        return component.loggedinRouter()
    }

    data class LoggedInInputParam(
        val userProfile: UserProfile
    )

    interface ParentComponent


    @dagger.Module
    abstract class Module {

        @dagger.Module
        companion object {

            @LoggedInScope
            @Provides
            @JvmStatic
            internal fun presenter(): EmptyPresenter {
                return EmptyPresenter()
            }

            @LoggedInScope
            @Provides
            @JvmStatic
            internal fun router(
                view: ViewGroup,
                component: Component,
                interactor: LoggedInInteractor
            ): LoggedInRouter {
                return LoggedInRouter(interactor, component, view, MainBuilder(component))
            }

            @LoggedInScope
            @Provides
            @JvmStatic
            internal fun currentUser(inputParam: LoggedInInputParam): UserProfile {
                return inputParam.userProfile
            }

        }
    }


    @LoggedInScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<LoggedInInteractor>, BuilderComponent,
        MainBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: LoggedInInteractor): Builder

            @BindsInstance
            fun inputParams(params: LoggedInInputParam): Builder

            @BindsInstance
            fun containerView(view: ViewGroup): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

    }

    interface BuilderComponent {
        fun loggedinRouter(): LoggedInRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class LoggedInScope


    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class LoggedInInternal
}
