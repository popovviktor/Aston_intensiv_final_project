package com.myapplication.finalproject.featureEpisodes.data.api

import com.myapplication.finalproject.featureLocation.data.api.ApiServiceLocations
import javax.inject.Inject

class RemoteDataSourceEpisodes @Inject constructor(private val apiServiceEpisodes: ApiServiceEpisodes) {
    suspend fun getEpisodes() = apiServiceEpisodes.getEpisodes()
    suspend fun getEpisodesNewPage(url:String) = apiServiceEpisodes.getEpisodesNewPage(url)
    suspend fun getEpisodeWeb(url: String) = apiServiceEpisodes.getEpisode(url)
}