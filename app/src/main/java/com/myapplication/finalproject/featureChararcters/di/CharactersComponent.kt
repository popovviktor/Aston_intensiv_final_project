package com.myapplication.finalproject.featureChararcters.di

import android.content.Context
import com.example.daggerlecture2023.core.di.ViewModelFactoryModule
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.app.core.di.CharactersViewModelModule
import com.myapplication.finalproject.featureChararcters.presentation.CharactersFragment
import com.myapplication.finalproject.featureChararcters.presentation.DetailCharacterFragment
import com.myapplication.finalproject.featureEpisodes.di.EpisodesComponent
import com.myapplication.finalproject.featureEpisodes.di.ProviderGetEpisode
import com.myapplication.finalproject.featureLocation.di.LocationsComponent
import com.myapplication.finalproject.featureLocation.di.ProviderGetLocation
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, CharactersViewModelModule::class,
            DomainModuleCharacters::class, DataModuleCharacters::class],
        dependencies = [ProviderGetLocation::class, ProviderGetEpisode::class])
interface CharactersComponent :DependenceProviderForCharacter{
    fun inject(mainActivity: MainActivity)
    fun inject(charactersFragment: CharactersFragment)
    fun inject(detailCharacterFragment: DetailCharacterFragment)
    @Component.Factory
    interface Factory{
        fun create(
            providerGetEpisode: ProviderGetEpisode,
            providerGetLocation: ProviderGetLocation
            ,@BindsInstance context: Context): CharactersComponent
    }
    companion object{
        fun init(context: Context): CharactersComponent {
            val getEpisodeProvider = EpisodesComponent.init(context)
            val getLocationProvider = LocationsComponent.init(context)
            return DaggerCharactersComponent.factory().create(getEpisodeProvider,
                getLocationProvider,context)
        }
    }

}