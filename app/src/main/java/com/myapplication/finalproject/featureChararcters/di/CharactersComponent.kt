package com.myapplication.finalproject.featureChararcters.di

import android.content.Context
import com.example.daggerlecture2023.core.di.ViewModelFactoryModule
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.app.core.di.CharactersViewModelModule
import com.myapplication.finalproject.featureChararcters.presentation.CharactersFragment
import com.myapplication.finalproject.featureChararcters.presentation.DetailCharacterFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, CharactersViewModelModule::class,
            DomainModuleCharacters::class, DataModuleCharacters::class])
interface CharactersComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(charactersFragment: CharactersFragment)
    fun inject(detailCharacterFragment: DetailCharacterFragment)
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): CharactersComponent
    }
    companion object{
        fun init(context: Context): CharactersComponent {
            return DaggerCharactersComponent.factory().create(context)
        }
    }

}