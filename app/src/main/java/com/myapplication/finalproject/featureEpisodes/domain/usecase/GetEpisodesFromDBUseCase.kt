package com.myapplication.finalproject.featureEpisodes.domain.usecase

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import javax.inject.Inject

class GetEpisodesFromDBUseCase @Inject constructor(private val repositoryEpisodes: RepositoryEpisodes) {
    suspend fun execute(): EpisodesDomain?{
        return repositoryEpisodes.getEpisodesFromDB()
    }
}