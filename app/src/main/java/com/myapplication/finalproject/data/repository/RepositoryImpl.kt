package com.myapplication.finalproject.data.repository

import androidx.room.Dao
import com.myapplication.finalproject.data.AppDb.AppDataBase
import com.myapplication.finalproject.data.api.RemoteDataSource
import com.myapplication.finalproject.data.dao.CharactersDao
import com.myapplication.finalproject.data.utils.MapModelDomainToData
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val remoteDataSource: RemoteDataSource,private val dao:CharactersDao):Repository {
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
    override suspend fun saveDataCharactersInDb(charactersDomain: CharactersDomain){
        val mapper = MapModelDomainToData()
        val modelDAta = mapper.mapCharactersDomainToData(charactersDomain)
        withContext(Dispatchers.IO){
         //dao.insert(modelDAta.toCharactersEntity())
        }


    }
}