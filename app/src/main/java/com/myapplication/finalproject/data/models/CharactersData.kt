package com.myapplication.finalproject.data.models

data class CharactersData(
    var info    : InfoData?              = InfoData(),
    var results : ArrayList<CharacterData> = arrayListOf()
){
    fun toChararctersEntity():CharactersEntity{
        return CharactersEntity(id = 1)
    }
    fun toCharacterEntity():AllEntityForCharacters{
        val episode_names = ArrayList<EdpisodeEntity>()
        val arrayLocationEntity = ArrayList<LocationEntity>()
        val arrayOriginEntity = ArrayList<OriginEntity>()
        val arrayCharacterEntity = ArrayList<CharacterEntity>()
        for (elem in results){
            val elemCharacterEntity = CharacterEntity(
                id_character = 1,
                id_page = 1,
                id = elem.id,
                name = elem.name,
                status = elem.status,
                species = elem.species,
                type = elem.type,
                gender = elem.gender,
                image = elem.image,
                url = elem.url,
                created = elem.created
            )
            for (elem_episode in elem.episode){
                val episode_name =toEpisodeEntity(elem_episode)
                episode_names.add(episode_name)
            }
            val originEntity = toOriginEntity(elem)
            val locationEntity = toLocationEntity(elem)
            arrayLocationEntity.add(locationEntity)
            arrayOriginEntity.add(originEntity)
            arrayCharacterEntity.add(elemCharacterEntity)
        }
        return  AllEntityForCharacters(
            charactersEntity = toChararctersEntity(),
            infoEntity = toInfoEntity(),
            arrayCharacterEntity = arrayCharacterEntity,
            arrayOriginEntity = arrayOriginEntity,
            arrayLocationEntity = arrayLocationEntity,
            arrayEpisodeEntity = episode_names
        )
    }
    fun toInfoEntity():InfoEntity{
        return InfoEntity(id_info = 0L,
            info?.count
            ,info?.pages
            ,info?.next
            ,info?.prev)
    }
    fun toOriginEntity(characterData: CharacterData):OriginEntity{
        return OriginEntity(
            id_character = 1,
            name = characterData.origin?.name,
            url = characterData.origin?.url
        )
    }
    fun toLocationEntity(characterData: CharacterData):LocationEntity{
        return LocationEntity(
            id_character = 1,
            name = characterData.location?.name,
            url = characterData.location?.url
        )
    }
    fun toEpisodeEntity(episode_name: String):EdpisodeEntity{
        return EdpisodeEntity(
            id_character = 1,
            episode_name = episode_name
        )
    }

}
data class InfoData (
    var count : Int?    = null,
    var pages : Int?    = null,
    var next  : String? = null,
    var prev  : String? = null
)
data class OriginData (
    var name : String? = null,
    var url  : String? = null
)
data class LocationData (
    var name : String? = null,
    var url  : String? = null
)
data class CharacterData(
    var id       : Int?              = null,
    var name     : String?           = null,
    var status   : String?           = null,
    var species  : String?           = null,
    var type     : String?           = null,
    var gender   : String?           = null,
    var origin   : OriginData?       = OriginData(),
    var location : LocationData?     = LocationData(),
    var image    : String?           = null,
    var episode  : ArrayList<String> = arrayListOf(),
    var url      : String?           = null,
    var created  : String?           = null
)
