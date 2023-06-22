package com.myapplication.finalproject.featureEpisodes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class EpisodesEntity (
    @SerializedName("info"    ) var info    : InfoEpisodePageData?   = InfoEpisodePageData(),
    @SerializedName("results" ) var results : ArrayList<EpisodeData> = arrayListOf()
)
@Entity(tableName = "info_episode_page")
data class InfoEpisodePageData (
    @PrimaryKey(autoGenerate = true)
    val id_info: Int? = null,
    @SerializedName("count" ) var count : Int?    = null,
    @SerializedName("pages" ) var pages : Int?    = null,
    @SerializedName("next"  ) var next  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null

)
@Entity(tableName = "table_episode")
data class EpisodeData (
    @PrimaryKey
    @SerializedName("id"         ) var id         : Int?              = null,
    @SerializedName("name"       ) var name       : String?           = null,
    @SerializedName("air_date"   ) var airDate    : String?           = null,
    @SerializedName("episode"    ) var episode    : String?           = null,
    @SerializedName("url"        ) var url        : String?           = null,
    @SerializedName("created"    ) var created    : String?           = null

)