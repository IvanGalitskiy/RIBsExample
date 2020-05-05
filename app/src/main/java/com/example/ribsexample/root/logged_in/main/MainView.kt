package com.example.ribsexample.root.logged_in.main

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ribsexample.entities.UserProfile
import kotlinx.android.synthetic.main.rib_main.view.*

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), MainInteractor.MainPresenter {
    override fun displayUser(user: UserProfile) {
        vHeaderText.text = if (user.isNew) "Greetings" else "Welcome back"
        vUserName.text = user.userName
    }
}
