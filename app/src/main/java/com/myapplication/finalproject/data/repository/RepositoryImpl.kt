package com.myapplication.finalproject.data.repository

import com.myapplication.finalproject.data.api.RemoteDataSource
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val remoteDataSource: RemoteDataSource):Repository {
    override suspend fun getDataCharacters(): CharactersDomain? {
        var characters:CharactersDomain? = null
        var mcharacters:MutableList<CharactersDomain>? = null
        try {
            remoteDataSource.getCharacters()
            characters = remoteDataSource.getCharacters().body()
        }catch (ex:java.lang.Exception){
            println(ex.message)
        }

        return characters
    }
    override fun saveDataCharactersInDb(charactersDomain: CharactersDomain){

    }
}