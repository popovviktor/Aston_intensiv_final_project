package com.myapplication.finalproject.data.models

import androidx.room.*
import kotlin.collections.ArrayList

data class CharactersEntity(
    var info    : InfoData?              = InfoData(),
    var results : ArrayList<CharacterData> = arrayListOf()
)
@Entity(tableName = "info_character_page")
data class InfoData (
    @PrimaryKey
    val id_info: Int? = null,
    var count : Int?    = null,
    var pages : Int?    = null,
    var next  : String? = null,
    var prev  : String? = null
)
@Entity(
    foreignKeys = arrayOf( ForeignKey(
        entity = CharacterData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_origin"),
        onDelete = ForeignKey.CASCADE
    ))
)
data class OriginData (
    @PrimaryKey
    @ColumnInfo("id_origin")var id_character:Int? =null,
    @ColumnInfo("name_origin")var name : String? = null,
    @ColumnInfo("url_origin")var url  : String? = null
)
@Entity(
    foreignKeys = arrayOf( ForeignKey(
        entity = CharacterData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_location"),
        onDelete = ForeignKey.CASCADE
    ))
)
data class LocationData (
    @PrimaryKey
    @ColumnInfo("id_location")var id_character:Int? =0,
    @ColumnInfo("name_location")var name : String? = null,
    @ColumnInfo("url_location")var url  : String? = null
)

@Entity(tableName = "table_character")
data class CharacterData(
    var id_page: Int? = null,
    @PrimaryKey(autoGenerate = true)
    var id       : Int?              = null,
    @ColumnInfo("name_character")var name     : String?           = null,
    var status   : String?           = null,
    var species  : String?           = null,
    var type     : String?           = null,
    var gender   : String?           = null,
    @Embedded
    var origin   : OriginData?       = OriginData(),
    @Embedded
    var location : LocationData?     = LocationData(),
    var image    : String?           = null,
    var episode  : String?           = null,
    var url      : String?           = null,
    var created  : String?           = null
){
}


