package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.Repository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(): CharactersDomain?{
        return repository.getDefaultPageCharactersFromWeb()
    }
}