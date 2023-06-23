package com.myapplication.finalproject.featureEpisodes.data.AppDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapplication.finalproject.featureEpisodes.data.dao.EpisodesDao
import com.myapplication.finalproject.featureEpisodes.data.models.EpisodeData
import com.myapplication.finalproject.featureEpisodes.data.models.InfoEpisodePageData
import com.myapplication.finalproject.featureLocation.data.AppDb.AppDataBaseLocations
import com.myapplication.finalproject.featureLocation.data.dao.LocationsDao

@Database(version = 1, entities = [InfoEpisodePageData::class, EpisodeData::class], exportSchema = false)
abstract class AppDataBaseEpisodes: RoomDatabase()  {
    abstract fun getEpisodesDao(): EpisodesDao
    companion object {
        private var db_instance: AppDataBaseEpisodes? = null
        fun getAppDataBase(context: Context): AppDataBaseEpisodes {
            if (db_instance ==null){
                db_instance = Room.databaseBuilder<AppDataBaseEpisodes>(
                    context.applicationContext, AppDataBaseEpisodes::class.java,"app_db3"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance!!
        }
    }
}