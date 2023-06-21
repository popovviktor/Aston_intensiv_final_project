package com.myapplication.finalproject.featureEpisodes.di

import androidx.lifecycle.ViewModel
import com.example.daggerlecture2023.core.di.ViewModelKey
import com.myapplication.finalproject.featureEpisodes.presentation.DetailEpisodeViewModel
import com.myapplication.finalproject.featureEpisodes.presentation.EpisodesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EpisodeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    fun bindLocationViewModel(episodesViewModel: EpisodesViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(DetailEpisodeViewModel::class)
    fun bindDetailCharacterViewModel(detailEpisodeViewModel: DetailEpisodeViewModel): ViewModel

}