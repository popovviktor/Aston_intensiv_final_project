package com.myapplication.finalproject.domain.usecase

import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.repository.Repository
import javax.inject.Inject

class SaveCharactersInDbUseCase@Inject constructor(private val repository: Repository) {
    suspend fun execute(charactersDomain: CharactersDomain){
        repository.saveDataCharactersInDb(charactersDomain =charactersDomain )
    }
}