package com.myapplication.finalproject.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.myapplication.finalproject.domain.models.CharacterDomain
import com.myapplication.finalproject.domain.models.InfoDomain

@Entity(tableName = "page_characters")
data class CharactersEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long
)
@Entity(tableName = "info",
foreignKeys = [ForeignKey(entity = CharactersEntity::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("id_info")
) ])
data class InfoEntity(
    @PrimaryKey
    var id_info:Long? = 0,
    var count : Int?    = null,
    var pages : Int?    = null,
    var next  : String? = null,
    var prev  : String? = null
)

@Entity(tableName = "character",
        foreignKeys = [ForeignKey(
    entity = CharactersEntity::class,
    parentColumns = arrayOf("results_id"),
    childColumns = arrayOf("id_page")
)])
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    var id_character:Long,
    var id_page:Long,
    var id       : Int?              = null,
    var name     : String?           = null,
    var status   : String?           = null,
    var species  : String?           = null,
    var type     : String?           = null,
    var gender   : String?           = null,
    //var origin   : OriginData?       = OriginData(),
    //var location : LocationData?     = LocationData(),
    var image    : String?           = null,
    //var episode  : ArrayList<String> = arrayListOf(),
    var url      : String?           = null,
    var created  : String?           = null
)
@Entity(tableName = "origin",
    foreignKeys = [ForeignKey(
        entity = CharacterEntity::class,
        parentColumns = arrayOf("results_id"),
        childColumns = arrayOf("id_character")
    )])
data class OriginEntity(
    @PrimaryKey
    val id_character:Long,
    var name : String? = null,
    var url  : String? = null
)
@Entity(tableName = "location",
    foreignKeys = [ForeignKey(
        entity = CharacterEntity::class,
        parentColumns = arrayOf("results_id"),
        childColumns = arrayOf("id_character")
    )])
data class LocationEntity(
    @PrimaryKey
    val id_character:Long,
    var name : String? = null,
    var url  : String? = null
)
@Entity(tableName = "episodes",
    foreignKeys = [ForeignKey(
        entity = CharacterEntity::class,
        parentColumns = arrayOf("results_id"),
        childColumns = arrayOf("id_character")
    )])
data class EdpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    var id_character:Long,
    var episode_name:String,
)
data class AllEntityForCharacters(
    var charactersEntity: CharactersEntity,
    var infoEntity: InfoEntity,
    var arrayCharacterEntity :ArrayList<CharacterEntity>,
    var arrayOriginEntity: ArrayList<OriginEntity>,
    var arrayLocationEntity:ArrayList<LocationEntity>,
    var arrayEpisodeEntity: ArrayList<EdpisodeEntity>
)