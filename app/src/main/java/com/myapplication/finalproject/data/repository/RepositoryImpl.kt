package com.myapplication.finalproject.data.repository

import com.myapplication.finalproject.data.api.RemoteDataSource
import com.myapplication.finalproject.data.dao.CharactersDao
import com.myapplication.finalproject.data.models.*
import com.myapplication.finalproject.data.utils.MapModelDomainToData
import com.myapplication.finalproject.domain.models.CharacterDomain
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.models.InfoDomain
import com.myapplication.finalproject.domain.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val remoteDataSource: RemoteDataSource,
private val dao_characters:CharactersDao):Repository {
    override suspend fun getDataCharacters(): CharactersDomain? {
        var characters:CharactersDomain? = null
        remoteDataSource.getCharacters().enqueue(object :Callback<MutableList<CharactersDomain>>{
            override fun onResponse(
                call: Call<MutableList<CharactersDomain>>,
                response: Response<MutableList<CharactersDomain>>
            ) {
                characters = response.body() as CharactersDomain
            }

            override fun onFailure(call: Call<MutableList<CharactersDomain>>, t: Throwable) {
                println("ffail chars")
            }

        })
        println(characters)
        return characters
    }

    override fun getDataCharactersDB(): CharactersDomain? {
        TODO("Not yet implemented")
    }

    override fun saveDataCharactersInDb(charactersDomain: CharactersDomain){
        val mapper = MapModelDomainToData()
        val modelData = mapper.mapCharactersDomainToData(charactersDomain)
        //val allEntity = modelData.toCharacterEntity()
        //dao_characters.insert(allEntity.charactersEntity)
        //dao_characters.insertInfo(allEntity.infoEntity)
    }
}