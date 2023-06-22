package com.myapplication.finalproject.featureChararcters.domain.usecase

import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import javax.inject.Inject

class GetCharactersWithoutInfoPageWeb @Inject constructor(private val repository: RepositoryCharacters) {
    suspend fun execute(url:String): ArrayList<CharacterDomain>?{
        return repository.getCharactersWithoutInfoPage(url)
    }
}