package com.myapplication.finalproject.app.di

import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.data.api.ApiService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DomainModule::class,DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}