package com.myapplication.finalproject.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.myapplication.finalproject.data.models.CharacterData
import com.myapplication.finalproject.data.models.CharactersTuple
import com.myapplication.finalproject.data.models.InfoData

@androidx.room.Dao
interface CharactersDao {
@Insert(entity = InfoData::class)
fun insert(infoData: InfoData)

@Query("SELECT * FROM table_character")
fun getAllCharacters():List<CharacterData>

}