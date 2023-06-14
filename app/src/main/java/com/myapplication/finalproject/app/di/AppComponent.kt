package com.myapplication.finalproject.app.di

import android.content.Context
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.data.api.ApiService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DomainModule::class,DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }
    companion object{
        fun init(context: Context):AppComponent{
            return DaggerAppComponent.factory().create(context=context)
        }
    }

}