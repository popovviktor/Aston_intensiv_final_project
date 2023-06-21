package com.myapplication.finalproject.featureLocation.data.AppDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapplication.finalproject.featureChararcters.data.AppDb.AppDataBaseCharacters
import com.myapplication.finalproject.featureChararcters.data.dao.CharactersDao
import com.myapplication.finalproject.featureChararcters.data.models.InfoData
import com.myapplication.finalproject.featureChararcters.data.models.LocationDataForCharacterElements
import com.myapplication.finalproject.featureChararcters.data.models.OriginData
import com.myapplication.finalproject.featureLocation.data.dao.LocationsDao
import com.myapplication.finalproject.featureLocation.data.models.InfoLocationPageData
import com.myapplication.finalproject.featureLocation.data.models.LocationData

@Database(version = 1, entities = [InfoLocationPageData::class,LocationData::class], exportSchema = false)
abstract class AppDataBaseLocations: RoomDatabase() {
    abstract fun getLocationsDao(): LocationsDao
    companion object {
        private var db_instance: AppDataBaseLocations? = null
        fun getAppDataBase(context: Context): AppDataBaseLocations{
            if (db_instance ==null){
                db_instance = Room.databaseBuilder<AppDataBaseLocations>(
                    context.applicationContext, AppDataBaseLocations::class.java,"app_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance!!
        }
    }
}