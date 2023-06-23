package com.myapplication.finalproject.featureEpisodes.domain.usecase

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import javax.inject.Inject

class GetEpisodeFromWebUseCase @Inject constructor(private val repositoryEpisodes: RepositoryEpisodes) {
    suspend fun execute(url:String): EpisodeDomain?{
        return repositoryEpisodes.getEpisodeFromWeb(url)
    }
}