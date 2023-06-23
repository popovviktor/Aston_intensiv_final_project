package com.myapplication.finalproject.featureEpisodes.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.myapplication.finalproject.featureEpisodes.data.models.EpisodeData
import com.myapplication.finalproject.featureEpisodes.data.models.InfoEpisodePageData
import com.myapplication.finalproject.featureLocation.data.models.InfoLocationPageData
import com.myapplication.finalproject.featureLocation.data.models.LocationData
@androidx.room.Dao
interface EpisodesDao {
    @Insert(entity = InfoEpisodePageData::class)
    fun insert(infoEpisodePageData: InfoEpisodePageData)

    @Insert(entity = EpisodeData::class)
    fun insertEpisode(episodeData: EpisodeData)
    @Query("SELECT * FROM info_episode_page")
    fun getInfo(): InfoEpisodePageData
    @Query("SELECT * FROM table_episode")
    fun getAllEpisodes():List<EpisodeData>
    @Query("DELETE FROM table_episode")
    fun clearTableEpisode()
    @Query("DELETE FROM info_episode_page")
    fun clearTableEpisodePageINfo()
}