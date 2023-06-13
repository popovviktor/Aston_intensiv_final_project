package com.myapplication.finalproject.data.AppDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myapplication.finalproject.data.dao.CharactersDao
import com.myapplication.finalproject.data.models.*

@Database(version = 1, entities = [CharacterData::class,InfoData::class,OriginData::class,LocationData::class], exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun getCharactersDao():CharactersDao

    companion object {
        private var db_instance:AppDataBase? = null
        fun getAppDataBase(context: Context):AppDataBase{
            if (db_instance==null){
                db_instance = Room.databaseBuilder<AppDataBase>(
                    context.applicationContext,AppDataBase::class.java,"app_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance!!
        }
    }
}