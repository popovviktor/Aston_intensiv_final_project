package com.myapplication.finalproject.featureEpisodes.di

import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodeFromWebUseCase

interface ProviderGetEpisode {
    fun provideGetEpisode():GetEpisodeFromWebUseCase
}