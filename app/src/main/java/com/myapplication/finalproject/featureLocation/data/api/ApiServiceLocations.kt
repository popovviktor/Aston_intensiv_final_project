package com.myapplication.finalproject.featureLocation.data.api


import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceLocations {
    @GET("location/")
    suspend fun getLocations(): Response<LocationsDomain>
    @GET
    suspend fun getLocationsNewPage(@Url url:String): Response<LocationsDomain>

}