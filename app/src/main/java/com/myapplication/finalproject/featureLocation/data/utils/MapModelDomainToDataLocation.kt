package com.myapplication.finalproject.featureLocation.data.utils

import com.myapplication.finalproject.featureLocation.data.models.InfoLocationPageData
import com.myapplication.finalproject.featureLocation.data.models.LocationsEntity
import com.myapplication.finalproject.featureLocation.data.models.LocationData
import com.myapplication.finalproject.featureLocation.domain.models.InfoLocationPageDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain

class MapModelDomainToDataLocation {
    fun mapToData(locationsDomain: LocationsDomain):LocationsEntity{
        val locationsEntity = LocationsEntity()
        val infoData = mapToDataInfo(locationsDomain.info)
        val resultsData = mapToDataResults(locationsDomain)
        locationsEntity.info = infoData
        locationsEntity.results = resultsData
        return locationsEntity
    }
    fun mapToDataInfo(info:InfoLocationPageDomain):InfoLocationPageData{
        val infoLocationPageData = InfoLocationPageData()
        infoLocationPageData.count = info.count
        infoLocationPageData.pages = info.pages
        infoLocationPageData.next = info.next
        infoLocationPageData.prev = info.prev
        return infoLocationPageData
    }
    fun mapToDataResults(locationsDomain: LocationsDomain):ArrayList<LocationData>{
        val arrayLocationData = ArrayList<LocationData>()
        for (elem in locationsDomain.results){
            val locationData = LocationData()
            locationData.name = elem.name
            locationData.url = elem.url
            locationData.id = elem.id
            locationData.created = elem.created
            locationData.type = elem.type
            locationData.dimension = elem.dimension
            val stringBuilder = StringBuilder()
            for (elemResident in elem.residents){
                stringBuilder.append(elemResident)
                stringBuilder.append(",")
            }
            locationData.residents = stringBuilder.toString()
            arrayLocationData.add(locationData)
        }
        return arrayLocationData
    }
}