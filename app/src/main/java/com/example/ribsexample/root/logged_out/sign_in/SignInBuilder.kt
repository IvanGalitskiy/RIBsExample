package com.example.ribsexample.root.logged_out.sign_in

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ribsexample.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link SignInScope}.
 */
class SignInBuilder(dependency: ParentComponent) :
    ViewBuilder<SignInView, SignInRouter, SignInBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [SignInRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [SignInRouter].
     */
    fun build(parentViewGroup: ViewGroup): SignInRouter {
        val view = createView(parentViewGroup)
        val interactor = SignInInteractor()
        val component = DaggerSignInBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.signinRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SignInView {
        return inflater.inflate(R.layout.rib_sign_in, parentViewGroup, false) as SignInView
    }

    interface ParentComponent {
        fun ribListener(): SignInInteractor.Listener
    }

    @dagger.Module
    abstract class Module {

        @SignInScope
        @Binds
        internal abstract fun presenter(view: SignInPresenterImpl): SignInInteractor.SignInPresenter

        @dagger.Module
        companion object {

            @SignInScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: SignInView,
                interactor: SignInInteractor
            ): SignInRouter {
                return SignInRouter(view, interactor, component)
            }
        }
    }

    @SignInScope
    @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<SignInInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: SignInInteractor): Builder

            @BindsInstance
            fun view(view: SignInView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun signinRouter(): SignInRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class SignInScope

    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class SignInInternal
}
