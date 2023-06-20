package com.myapplication.finalproject.featureChararcters.data.utils


import com.myapplication.finalproject.featureChararcters.data.models.CharacterData
import com.myapplication.finalproject.featureChararcters.data.models.CharactersEntity
import com.myapplication.finalproject.featureChararcters.data.models.LocationData
import com.myapplication.finalproject.featureChararcters.data.models.OriginData
import com.myapplication.finalproject.featureChararcters.domain.models.*

class MapModelDataToDomainCharacters {
    fun mapToDomain(charactersData: CharactersEntity): CharactersDomain {
        val charctersDomain = ArrayList<CharacterDomain>()
        for (elem in charactersData.results){
            charctersDomain.add(mapToDomainCharacter(elem))
        }
        return CharactersDomain(
            info = mapToDomainInfoPage(charactersData),
            results = charctersDomain
        )
    }
    fun mapToDomainInfoPage(charactersData: CharactersEntity): Info {
        return Info(
            count = charactersData.info?.count,
            pages = charactersData.info?.pages,
            prev = charactersData.info?.prev,
            next = charactersData.info?.next
        )
    }
    fun mapToDomainCharacter(characterData: CharacterData): CharacterDomain {
        val array = characterData.episode?.split(",")
        return CharacterDomain(
            id = characterData.id,
            name = characterData.name,
            status = characterData.status,
            species = characterData.species,
            type = characterData.type,
            gender = characterData.gender,
            origin = characterData.origin?.let { mapToDomainOrigin(it) },
            location = characterData.location?.let { mapToDomainLocation(it) },
            image = characterData.image,
            episode = array as ArrayList<String>,
            url =characterData.url,
            created = characterData.created

        )
    }
    fun mapToDomainOrigin(originData: OriginData): Origin {
        return Origin(
            name = originData.name,
            url = originData.url
        )
    }
    fun mapToDomainLocation(locationData: LocationData): Location {
        return Location(
            name = locationData.name,
            url = locationData.url
        )
    }
}