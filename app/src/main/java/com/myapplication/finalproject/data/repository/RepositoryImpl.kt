package com.myapplication.finalproject.data.repository

import androidx.room.Dao
import com.myapplication.finalproject.data.AppDb.AppDataBase
import com.myapplication.finalproject.data.api.RemoteDataSource
import com.myapplication.finalproject.data.dao.CharactersDao
import com.myapplication.finalproject.data.models.CharacterData
import com.myapplication.finalproject.data.models.CharactersEntity
import com.myapplication.finalproject.data.utils.MapModelDataToDomain
import com.myapplication.finalproject.data.utils.MapModelDomainToData
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val remoteDataSource: RemoteDataSource,private val dao:CharactersDao):Repository {
    override suspend fun getDataCharacters(): CharactersDomain? {
        var characters:CharactersDomain? = null
        try {
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
         if (modelDAta!=null){
             dao.clearTableCharacter()
             dao.clearTableCharacterPageINfo()
             dao.insert(modelDAta.info!!)
             for (elem in (modelDAta.results)){
                 dao.insertCharacter(elem)
             }
         }

        }


    }

    override suspend fun getDataCharactersFromDB(): CharactersDomain? {
        val dataInfo = dao.getInfo()
        val data = dao.getAllCharacters()
        val mapper = MapModelDataToDomain()
        val charactersDomain = mapper.mapToDomain(CharactersEntity(info = dataInfo,
        results = data as ArrayList<CharacterData>
        ))
        return charactersDomain
    }

    override suspend fun getNewPageCharacters(urlNewPage: String): CharactersDomain? {
        var characters:CharactersDomain? = null
        try {
            val newPage = remoteDataSource.getCharactersNewPage(urlNewPage)
            characters = newPage.body()
        }catch (ex:java.lang.Exception){}
        println(characters)
        println("newpage")
        return characters

    }
}