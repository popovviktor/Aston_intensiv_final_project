package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import javax.inject.Inject

class GetCharactersNewPageUseCase @Inject constructor(private val repository: RepositoryCharacters) {
    suspend fun execute(url: String): CharactersDomain?{
        return repository.getNewPageCharactersFromWeb(url)
    }
}