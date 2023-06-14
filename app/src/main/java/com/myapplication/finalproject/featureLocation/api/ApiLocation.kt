package com.myapplication.finalproject.featureLocation.api

import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.featureLocation.models.Locations
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiLocation {
    @GET
    suspend fun getLocations(@Url url:String): Response<Locations>
    @GET
    suspend fun getLocationsNewPage(@Url url:String): Response<Locations>

}