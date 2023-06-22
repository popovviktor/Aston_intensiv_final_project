package com.myapplication.finalproject.ComponentForMissCircularDependency

import android.app.Activity
import android.content.Context
import com.example.daggerlecture2023.core.di.ViewModelFactoryModule
import com.myapplication.finalproject.app.core.di.CharactersViewModelModule
import com.myapplication.finalproject.featureChararcters.di.DataModuleCharacters
import com.myapplication.finalproject.featureChararcters.di.DomainModuleCharacters
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModuleCharacters::class, DataModuleCharacters::class])
interface ComponentOther :ProviderGetCharacter{
    fun inject(activity: Activity)
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context
        ): ComponentOther
    }

    companion object{
        fun init(context: Context): ComponentOther {
            return DaggerComponentOther.factory().create(context)
        }
    }
}