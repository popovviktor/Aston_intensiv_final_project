package com.myapplication.finalproject.featureEpisodes.data.utils

import com.myapplication.finalproject.featureEpisodes.data.models.EpisodesEntity
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.InfoEpisodesDomain

class MapModelDataToDomainEpisodes {
    fun mapToDomain(episodesEntity: EpisodesEntity):EpisodesDomain{
        val info = mapToDomainInfo(episodesEntity)
        val results = mapToDomainResults(episodesEntity)
        return EpisodesDomain(info,results)
    }
    fun mapToDomainInfo(episodesEntity: EpisodesEntity):InfoEpisodesDomain{
        val info = InfoEpisodesDomain()
        info.count = episodesEntity.info?.count
        info.next = episodesEntity.info?.next
        info.prev = episodesEntity.info?.prev
        info.pages = episodesEntity.info?.pages
        return info
    }
    fun mapToDomainResults(episodesEntity: EpisodesEntity):ArrayList<EpisodeDomain>{
        val arrayEpisodes = ArrayList<EpisodeDomain>()
        for (elem in episodesEntity.results){
            val episode = EpisodeDomain()
            episode.episode = elem.episode
            episode.id = elem.id
            episode.airDate = elem.airDate
            episode.name = elem.name
            episode.url = elem.url
            episode.created = elem.created
            arrayEpisodes.add(episode)
        }
        return arrayEpisodes
    }
}