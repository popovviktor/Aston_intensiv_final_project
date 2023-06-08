package com.myapplication.finalproject.data.repository

import android.util.Log
import com.myapplication.finalproject.data.api.RemoteDataSource
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val remoteDataSource: RemoteDataSource):Repository {
    override suspend fun getDataCharacters(): CharactersDomain? {
        var characters:CharactersDomain? = null
        var mcharacters:MutableList<CharactersDomain>? = null
        try {
            remoteDataSource.getCharacters()
            println("DCTTTT GOOOOOD")
        }catch (ex:java.lang.Exception){
            println(ex.message)
        }
        println(characters)
        return characters
    }
    override fun saveDataCharactersInDb(charactersDomain: CharactersDomain){

    }
}