package com.myapplication.finalproject.featureEpisodes.domain.repository

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain

interface RepositoryEpisodes {
    suspend fun getDefaultPageEpisodesFromWeb(): EpisodesDomain?
    suspend fun saveDataEpisodesInDB(episodesDomain: EpisodesDomain)
    suspend fun getEpisodesFromDB(): EpisodesDomain?
    suspend fun getNewPageEpisodesFromWeb(urlNewPage:String): EpisodesDomain?
    suspend fun getEpisodeFromWeb(url:String):EpisodeDomain?
    suspend fun getEpisodesWithoutInfoPageWeb(url:String):ArrayList<EpisodeDomain>?
}