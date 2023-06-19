package com.myapplication.finalproject.featureChararcters.data.api

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("character/?page=11")
    suspend fun getCharacters(): Response<CharactersDomain>
    @GET
    suspend fun getCharactersNewPage(@Url url:String):Response<CharactersDomain>


}