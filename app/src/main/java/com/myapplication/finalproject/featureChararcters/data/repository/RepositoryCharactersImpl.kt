package com.myapplication.finalproject.featureChararcters.data.repository

import com.myapplication.finalproject.featureChararcters.data.api.RemoteDataSourceCharacters
import com.myapplication.finalproject.featureChararcters.data.dao.CharactersDao
import com.myapplication.finalproject.featureChararcters.data.models.CharacterData
import com.myapplication.finalproject.featureChararcters.data.models.CharactersEntity
import com.myapplication.finalproject.featureChararcters.data.utils.MapModelDataToDomainCharacters
import com.myapplication.finalproject.featureChararcters.data.utils.MapModelDomainToDataCharacters
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryCharactersImpl @Inject constructor (private val remoteDataSource: RemoteDataSourceCharacters, private val dao: CharactersDao):
    RepositoryCharacters {
    override suspend fun getDefaultPageCharactersFromWeb(): CharactersDomain? {
        var characters: CharactersDomain? = null
        try {
            characters = remoteDataSource.getCharacters().body()
            dao.clearTableCharacter()
        }catch (ex:java.lang.Exception){
        }

        return characters
    }
    override suspend fun saveDataCharactersInDB(charactersDomain: CharactersDomain){
        val mapper = MapModelDomainToDataCharacters()
        val modelDAta = mapper.mapCharactersDomainToData(charactersDomain)
        withContext(Dispatchers.IO){
         if (modelDAta!=null){
             dao.clearTableCharacterPageINfo()
             dao.insert(modelDAta.info!!)
             for (elem in (modelDAta.results)){
                 dao.insertCharacter(elem)
             }
         }
        }
    }

    override suspend fun getPageCharactersFromDB(): CharactersDomain? {
        val dataInfo = dao.getInfo()
        val data = dao.getAllCharacters()
        val mapper = MapModelDataToDomainCharacters()
        val charactersDomain = mapper.mapToDomain(
            CharactersEntity(info = dataInfo,
        results = data as ArrayList<CharacterData>
        )
        )
        return charactersDomain
    }

    override suspend fun getNewPageCharactersFromWeb(urlNewPage: String): CharactersDomain? {
        var characters: CharactersDomain? = null
        try {
            val newPage = remoteDataSource.getCharactersNewPage(urlNewPage)
            characters = newPage.body()
        }catch (ex:java.lang.Exception){}
        return characters

    }

    override suspend fun getCharacterWebUseCase(urlNewPage: String): CharacterDomain? {
        var character: CharacterDomain? = null
        try {
            val newPage = remoteDataSource.getCharacter(urlNewPage)
            character = newPage.body()
        }catch (ex:java.lang.Exception){}
        return character
    }

    override suspend fun getCharactersWithoutInfoPage(url: String): ArrayList<CharacterDomain>? {
        var characters: ArrayList<CharacterDomain>? = null
        try {
            val loadedCharacters = remoteDataSource.getCharactersWithoutPage(url)
            characters = loadedCharacters.body()
        }catch (ex:java.lang.Exception){}
        return characters
    }

//    override suspend fun getItemCharacter(id: Int): CharacterDomain {
//
//    }
}