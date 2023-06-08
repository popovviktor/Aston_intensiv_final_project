package com.myapplication.finalproject.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.myapplication.finalproject.data.models.CharactersEntity
import com.myapplication.finalproject.data.models.CharactersTuple
import com.myapplication.finalproject.data.models.InfoEntity

@androidx.room.Dao
interface CharactersDao {
@Insert(entity = CharactersEntity::class)
fun insert(charactersEntity: CharactersEntity)


@Query("SELECT * FROM page_characters")
fun getAllCharacters():List<CharactersTuple>
}