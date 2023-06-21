package com.myapplication.finalproject.featureEpisodes.di

import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromDBUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesNewPageUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.SaveEpisodesInDBUseCase
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsFromDBUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsFromWebUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsNewPageUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.SaveLocationsInDBUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModuleEpisodes {
    @Singleton
    @Provides
    fun providesGetEpisodesUseCase(repository: RepositoryEpisodes): GetEpisodesFromWebUseCase {
        return GetEpisodesFromWebUseCase(repository)
    }
    @Singleton
    @Provides
    fun providesSaveEpisodesInDb(repository: RepositoryEpisodes): SaveEpisodesInDBUseCase {
        return SaveEpisodesInDBUseCase(repository)
    }
    @Singleton
    @Provides
    fun provideGetEpisodesFromDb(repository: RepositoryEpisodes): GetEpisodesFromDBUseCase {
        return GetEpisodesFromDBUseCase(repository)
    }
    @Singleton
    @Provides
    fun provideGetEpisodesNewPage(repository: RepositoryEpisodes): GetEpisodesNewPageUseCase {
        return GetEpisodesNewPageUseCase(repository)
    }
}