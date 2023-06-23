package com.myapplication.finalproject.featureChararcters.data.api

import javax.inject.Inject

class RemoteDataSourceCharacters @Inject constructor(private val apiService: ApiServiceCharacters) {
    suspend fun getCharacters() = apiService.getCharacters()
    suspend fun getCharactersNewPage(url:String) = apiService.getCharactersNewPage(url)
    suspend fun getCharacter(url:String) = apiService.getCharacter(url)
    suspend fun getCharactersWithoutPage(url: String) = apiService.getCharactersWithoutPage(url)
}