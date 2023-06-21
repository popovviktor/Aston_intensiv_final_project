package com.myapplication.finalproject.featureLocation.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.myapplication.finalproject.featureChararcters.data.models.CharacterData
import com.myapplication.finalproject.featureChararcters.data.models.InfoData
import com.myapplication.finalproject.featureLocation.data.models.InfoLocationPageData
import com.myapplication.finalproject.featureLocation.data.models.LocationData

@androidx.room.Dao
interface LocationsDao {
    @Insert(entity = InfoLocationPageData::class)
    fun insert(infoLocationPageData: InfoLocationPageData)

    @Insert(entity = LocationData::class)
    fun insertCharacter(locationData: LocationData)
    @Query("SELECT * FROM info_location_page")
    fun getInfo(): InfoLocationPageData
    @Query("SELECT * FROM table_location")
    fun getAllCharacters():List<LocationData>
    @Query("DELETE FROM table_location")
    fun clearTableCharacter()
    @Query("DELETE FROM info_location_page")
    fun clearTableCharacterPageINfo()
}