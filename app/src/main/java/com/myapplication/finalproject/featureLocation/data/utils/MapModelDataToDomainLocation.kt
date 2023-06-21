package com.myapplication.finalproject.featureLocation.data.utils

import com.myapplication.finalproject.featureLocation.data.models.LocationsEntity
import com.myapplication.finalproject.featureLocation.domain.models.InfoLocationPageDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain

class MapModelDataToDomainLocation {
    fun mapToDomain(locationsEntity: LocationsEntity):LocationsDomain{
        val locationsDomain = LocationsDomain()
        val infoDomain = mapToDomainInfo(locationsEntity)
        val resultsDomain = mapToDomainResults(locationsEntity)
        locationsDomain.info = infoDomain
        locationsDomain.results = resultsDomain
        return locationsDomain
    }
    fun mapToDomainInfo(locationsEntity: LocationsEntity):InfoLocationPageDomain{
        val infoData = locationsEntity.info
        val infoDomain = InfoLocationPageDomain()
        infoDomain.count = infoData?.count
        infoDomain.next = infoData?.next
        infoDomain.prev = infoData?.prev
        infoDomain.pages = infoData?.pages
        return infoDomain
    }
    fun mapToDomainResults(locationsEntity: LocationsEntity):ArrayList<LocationDomain>{
        val resultsData = locationsEntity.results
        val resultsDomain = ArrayList<LocationDomain>()
        for (elem in resultsData){
            val locationDomain = LocationDomain()
            locationDomain.id = elem.id
            locationDomain.created = elem.created
            locationDomain.name = elem.name
            locationDomain.type = elem.type
            locationDomain.dimension = elem.dimension
            locationDomain.url = elem.url
            locationDomain.residents = elem.residents?.split(",") as ArrayList<String>
            resultsDomain.add(locationDomain)
        }
        return resultsDomain
    }
}