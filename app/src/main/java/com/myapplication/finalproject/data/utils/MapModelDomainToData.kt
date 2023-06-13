package com.myapplication.finalproject.data.utils

import com.myapplication.finalproject.data.models.*
import com.myapplication.finalproject.domain.models.CharacterDomain
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.models.Info

class MapModelDomainToData {
    fun mapCharactersDomainToData(charactersDomain: CharactersDomain): CharactersEntity {
        val charactersData = CharactersEntity()
        val infoToData = mapInfoToData(charactersDomain.info)
        for (elem in charactersDomain.results!!){
            val characterToData = mapChatacterToData(elem)
            charactersData.results.add(characterToData)
        }
        charactersData.info = infoToData
        return charactersData
    }
    fun mapInfoToData(infoDomain: Info?): InfoData {
        val mapInfoTodata = InfoData()
        mapInfoTodata.count = infoDomain?.count
        mapInfoTodata.pages = infoDomain?.pages
        mapInfoTodata.next = infoDomain?.next
        mapInfoTodata.prev = infoDomain?.prev
        return mapInfoTodata
    }
    fun mapChatacterToData(characterDomain: CharacterDomain): CharacterData {
        val mapOriginToData = OriginData()
        val mapLocationToData = LocationData()
        val mapCharacterToData = CharacterData()
        mapOriginToData.name = characterDomain.origin?.name
        mapOriginToData.url = characterDomain.origin?.url
        mapLocationToData.name = characterDomain.location?.name
        mapLocationToData.url = characterDomain.location?.url
        mapCharacterToData.name = characterDomain.name
        mapCharacterToData.created = characterDomain.created
        mapCharacterToData.episode = arrayStringToArray(characterDomain.episode)   //(characterDomain.episode)
        mapCharacterToData.id = characterDomain.id
        mapCharacterToData.gender = characterDomain.gender
        mapCharacterToData.image = characterDomain.image
        mapCharacterToData.location = mapLocationToData
        mapCharacterToData.origin = mapOriginToData
        mapCharacterToData.species = characterDomain.species
        mapCharacterToData.status = characterDomain.status
        mapCharacterToData.type = characterDomain.type
        mapCharacterToData.url = characterDomain.url
        return mapCharacterToData
    }
    fun arrayStringToArray(arrayString: ArrayList<String>):String{
        val stringBuilder = StringBuilder()
        for (elem in arrayString){
            stringBuilder.append(elem)
            stringBuilder.append(",")
        }
        return stringBuilder.toString()
    }
}