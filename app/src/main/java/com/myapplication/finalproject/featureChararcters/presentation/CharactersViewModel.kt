package com.myapplication.finalproject.featureChararcters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.models.CharactersDomain
import com.myapplication.finalproject.featureChararcters.domain.models.Info
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.SaveCharactersInDbUseCase
import com.myapplication.finalproject.featureChararcters.presentation.model.LoadingPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase,
                                              private val saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
                                              private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
                                              private val getCharactersNewPageUseCase: GetCharactersNewPageUseCase
):ViewModel(){
    private val isLoading = MutableLiveData<LoadingPage>()
        val _isLoading:LiveData<LoadingPage>
            get() = isLoading
    private val liveCharsive = MutableLiveData<CharactersDomain>()
    val _live:LiveData<CharactersDomain>
        get() = liveCharsive
    private val characterPAge = MutableLiveData<ArrayList<CharacterDomain>>()
    val _characterPAge:LiveData<ArrayList<CharacterDomain>>
        get() = characterPAge
    private val infoPage = MutableLiveData<Info>()
        val _infoPAge:LiveData<Info>
        get() = infoPage
    init {

    }
    fun getInfo(){
        viewModelScope.launch(Dispatchers.Main) {
            getCharactersUseCase.execute().let {
                   if (it!=null){
                       liveCharsive.value = it
                       characterPAge.value = it.results!!
                       saveInDb(it)
                       println("1sdasd1")
                   }else{
                       println("1sssssss1")
                       getPageFromDB()

                   }

                }

            }
        }
    suspend fun getPageFromDB(){
        getCharactersFromDbUseCase.execute().let {
            if (it!=null){
                println(it)
                liveCharsive.value = it
                characterPAge.value = it.results!!
            }else{
                println("dont load in cache")
            }
        }
    }
    fun loadNewPage(url:String){
        if (url==null||url=="null"){
            println("дошли до конца списка")
            isLoading.postValue(LoadingPage(false,false))
        }else{
            viewModelScope.launch(Dispatchers.IO) {

                if (isLoading.value?.IsLoadingPrevPage!=true || isLoading.value?.IsLoadingNewPage!=true){
                   isLoading.postValue(LoadingPage(true,true))

                    getCharactersNewPageUseCase.execute(url).let {
                    println("end loading")
                    isLoading.postValue(LoadingPage(false,false))
                    if (it!=null){
                        println("asdasd")
                        println(it.results?.size!!)
                        liveCharsive.value?.info?.next = it.info?.next
                        println(liveCharsive.value?.info?.next)
                        characterPAge.value?.addAll(it.results!!)
                        saveInDb(it)
                    }
                    else{
                        println("eror internet")
                    }

                }
            }}
       }
    }
    fun saveInDb(charactersDomain: CharactersDomain){
        viewModelScope.launch(Dispatchers.IO){
            saveCharactersInDbUseCase.execute(charactersDomain)
        }

    }
    }

