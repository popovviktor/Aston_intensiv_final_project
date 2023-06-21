package com.myapplication.finalproject.featureChararcters.data.api

import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceCharacters {
    @GET("character/")
    suspend fun getCharacters(): Response<CharactersDomain>
    @GET
    suspend fun getCharactersNewPage(@Url url:String):Response<CharactersDomain>
    @GET
    suspend fun getCharacter(@Url url:String):Response<CharacterDomain>


}