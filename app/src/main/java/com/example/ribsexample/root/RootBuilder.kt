package com.example.ribsexample.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ribsexample.root.logged_in.LoggedInBuilder
import com.example.ribsexample.root.logged_out.LoggedOutBuilder
import com.example.ribsexample.root.logged_out.LoggedOutInteractor
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

class RootBuilder(dependency: ParentComponent) :
    ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

    fun build(parentViewGroup: ViewGroup): RootRouter {
        val view = createView(parentViewGroup)
        val interactor = RootInteractor()
        val component = DaggerRootBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.rootRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView? =
        RootView(inflater.context)

    interface ParentComponent

    @dagger.Module
    abstract class Module {

        @RootScope
        @Binds
        internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

        @dagger.Module
        companion object {

            @RootScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: RootView,
                interactor: RootInteractor
            ): RootRouter {
                return RootRouter(
                    view,
                    interactor,
                    component,
                    LoggedOutBuilder(component),
                    LoggedInBuilder(component)
                )
            }

            @RootScope
            @Provides
            @JvmStatic
            internal fun loggedOutListener(interactor: RootInteractor): LoggedOutInteractor.Listener {
                return interactor.LoggedOutListener()
            }
        }
    }

    @RootScope
    @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<RootInteractor>, BuilderComponent,
        LoggedOutBuilder.ParentComponent, LoggedInBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RootInteractor): Builder

            @BindsInstance
            fun view(view: RootView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun rootRouter(): RootRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class RootScope

    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class RootInternal
}
