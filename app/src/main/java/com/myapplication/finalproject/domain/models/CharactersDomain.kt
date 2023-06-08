package com.myapplication.finalproject.domain.models

import com.google.gson.annotations.SerializedName

data class CharactersDomain (
    @SerializedName("info"    ) var info    : InfoDomain?              = InfoDomain(),
    @SerializedName("results" ) var results : ArrayList<CharacterDomain> = arrayListOf()
)
data class InfoDomain (
    @SerializedName("count" ) var count : Int?    = null,
    @SerializedName("pages" ) var pages : Int?    = null,
    @SerializedName("next"  ) var next  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null
)
data class OriginDomain (
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)
data class LocationDomain (
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)
data class CharacterDomain(
    @SerializedName("id"       ) var id       : Int?              = null,
    @SerializedName("name"     ) var name     : String?           = null,
    @SerializedName("status"   ) var status   : String?           = null,
    @SerializedName("species"  ) var species  : String?           = null,
    @SerializedName("type"     ) var type     : String?           = null,
    @SerializedName("gender"   ) var gender   : String?           = null,
    @SerializedName("origin"   ) var origin   : OriginDomain?           = OriginDomain(),
    @SerializedName("location" ) var location : LocationDomain?         = LocationDomain(),
    @SerializedName("image"    ) var image    : String?           = null,
    @SerializedName("episode"  ) var episode  : ArrayList<String> = arrayListOf(),
    @SerializedName("url"      ) var url      : String?           = null,
    @SerializedName("created"  ) var created  : String?           = null
)