package com.myapplication.finalproject.featureLocation.data.repository

import com.myapplication.finalproject.featureChararcters.data.api.RemoteDataSourceCharacters
import com.myapplication.finalproject.featureChararcters.data.dao.CharactersDao
import com.myapplication.finalproject.featureChararcters.data.utils.MapModelDomainToDataCharacters
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureLocation.data.api.RemoteDataSourceLocations
import com.myapplication.finalproject.featureLocation.data.dao.LocationsDao
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryLocationsImpl @Inject constructor (private val remoteDataSource: RemoteDataSourceLocations, private val dao: LocationsDao):RepositoryLocations {
    override suspend fun getDefaultPageLocationsFromWeb(): LocationsDomain? {
        var locations: LocationsDomain? = null
        try {
            locations = remoteDataSource.getLocations().body()
            dao.clearTableCharacter()
        }catch (ex:java.lang.Exception){
        }

        return locations
    }

    override suspend fun saveDataLocationsInDB(locationsDomain: LocationsDomain) {

    }

    override suspend fun getLocationsFromDB(): LocationsDomain? {
        TODO("Not yet implemented")
    }

    override suspend fun getNewPageLocationFromWeb(urlNewPage: String): LocationsDomain? {
        TODO("Not yet implemented")
    }
}