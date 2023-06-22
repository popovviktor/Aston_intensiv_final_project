package com.myapplication.finalproject.featureEpisodes.di

import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodeFromWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesNewPageUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesWithoutInfoPageWeb

interface ProviderGetEpisode {
    fun provideGetEpisodeWithoutPageInfo():GetEpisodesWithoutInfoPageWeb
    fun provideGetEpisode():GetEpisodeFromWebUseCase
}