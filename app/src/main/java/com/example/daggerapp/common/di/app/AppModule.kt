package com.example.daggerapp.common.di.app

import android.app.Application
import com.example.daggerapp.Constants
import com.example.daggerapp.networking.StackOverFlowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application: Application) {

    @Provides
    @AppScope
    fun  retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()}

    @Provides
    fun application()=application


    @Provides
    @AppScope
    fun stackoverflowApi(retrofit: Retrofit) = retrofit.create(StackOverFlowApi::class.java)


}