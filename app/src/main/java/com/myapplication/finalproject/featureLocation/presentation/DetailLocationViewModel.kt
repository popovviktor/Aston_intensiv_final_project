package com.myapplication.finalproject.featureLocation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersWithoutInfoPageWeb
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailLocationViewModel @Inject constructor(
    private val getLocation:GetLocationWebUseCase,
    private val getCharacterUseCase: GetCharacterWebUseCase,
    private val getCharactersWithoutInfoPageWeb: GetCharactersWithoutInfoPageWeb
):ViewModel() {
    private val _characters = MutableLiveData<ArrayList<CharacterDomain>>()
    val characters:LiveData<ArrayList<CharacterDomain>>
        get() = _characters
    private val _location = MutableLiveData<LocationDomain>()
    val location: LiveData<LocationDomain>
        get() = _location
    fun startLoadDetailLocation(url:String){
        viewModelScope.launch(Dispatchers.IO) {
            getLocation.execute(url).let {
                if (it!=null){
                    _location.postValue(it)
                }
            }
        }
    }
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