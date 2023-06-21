package com.myapplication.finalproject.featureLocation.di

import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.SaveCharactersInDbUseCase
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import com.myapplication.finalproject.featureLocation.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModuleLocations {
    @Singleton
    @Provides
    fun providesGetLocationsUseCase(repository: RepositoryLocations): GetLocationsFromWebUseCase {
        return GetLocationsFromWebUseCase(repository)
    }
    @Singleton
    @Provides
    fun providesSaveLocationsInDb(repository: RepositoryLocations): SaveLocationsInDBUseCase {
        return SaveLocationsInDBUseCase(repository)
    }
    @Singleton
    @Provides
    fun provideGetLocationsFromDb(repository: RepositoryLocations): GetLocationsFromDBUseCase {
        return GetLocationsFromDBUseCase(repository)
    }
    @Singleton
    @Provides
    fun provideGetLocationsNewPage(repository: RepositoryLocations): GetLocationsNewPageUseCase {
        return GetLocationsNewPageUseCase(repository)
    }
    @Singleton
    @Provides
    fun providesGetLocationWeb(repository: RepositoryLocations):GetLocationWebUseCase{
        return GetLocationWebUseCase(repository)
    }
}