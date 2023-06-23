package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import javax.inject.Inject

class GetCharactersFromWebUseCase @Inject constructor(private val repository: RepositoryCharacters) {
    suspend fun execute(): CharactersDomain?{
        return repository.getDefaultPageCharactersFromWeb()
    }
}