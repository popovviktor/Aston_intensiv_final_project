package com.myapplication.finalproject.featureChararcters.data.AppDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapplication.finalproject.featureChararcters.data.dao.CharactersDao
import com.myapplication.finalproject.featureChararcters.data.models.CharacterData
import com.myapplication.finalproject.featureChararcters.data.models.InfoData
import com.myapplication.finalproject.featureChararcters.data.models.LocationDataForCharacterElements
import com.myapplication.finalproject.featureChararcters.data.models.OriginData

@Database(version = 1, entities = [CharacterData::class, InfoData::class, OriginData::class, LocationDataForCharacterElements::class], exportSchema = false)
abstract class AppDataBaseCharacters:RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao

    companion object {
        private var db_instance: AppDataBaseCharacters? = null
        fun getAppDataBase(context: Context): AppDataBaseCharacters {
            if (db_instance ==null){
                db_instance = Room.databaseBuilder<AppDataBaseCharacters>(
                    context.applicationContext, AppDataBaseCharacters::class.java,"app_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance!!
        }
    }
}