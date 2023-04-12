package com.example.daggerapp.screens.commons.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.daggerapp.MyApplication

open class BaseActivity : AppCompatActivity() {
    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponentBuilder().activity(this).build()

    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()

    }


    protected val injector get() =presentationComponent


}