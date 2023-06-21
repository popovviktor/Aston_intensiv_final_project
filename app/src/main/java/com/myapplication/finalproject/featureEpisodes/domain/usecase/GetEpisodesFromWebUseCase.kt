package com.myapplication.finalproject.featureEpisodes.domain.usecase

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import javax.inject.Inject

class GetEpisodesFromWebUseCase @Inject constructor(private val repositoryEpisodes: RepositoryEpisodes) {
    suspend fun execute(): EpisodesDomain?{
        return repositoryEpisodes.getDefaultPageEpisodesFromWeb()
    }
}