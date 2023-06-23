package com.myapplication.finalproject.featureEpisodes.domain.models

import com.google.gson.annotations.SerializedName

data class EpisodesDomain(
    @SerializedName("info"    ) var info    : InfoEpisodesDomain?              = InfoEpisodesDomain(),
    @SerializedName("results" ) var results : ArrayList<EpisodeDomain> = arrayListOf()
)
data class InfoEpisodesDomain(
    @SerializedName("count" ) var count : Int?    = null,
    @SerializedName("pages" ) var pages : Int?    = null,
    @SerializedName("next"  ) var next  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null
)
data class EpisodeDomain(
    @SerializedName("id"         ) var id         : Int?              = null,
    @SerializedName("name"       ) var name       : String?           = null,
    @SerializedName("air_date"   ) var airDate    : String?           = null,
    @SerializedName("episode"    ) var episode    : String?           = null,
    @SerializedName("characters" ) var characters : ArrayList<String> = arrayListOf(),
    @SerializedName("url"        ) var url        : String?           = null,
    @SerializedName("created"    ) var created    : String?           = null

)