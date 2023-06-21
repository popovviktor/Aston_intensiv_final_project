package com.myapplication.finalproject.featureChararcters.domain.repository

import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain

interface RepositoryCharacters {
    suspend fun getDefaultPageCharactersFromWeb(): CharactersDomain?
    suspend fun saveDataCharactersInDB(charactersDomain: CharactersDomain)
    suspend fun getPageCharactersFromDB(): CharactersDomain?
    suspend fun getNewPageCharactersFromWeb(urlNewPage:String): CharactersDomain?
    suspend fun getCharacterWebUseCase(urlNewPage:String): CharacterDomain?
}