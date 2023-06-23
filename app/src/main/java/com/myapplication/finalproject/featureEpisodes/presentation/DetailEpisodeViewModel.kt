package com.myapplication.finalproject.featureEpisodes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersWithoutInfoPageWeb
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailEpisodeViewModel@Inject constructor(
    private val getEpisode:GetEpisodeFromWebUseCase,
    private val getCharacterUseCase: GetCharacterWebUseCase,
    private val getCharactersWithoutInfoPageWeb: GetCharactersWithoutInfoPageWeb
):ViewModel() {
    private val _characters = MutableLiveData<ArrayList<CharacterDomain>>()
    val characters:LiveData<ArrayList<CharacterDomain>>
        get() = _characters
    private val _episode = MutableLiveData<EpisodeDomain>()
    val episode: LiveData<EpisodeDomain>
        get() = _episode
    fun startLoadDetailEpisode(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            getEpisode.execute(url).let {
                if (it!=null){
                    _episode.postValue(it)
                }
            }
        }}
    fun startLoadCharacters(url: String){
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersWithoutInfoPageWeb.execute(url).let {
                if (it!=null){
                    _characters.postValue(it)
                }else{
                    startLoadCharacter(url)
                }
            }
        }
    }
    suspend fun startLoadCharacter(url: String){
        getCharacterUseCase.execute(url).let {
            if (it!=null){
                val arrayCharacters = ArrayList<CharacterDomain>()
                arrayCharacters.add(it)
                _characters.postValue(arrayCharacters)
            }
        }
    }
}