package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.Repository
import retrofit2.http.Url
import javax.inject.Inject

class GetCharactersNewPageUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(url: String): CharactersDomain?{
        return repository.getNewPageCharacters(url)
    }
}