package com.myapplication.finalproject.featureChararcters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.data.models.CharacterData
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.usecase.*
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodeFromWebUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationWebUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailCharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterWebUseCase,
    private val getEpisode:GetEpisodeFromWebUseCase,
    private val getLocation:GetLocationWebUseCase
    ):ViewModel() {
        private val _character = MutableLiveData<CharacterDomain>()
        val character:LiveData<CharacterDomain>
            get() = _character
    fun startLoadDetailCharacter(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterUseCase.execute(url).let {
                if (it!=null){
                    println(it)
                    _character.postValue(it)
                    getLocation.execute(it.origin?.url!!).let {
                        println(it)
                    }
                    getEpisode.execute(it.episode.get(0)).let {
                        if (it!=null){
                            println(it)
                        }
                    }
                }
            }
        }}
    }