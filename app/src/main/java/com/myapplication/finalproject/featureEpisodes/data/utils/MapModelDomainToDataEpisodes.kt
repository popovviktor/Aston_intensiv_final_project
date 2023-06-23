package com.myapplication.finalproject.featureEpisodes.data.utils

import com.myapplication.finalproject.featureEpisodes.data.models.EpisodeData
import com.myapplication.finalproject.featureEpisodes.data.models.EpisodesEntity
import com.myapplication.finalproject.featureEpisodes.data.models.InfoEpisodePageData
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain

class MapModelDomainToDataEpisodes {
    fun mapToData(episodesDomain: EpisodesDomain):EpisodesEntity{
        val info = mapToDataInfo(episodesDomain)
        val results = mapToDataResults(episodesDomain)
        return EpisodesEntity(info,results)

    }
    fun mapToDataInfo(episodesDomain: EpisodesDomain):InfoEpisodePageData{
        val info = InfoEpisodePageData()
        info.count = episodesDomain.info?.count
        info.next = episodesDomain.info?.next
        info.prev = episodesDomain.info?.prev
        info.pages = episodesDomain.info?.pages
        return info
    }
    fun mapToDataResults(episodesDomain: EpisodesDomain):ArrayList<EpisodeData>{
        val episodesData = ArrayList<EpisodeData>()
        for (elem in episodesDomain.results){
            val episode = EpisodeData()
            episode.id = elem.id
            episode.created = elem.created
            episode.name = elem.name
            episode.url = elem.url
            episode.airDate = elem.airDate
            episode.episode = elem.episode
            episodesData.add(episode)
        }
        return episodesData

    }
}