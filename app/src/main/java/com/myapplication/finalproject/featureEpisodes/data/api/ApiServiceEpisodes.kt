package com.myapplication.finalproject.featureEpisodes.data.api

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceEpisodes {
    @GET("episode/")
    suspend fun getEpisodes(): Response<EpisodesDomain>
    @GET
    suspend fun getEpisodesNewPage(@Url url:String): Response<EpisodesDomain>
    @GET
    suspend fun getEpisode(@Url url:String): Response<EpisodeDomain>

}