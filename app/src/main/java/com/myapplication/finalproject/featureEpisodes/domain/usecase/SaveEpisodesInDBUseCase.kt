package com.myapplication.finalproject.featureEpisodes.domain.usecase

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import javax.inject.Inject

class SaveEpisodesInDBUseCase @Inject constructor(private val repositoryEpisodes: RepositoryEpisodes) {
    suspend fun execute(episodesDomain: EpisodesDomain){
        repositoryEpisodes.saveDataEpisodesInDB(episodesDomain)
    }
}