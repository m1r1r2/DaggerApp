package com.example.daggerapp.common.service

import android.app.Service
import com.example.daggerapp.MyApplication
import com.example.daggerapp.common.di.service.ServiceModule

abstract  class BaseService:Service( ){
    private  val appComponent get()=(application as MyApplication).appComponent

    val serviceComponent by lazy {
        appComponent.newServiceComponent(ServiceModule(this))

    }

}