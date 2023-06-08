package com.myapplication.finalproject.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getCharacters() = apiService.getCharacters()
}