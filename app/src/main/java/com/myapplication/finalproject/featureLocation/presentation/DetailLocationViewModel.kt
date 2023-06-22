package com.myapplication.finalproject.featureLocation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailLocationViewModel @Inject constructor(
    private val getLocation:GetLocationWebUseCase,
    private val getCharacterUseCase: GetCharacterWebUseCase
):ViewModel() {
    private val _location = MutableLiveData<LocationDomain>()
    val location: LiveData<LocationDomain>
        get() = _location
    fun startLoadDetailLocation(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            getLocation.execute(url).let {
                if (it!=null){
                    _location.postValue(it)
                    getCharacterUseCase.execute(it.residents.get(0)).let {
                        if (it!=null){
                            println(it)
                        }
                    }
                }
            }
        }}
}