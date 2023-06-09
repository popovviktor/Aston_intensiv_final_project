package com.myapplication.finalproject.featureChararcters.di

import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import com.myapplication.finalproject.featureChararcters.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModuleCharacters {
    @Singleton
    @Provides
    fun providesGetCharactersUseCase(repository: RepositoryCharacters): GetCharactersFromWebUseCase {
        return GetCharactersFromWebUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun providesSaveChararctersInDb(repository: RepositoryCharacters): SaveCharactersInDbUseCase {
        return SaveCharactersInDbUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun provideGetCharactersFromDb(repository: RepositoryCharacters): GetCharactersFromDbUseCase {
        return GetCharactersFromDbUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun provideGetCharactersNewPage(repository: RepositoryCharacters): GetCharactersNewPageUseCase {
        return GetCharactersNewPageUseCase(repository = repository)
    }
    @Singleton
    @Provides
    fun providesGetCharacter(repository: RepositoryCharacters): GetCharacterWebUseCase {
        return GetCharacterWebUseCase(repository = repository)
    }
}