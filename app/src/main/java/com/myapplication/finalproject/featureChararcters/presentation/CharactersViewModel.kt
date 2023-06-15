package com.myapplication.finalproject.featureChararcters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.SaveCharactersInDbUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase,
                                              private val saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
                                              private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
                                              private val getCharactersNewPageUseCase: GetCharactersNewPageUseCase
):ViewModel(){
    val isLoading:MutableLiveData<Boolean> = MutableLiveData(false)
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
    fun loadNewPage(url:String){
        if (isLoading.value==false){
            isLoading.value = true
            viewModelScope.launch(Dispatchers.Main) {
                getCharactersNewPageUseCase.execute(url).let {
                    isLoading.value = false
                    if (it!=null){
                        println("asdasd")
                        println("22222")
                        println("2222")
                        println(it)
                        liveCharsive.value = it
                    }
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

