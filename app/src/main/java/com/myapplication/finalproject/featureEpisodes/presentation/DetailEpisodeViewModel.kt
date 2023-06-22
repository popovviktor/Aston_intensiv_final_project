package com.myapplication.finalproject.featureEpisodes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailEpisodeViewModel@Inject constructor(
    private val getEpisode:GetEpisodeFromWebUseCase,
    private val getCharacterUseCase: GetCharacterWebUseCase
):ViewModel() {
    private val _episode = MutableLiveData<EpisodeDomain>()
    val episode: LiveData<EpisodeDomain>
        get() = _episode
    fun startLoadDetailEpisode(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            getEpisode.execute(url).let {
                if (it!=null){
                    _episode.postValue(it)
                    getCharacterUseCase.execute(it.characters.get(0)).let {
                        if (it!=null){
                            println(it)
                        }
                    }
                }
            }
        }}
}