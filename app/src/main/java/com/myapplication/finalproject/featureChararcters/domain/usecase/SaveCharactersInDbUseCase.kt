package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import javax.inject.Inject

class SaveCharactersInDbUseCase@Inject constructor(private val repository: RepositoryCharacters) {
    suspend fun execute(charactersDomain: CharactersDomain){
        repository.saveDataCharactersInDB(charactersDomain =charactersDomain )
    }
}