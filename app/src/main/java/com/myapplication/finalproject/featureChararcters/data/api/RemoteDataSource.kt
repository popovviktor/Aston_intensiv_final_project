package com.myapplication.finalproject.featureChararcters.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getCharacters() = apiService.getCharacters()
    suspend fun getCharactersNewPage(url:String) = apiService.getCharactersNewPage(url)
}