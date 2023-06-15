package com.myapplication.finalproject.featureChararcters.domain.repository

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain

interface Repository {
    suspend fun getDataCharacters(): CharactersDomain?
    suspend fun saveDataCharactersInDb(charactersDomain: CharactersDomain)
    suspend fun getDataCharactersFromDB(): CharactersDomain?
    suspend fun getNewPageCharacters(urlNewPage:String): CharactersDomain?
}