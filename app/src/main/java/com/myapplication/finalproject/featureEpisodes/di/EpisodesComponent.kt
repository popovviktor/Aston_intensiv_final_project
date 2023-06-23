package com.myapplication.finalproject.featureEpisodes.di

import android.content.Context
import com.example.daggerlecture2023.core.di.ViewModelFactoryModule
import com.myapplication.finalproject.ComponentForMissCircularDependency.ComponentOther
import com.myapplication.finalproject.ComponentForMissCircularDependency.ProviderGetCharacter
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.featureEpisodes.presentation.DetailEpisodeFragment
import com.myapplication.finalproject.featureEpisodes.presentation.EpisodesFragment
import com.myapplication.finalproject.featureLocation.di.DaggerLocationsComponent
import com.myapplication.finalproject.featureLocation.di.LocationsComponent
import com.myapplication.finalproject.featureLocation.presentation.DetailLocationFragment
import com.myapplication.finalproject.featureLocation.presentation.LocationsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, EpisodeViewModelModule::class,
    DomainModuleEpisodes::class, DataModuleEpisodes::class],
    dependencies = [ProviderGetCharacter::class])
interface EpisodesComponent :ProviderGetEpisode,DependencyProviderGetCharactersForEpisodes{
    fun inject(mainActivity: MainActivity)
    fun inject(episodesFragment: EpisodesFragment)
    fun inject(detailEpisodeFragment: DetailEpisodeFragment)
    @Component.Factory
    interface Factory{
        fun create(
            providerGetCharacter: ProviderGetCharacter,@BindsInstance context: Context): EpisodesComponent
    }
    companion object{
        fun init(context: Context): EpisodesComponent {
            val providerGetCharactersForEpisodes = ComponentOther.init(context)
            return DaggerEpisodesComponent.factory().create(
                providerGetCharactersForEpisodes,context)
        }
    }
}