package com.myapplication.finalproject.featureChararcters.di

import com.myapplication.finalproject.featureChararcters.domain.repository.Repository
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.SaveCharactersInDbUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun providesGetCharactersUseCase(repository: Repository): GetCharactersUseCase {
        return GetCharactersUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun providesSaveChararctersInDb(repository: Repository): SaveCharactersInDbUseCase {
        return SaveCharactersInDbUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun provideGetCharactersFromDb(repository: Repository): GetCharactersFromDbUseCase {
        return GetCharactersFromDbUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun provideGetCharactersNewPage(repository: Repository): GetCharactersNewPageUseCase {
        return GetCharactersNewPageUseCase(repository = repository)
    }

}