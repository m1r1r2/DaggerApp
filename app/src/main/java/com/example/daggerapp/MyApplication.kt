package com.example.daggerapp

import android.app.Application
import com.example.daggerapp.common.di.app.AppModule
import com.example.daggerapp.common.di.app.DaggerAppComponent

class MyApplication :Application() {

    public val  appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
    override fun onCreate() {
        super.onCreate()
    }
}