package com.example.daggerapp.common.di.activity


import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerapp.screens.commons.ScreenNavigator
import com.example.daggerapp.screens.commons.ScreenNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {
    @ActivityScope
    @Binds
    abstract  fun screensNavigator(screenNavigatorImpl: ScreenNavigatorImpl): ScreenNavigator

    companion object{

        @Provides
        fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
    }

}