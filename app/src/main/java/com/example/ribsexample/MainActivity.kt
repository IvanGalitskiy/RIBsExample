package com.example.ribsexample

import android.view.ViewGroup
import com.example.ribsexample.root.RootBuilder
import com.example.ribsexample.root.RootInteractor
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class MainActivity : RibActivity() {

    private lateinit var rootInteractor: RootInteractor


    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
        val rootBuilder = RootBuilder(object : RootBuilder.ParentComponent {})
        val router = rootBuilder.build(parentViewGroup)
        rootInteractor = router.interactor
        return router
    }
}
