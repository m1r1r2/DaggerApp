package com.example.daggerapp.common.di.app

import com.example.daggerapp.common.di.activity.ActivityComponent
import com.example.daggerapp.common.di.service.ServiceComponent
import com.example.daggerapp.common.di.service.ServiceModule
import dagger.Component
import dagger.Subcomponent

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponentBuilder(): ActivityComponent.Builder

    fun  newServiceComponent(serviceModule: ServiceModule): ServiceComponent
}