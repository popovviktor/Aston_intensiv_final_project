package com.myapplication.finalproject.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.domain.models.CharactersDomain
import com.myapplication.finalproject.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.domain.usecase.SaveCharactersInDbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val getCharactersUseCase: GetCharactersUseCase,
private val saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase):ViewModel(){
    private val liveCharsive = MutableLiveData<CharactersDomain>()
    val _live:LiveData<CharactersDomain>
        get() = liveCharsive
    fun getInfo(){
        viewModelScope.launch(Dispatchers.Main) {
            getCharactersUseCase.execute()?.let {
                   if (it!=null){
                       liveCharsive.value = it
                       println("sdasd")
                   }else{
                       println("sssssss")
                   }

                }
            getCharactersFromDbUseCase.execute()?.let {
                if (it!=null){
                    println(it)
                }
            }
            }
        }
    fun saveInDb(charactersDomain: CharactersDomain){
        viewModelScope.launch(Dispatchers.IO){
            saveCharactersInDbUseCase.execute(charactersDomain)
        }

    }
    }
