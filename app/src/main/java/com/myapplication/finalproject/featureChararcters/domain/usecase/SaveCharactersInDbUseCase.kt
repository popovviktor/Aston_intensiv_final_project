package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.Repository
import javax.inject.Inject

class SaveCharactersInDbUseCase@Inject constructor(private val repository: Repository) {
    suspend fun execute(charactersDomain: CharactersDomain){
        repository.saveDataCharactersInDb(charactersDomain =charactersDomain )
    }
}