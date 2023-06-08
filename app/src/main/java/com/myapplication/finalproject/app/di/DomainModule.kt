package com.myapplication.finalproject.app.di

import com.myapplication.finalproject.domain.repository.Repository
import com.myapplication.finalproject.domain.usecase.GetCharactersUseCase
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

}