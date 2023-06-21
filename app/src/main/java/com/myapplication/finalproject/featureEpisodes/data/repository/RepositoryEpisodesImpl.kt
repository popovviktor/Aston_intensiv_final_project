package com.myapplication.finalproject.featureEpisodes.data.repository

import com.myapplication.finalproject.featureEpisodes.data.api.RemoteDataSourceEpisodes
import com.myapplication.finalproject.featureEpisodes.data.dao.EpisodesDao
import com.myapplication.finalproject.featureEpisodes.data.models.EpisodeData
import com.myapplication.finalproject.featureEpisodes.data.models.EpisodesEntity
import com.myapplication.finalproject.featureEpisodes.data.utils.MapModelDataToDomainEpisodes
import com.myapplication.finalproject.featureEpisodes.data.utils.MapModelDomainToDataEpisodes
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryEpisodesImpl @Inject constructor (private val remoteDataSource: RemoteDataSourceEpisodes, private val dao: EpisodesDao):
    RepositoryEpisodes {
    override suspend fun getDefaultPageEpisodesFromWeb(): EpisodesDomain? {
        var episodes: EpisodesDomain? = null
        try {
            episodes = remoteDataSource.getEpisodes().body()
            dao.clearTableEpisode()
        }catch (ex:java.lang.Exception){
        }

        return episodes
    }

    override suspend fun saveDataEpisodesInDB(episodesDomain: EpisodesDomain) {
        val mapper = MapModelDomainToDataEpisodes()
        val modelDAta = mapper.mapToData(episodesDomain)
        withContext(Dispatchers.IO){
            if (modelDAta!=null){
                dao.clearTableEpisodePageINfo()
                dao.insert(modelDAta.info!!)
                for (elem in (modelDAta.results)){
                    dao.insertEpisode(elem)
                }
            }
        }
    }

    override suspend fun getEpisodesFromDB(): EpisodesDomain? {
        val dataInfo = dao.getInfo()
        val data = dao.getAllEpisodes()
        val mapper = MapModelDataToDomainEpisodes()
        val episodesDomain = mapper.mapToDomain(
            EpisodesEntity(info = dataInfo,
                results = data as ArrayList<EpisodeData>
            )
        )
        return episodesDomain
    }

    override suspend fun getNewPageEpisodesFromWeb(urlNewPage: String): EpisodesDomain? {
        var episodes: EpisodesDomain? = null
        try {
            val newPage = remoteDataSource.getEpisodesNewPage(urlNewPage)
            episodes = newPage.body()
        }catch (ex:java.lang.Exception){}
        return episodes
    }
}