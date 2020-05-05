package com.example.ribsexample.root.logged_out.sign_up

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
 * Builder for the {@link SignUpScope}.
 */
class SignUpBuilder(dependency: ParentComponent) :
    ViewBuilder<SignUpView, SignUpRouter, SignUpBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [SignUpRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [SignUpRouter].
     */
    fun build(parentViewGroup: ViewGroup): SignUpRouter {
        val view = createView(parentViewGroup)
        val interactor = SignUpInteractor()
        val component = DaggerSignUpBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.signupRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SignUpView {
        return inflater.inflate(R.layout.rib_sign_up, parentViewGroup, false) as SignUpView
    }

    interface ParentComponent {
        fun signUpRibListener(): SignUpInteractor.Listener
    }

    @dagger.Module
    abstract class Module {

        @SignUpScope
        @Binds
        internal abstract fun presenter(view: SignUpView): SignUpInteractor.SignUpPresenter

        @dagger.Module
        companion object {

            @SignUpScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: SignUpView,
                interactor: SignUpInteractor
            ): SignUpRouter {
                return SignUpRouter(view, interactor, component)
            }
        }
    }

    @SignUpScope
    @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<SignUpInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: SignUpInteractor): Builder

            @BindsInstance
            fun view(view: SignUpView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun signupRouter(): SignUpRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class SignUpScope

    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.BINARY)
    internal annotation class SignUpInternal
}
