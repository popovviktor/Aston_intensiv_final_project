package com.myapplication.finalproject.featureChararcters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.usecase.*
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodeFromWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesNewPageUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesWithoutInfoPageWeb
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationWebUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailCharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterWebUseCase,
    private val getEpisodes:GetEpisodesWithoutInfoPageWeb,
    private val getEpisode:GetEpisodeFromWebUseCase,
    private val getLocation:GetLocationWebUseCase
    ):ViewModel() {
        private val _episodes = MutableLiveData<ArrayList<EpisodeDomain>>()
        val episodes:LiveData<ArrayList<EpisodeDomain>>
            get() = _episodes
        private val _origin = MutableLiveData<LocationDomain>()
        val origin:LiveData<LocationDomain>
            get() = _origin
        private val _location = MutableLiveData<LocationDomain>()
        val location:LiveData<LocationDomain>
            get() = _location
        private val _character = MutableLiveData<CharacterDomain>()
        val character:LiveData<CharacterDomain>
            get() = _character
    fun startLoadDetailCharacter(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterUseCase.execute(url).let {
                if (it!=null){
                    _character.postValue(it)
                }
            }
        }}
    fun startLoadEpisodes(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            getEpisodes.execute(url).let {
                if (it!=null){
                    _episodes.postValue(it)
                }else{
                    startLoadEpisode(url)
                }
            }
        }
    }
    suspend fun startLoadEpisode(url: String){
        getEpisode.execute(url).let {
            if (it!=null){
                val arrayEpisodes = ArrayList<EpisodeDomain>()
                arrayEpisodes.add(it)
                _episodes.postValue(arrayEpisodes)
            }
        }
    }
    fun startLoadOrigin(){
        viewModelScope.launch(Dispatchers.IO) {
            if (_character.value?.origin?.url!=null && _character.value?.origin?.url?.length!! >2){
                getLocation.execute(_character.value?.origin?.url!!).let {
                    if (it!=null){
                        _origin.postValue(it)
                    }
                }
            }
        }
    }
    fun startLoadLocation(){
        viewModelScope.launch(Dispatchers.IO) {
            if (_character.value?.location?.url!=null && _character.value?.location?.url?.length!!>2){
                getLocation.execute(_character.value?.location?.url!!).let {
                    if (it!=null){
                       _location.postValue(it)
                        }
                    }
                }
            }
        }
    }