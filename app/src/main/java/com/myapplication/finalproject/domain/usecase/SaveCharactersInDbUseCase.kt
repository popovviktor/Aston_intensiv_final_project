package com.myapplication.finalproject.domain.usecase

import com.myapplication.finalproject.domain.models.CharacterDomain
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.repository.Repository
import javax.inject.Inject

class SaveCharactersInDbUseCase@Inject constructor(private val repository: Repository) {
    fun execute(charactersDomain: CharactersDomain){
        repository.saveDataCharactersInDb(charactersDomain =charactersDomain )
    }
}