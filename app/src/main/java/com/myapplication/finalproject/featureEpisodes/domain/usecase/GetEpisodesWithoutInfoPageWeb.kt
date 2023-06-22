package com.myapplication.finalproject.featureEpisodes.domain.usecase

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import javax.inject.Inject

class GetEpisodesWithoutInfoPageWeb @Inject constructor(private val repositoryEpisodes: RepositoryEpisodes) {
    suspend fun execute(urlNewPage:String): ArrayList<EpisodeDomain>?{
        return repositoryEpisodes.getEpisodesWithoutInfoPageWeb(urlNewPage)
    }
}