package com.myapplication.finalproject.featureLocation.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class LocationsEntity (
    @SerializedName("info"    ) var info    : InfoLocationPageData?              = InfoLocationPageData(),
    @SerializedName("results" ) var results : ArrayList<LocationData> = arrayListOf()
)
@Entity(tableName = "info_location_page")
data class InfoLocationPageData (
    @PrimaryKey
    val id_info: Int? = null,
    @SerializedName("count" ) var count : Int?    = null,
    @SerializedName("pages" ) var pages : Int?    = null,
    @SerializedName("next"  ) var next  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null

)
@Entity(tableName = "table_location")
data class LocationData (
    @PrimaryKey
    @SerializedName("id"        ) var id        : Int?              = null,
    @SerializedName("name"      ) var name      : String?           = null,
    @SerializedName("type"      ) var type      : String?           = null,
    @SerializedName("dimension" ) var dimension : String?           = null,
    @SerializedName("residents" ) var residents : String?           = null,
    @SerializedName("url"       ) var url       : String?           = null,
    @SerializedName("created"   ) var created   : String?           = null

)