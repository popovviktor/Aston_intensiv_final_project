package com.myapplication.finalproject.featureLocation.domain.repository

import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain


interface RepositoryLocations {
    suspend fun getDefaultPageLocationsFromWeb(): LocationsDomain?
    suspend fun saveDataLocationsInDB(locationsDomain: LocationsDomain)
    suspend fun getLocationsFromDB(): LocationsDomain?
    suspend fun getNewPageLocationFromWeb(urlNewPage:String): LocationsDomain?
}