package com.myapplication.finalproject.featureChararcters.data.api

import javax.inject.Inject

class RemoteDataSourceCharacters @Inject constructor(private val apiService: ApiServiceCharacters) {
    suspend fun getCharacters() = apiService.getCharacters()
    suspend fun getCharactersNewPage(url:String) = apiService.getCharactersNewPage(url)
}