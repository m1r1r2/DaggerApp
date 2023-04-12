package com.example.daggerapp.screens.commons.dialogs

import androidx.fragment.app.DialogFragment
import com.example.daggerapp.screens.commons.activities.BaseActivity

open class BaseDialogs :DialogFragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(

        )
    }

    val injector get() = presentationComponent

}