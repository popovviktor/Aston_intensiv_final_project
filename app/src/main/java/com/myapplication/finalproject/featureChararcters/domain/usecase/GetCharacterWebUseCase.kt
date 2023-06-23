package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import javax.inject.Inject

class GetCharacterWebUseCase @Inject constructor(private val repository: RepositoryCharacters) {
    suspend fun execute(url: String): CharacterDomain? {
        return repository.getCharacterWebUseCase(url)
    }
}