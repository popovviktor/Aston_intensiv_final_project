package com.myapplication.finalproject.data.api

import com.myapplication.finalproject.domain.models.CharactersDomain
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character/")
    suspend fun getCharacters(): Response<CharactersDomain>



}