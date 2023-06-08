package com.myapplication.finalproject.domain.usecase

import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.repository.Repository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute():CharactersDomain?{
        return repository.getDataCharacters()
    }
}