package com.myapplication.finalproject.featureEpisodes.domain.usecase

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import javax.inject.Inject

class GetEpisodesNewPageUseCase @Inject constructor(private val repositoryEpisodes: RepositoryEpisodes) {
    suspend fun execute(urlNewPage:String): EpisodesDomain?{
        return repositoryEpisodes.getNewPageEpisodesFromWeb(urlNewPage)
    }
}