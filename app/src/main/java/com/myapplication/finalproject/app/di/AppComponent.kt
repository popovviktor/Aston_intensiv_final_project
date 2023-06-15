package com.myapplication.finalproject.app.di

import android.content.Context
import com.example.daggerlecture2023.core.di.ViewModelFactoryModule
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.app.core.base.di.CharactersViewModelModule
import com.myapplication.finalproject.featureLocation.di.NetworkProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class,CharactersViewModelModule::class,AppModule::class,DomainModule::class,DataModule::class])
interface AppComponent: NetworkProvider {
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