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
import com.myapplication.finalproject.featureChararcters.presentation.model.LoadingPrev
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase,
                                              private val saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
                                              private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
                                              private val getCharactersNewPageUseCase: GetCharactersNewPageUseCase
):ViewModel(){
    private val _stateLoadingRefresh= MutableStateFlow<Boolean>(false)
        val stateLoadingPrev:StateFlow<Boolean> = _stateLoadingRefresh.asStateFlow()
    private val _stateLoadingNext = MutableStateFlow<Boolean>(false)
        val stateLoadingNext:StateFlow<Boolean> = _stateLoadingNext.asStateFlow()
    private val liveCharsive = MutableLiveData<CharactersDomain>()
    val _live:LiveData<CharactersDomain>
        get() = liveCharsive
    private val _characterPageForFilter = MutableLiveData<CharactersDomain>()
    val characterPageForFilter:LiveData<CharactersDomain>
        get() = _characterPageForFilter
    init {

    }
    fun setStateLoadNextEnd(){
        _stateLoadingNext.value = false
    }
    fun setStateLoadPrevEnd(){
        _stateLoadingRefresh.value = false
    }

    fun getInfo(){
        viewModelScope.launch(Dispatchers.Main) {
            getCharactersUseCase.execute().let {
                   if (it!=null){
                       liveCharsive.value = it
                       saveInDb(it)
                   }else{
                       println("нет интернета")
                       getPageFromDB()

                   }

                }

            }
        }
    suspend fun getPageFromDB(){
        getCharactersFromDbUseCase.execute().let {
            if (it!=null){
                liveCharsive.value = it
            }else{
                println("нет сохранненного кеша")
            }
        }
    }
    fun pullToRefresh(){
        //getInfo()
        setStateLoadPrevEnd()
        viewModelScope.launch(Dispatchers.Main) {
            getCharactersUseCase.execute().let {
                if (it!=null){
                    liveCharsive.value?.results?.clear()
                    liveCharsive.value = it
                    saveInDb(it)
                    _stateLoadingRefresh.value = true
                }else{
                    println("нет интернета")
                    getPageFromDB()

                }

            }

        }
    }
    fun loadNextPage(url:String){
        if (url==null||url=="null"){
            println("дошли до конца списка")
            _stateLoadingNext.value = true
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                    getCharactersNewPageUseCase.execute(url).let {
                    println("end loading")
                    if (it!=null){
                        liveCharsive.value?.info?.next = it.info?.next
                        liveCharsive.value?.results?.addAll(it.results!!)
                        println(liveCharsive.value?.info?.next)
                        _stateLoadingNext.value = true
                        saveInDb(it)
                    }
                    else{
                        println("eror internet")
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

