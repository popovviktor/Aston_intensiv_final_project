package com.myapplication.finalproject.featureLocation.data.repository

import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureLocation.data.utils.MapModelDataToDomainLocations
import com.myapplication.finalproject.featureLocation.data.utils.*

import com.myapplication.finalproject.featureLocation.data.api.RemoteDataSourceLocations
import com.myapplication.finalproject.featureLocation.data.dao.LocationsDao
import com.myapplication.finalproject.featureLocation.data.models.LocationData
import com.myapplication.finalproject.featureLocation.data.models.LocationsEntity
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
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
            dao.clearTableLocation()
        }catch (ex:java.lang.Exception){
        }

        return locations
    }

    override suspend fun saveDataLocationsInDB(locationsDomain: LocationsDomain) {
        val mapper = MapModelDomainToDataLocations()
        val modelDAta = mapper.mapToData(locationsDomain)
        withContext(Dispatchers.IO){
            if (modelDAta!=null){
                dao.clearTableLocationPageINfo()
                dao.insert(modelDAta.info!!)
                for (elem in (modelDAta.results)){
                    dao.insertLocation(elem)
                }
            }
        }
    }

    override suspend fun getLocationsFromDB(): LocationsDomain? {
        val dataInfo = dao.getInfo()
        val data = dao.getAllLocations()
        val mapper = MapModelDataToDomainLocations()
        val locationsDomain = mapper.mapToDomain(
            LocationsEntity(info = dataInfo,
                results = data as ArrayList<LocationData>
            )
        )
        return locationsDomain
    }

    override suspend fun getNewPageLocationFromWeb(urlNewPage: String): LocationsDomain? {
        var locations: LocationsDomain? = null
        try {
            val newPage = remoteDataSource.getLocationsNewPage(urlNewPage)
            locations = newPage.body()
        }catch (ex:java.lang.Exception){}
        return locations
    }

    override suspend fun getLocationWeb(url: String): LocationDomain? {
        var location: LocationDomain? = null
        try {
            val loadLocation = remoteDataSource.getLocation(url)
            location = loadLocation.body()
        }catch (ex:java.lang.Exception){}
        return location
    }
}