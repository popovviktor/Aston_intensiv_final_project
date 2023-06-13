package com.myapplication.finalproject.app.di

import com.myapplication.finalproject.domain.repository.Repository
import com.myapplication.finalproject.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.domain.usecase.SaveCharactersInDbUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun providesGetCharactersUseCase(repository: Repository):GetCharactersUseCase{
        return GetCharactersUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun providesSaveChararctersInDb(repository: Repository):SaveCharactersInDbUseCase{
        return SaveCharactersInDbUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun provideGetCharactersFromDb(repository: Repository):GetCharactersFromDbUseCase{
        return GetCharactersFromDbUseCase(repository = repository)
    }

}