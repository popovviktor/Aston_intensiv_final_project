package com.myapplication.finalproject.featureLocation.data.api

import javax.inject.Inject

class RemoteDataSourceLocations @Inject constructor(private val apiServiceLocations: ApiServiceLocations) {
    suspend fun getLocations() = apiServiceLocations.getLocations()
    suspend fun getLocationsNewPage(url:String) = apiServiceLocations.getLocationsNewPage(url)

}