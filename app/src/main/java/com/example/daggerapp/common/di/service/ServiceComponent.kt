package com.example.daggerapp.common.di.service

import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [ServiceModule::class])
interface ServiceComponent {
}