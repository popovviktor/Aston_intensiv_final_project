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
    fun insertLocation(locationData: LocationData)
    @Query("SELECT * FROM info_location_page")
    fun getInfo(): InfoLocationPageData
    @Query("SELECT * FROM table_location")
    fun getAllLocations():List<LocationData>
    @Query("DELETE FROM table_location")
    fun clearTableLocation()
    @Query("DELETE FROM info_location_page")
    fun clearTableLocationPageINfo()
}