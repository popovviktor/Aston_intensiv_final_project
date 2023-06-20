package com.myapplication.finalproject.featureLocation.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.myapplication.finalproject.featureChararcters.data.models.CharacterData
import com.myapplication.finalproject.featureChararcters.data.models.InfoData

@androidx.room.Dao
interface LocationsDao {
    @Insert(entity = InfoData::class)
    fun insert(infoData: InfoData)

    @Insert(entity = CharacterData::class)
    fun insertCharacter(characterData: CharacterData)
    @Query("SELECT * FROM info_character_page")
    fun getInfo(): InfoData
    @Query("SELECT * FROM table_character")
    fun getAllCharacters():List<CharacterData>
    @Query("DELETE FROM table_character")
    fun clearTableCharacter()
    @Query("DELETE FROM info_character_page")
    fun clearTableCharacterPageINfo()
}