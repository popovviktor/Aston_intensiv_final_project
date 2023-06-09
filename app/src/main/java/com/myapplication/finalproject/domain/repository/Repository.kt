package com.myapplication.finalproject.domain.repository

import com.myapplication.finalproject.domain.models.CharactersDomain

interface Repository {
    suspend fun getDataCharacters():CharactersDomain?
    suspend fun saveDataCharactersInDb(charactersDomain: CharactersDomain)
}