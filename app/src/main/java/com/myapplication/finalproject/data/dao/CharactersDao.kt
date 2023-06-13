package com.myapplication.finalproject.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.myapplication.finalproject.data.models.CharacterData
import com.myapplication.finalproject.data.models.CharactersEntity
import com.myapplication.finalproject.data.models.InfoData
import retrofit2.http.GET

@androidx.room.Dao
interface CharactersDao {
@Insert(entity = InfoData::class)
fun insert(infoData: InfoData)

@Insert(entity = CharacterData::class)
fun insertCharacter(characterData: CharacterData)
@Query("SELECT * FROM info_character_page")
fun getInfo():InfoData
@Query("SELECT * FROM table_character")
fun getAllCharacters():List<CharacterData>
@Query("DELETE FROM table_character")
fun clearTableCharacter()
@Query("DELETE FROM info_character_page")
fun clearTableCharacterPageINfo()
}