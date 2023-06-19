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
    private val _stateLoadingPrev= MutableStateFlow<LoadingPrev>(LoadingPrev(null,null))
        val stateLoadingPrev:StateFlow<LoadingPrev> = _stateLoadingPrev.asStateFlow()
    private val _stateLoadingNext = MutableStateFlow<Boolean>(false)
        val stateLoadingNext:StateFlow<Boolean> = _stateLoadingNext.asStateFlow()
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
    fun setStateLoadNextEnd(){
        _stateLoadingNext.value = false
    }
    fun setStateLoadPrevEnd(){
        _stateLoadingPrev.value = LoadingPrev(null,null)
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
                       println("нет интернета")
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
                println("нет сохранненного кеша")
            }
        }
    }
    fun loadPrevPage(url:String){
        if (url==null||url=="null"){
            println("дошли до конца списка")
            _stateLoadingPrev.value = LoadingPrev(true,0)
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                    getCharactersNewPageUseCase.execute(url).let {
                        if (it!=null){
                            liveCharsive.value?.info?.prev = it.info?.prev
                            val def = characterPAge.value
                            characterPAge.value?.addAll(0,it.results!!)
                            liveCharsive.value?.results = characterPAge.value
                            //(it.results!!)
                            _stateLoadingPrev.value = LoadingPrev(true,it.results!!.size)
                            saveInDb(it)
                        }
                        else{
                            println("eror internet")
                        }

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
                        println(liveCharsive.value?.info?.next)
                        characterPAge.value?.addAll(it.results!!)
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

