package com.myapplication.finalproject.featureEpisodes.di

import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import com.myapplication.finalproject.featureEpisodes.domain.usecase.*
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
    @Singleton
    @Provides
    fun provideGetEpisodeWev(repository: RepositoryEpisodes):GetEpisodeFromWebUseCase{
        return GetEpisodeFromWebUseCase(repository)
    }
}