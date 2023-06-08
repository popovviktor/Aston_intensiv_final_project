package com.myapplication.finalproject.data.api

import com.myapplication.finalproject.domain.models.CharactersDomain
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character/")
    fun getCharacters():Call<MutableList<CharactersDomain>>
}