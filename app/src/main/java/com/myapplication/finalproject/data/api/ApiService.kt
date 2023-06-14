package com.myapplication.finalproject.data.api

import com.myapplication.finalproject.domain.models.CharactersDomain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("character/")
    suspend fun getCharacters(): Response<CharactersDomain>
    @GET
    suspend fun getCharactersNewPage(@Url url:String):Response<CharactersDomain>


}