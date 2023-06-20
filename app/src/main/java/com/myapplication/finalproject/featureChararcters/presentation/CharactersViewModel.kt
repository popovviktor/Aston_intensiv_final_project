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
import com.myapplication.finalproject.featureChararcters.presentation.model.ParamsFilterFromDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
    private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
    private val getCharactersNewPageUseCase: GetCharactersNewPageUseCase
):ViewModel(){
    private val _stateEndLoadingRefresh= MutableStateFlow<Boolean>(false)
        val stateEndLoadingRefresh:StateFlow<Boolean> = _stateEndLoadingRefresh.asStateFlow()
    private val _stateEndLoadingNextPage = MutableStateFlow<Boolean>(false)
        val stateEndLoadingNextPage:StateFlow<Boolean> = _stateEndLoadingNextPage.asStateFlow()
    private val _characters = MutableLiveData<CharactersDomain>()
    val characters:LiveData<CharactersDomain>
        get() = _characters
    private val _isFilterEnable=MutableStateFlow<Boolean>(false)
    val isFilterEnable:StateFlow<Boolean> = _isFilterEnable.asStateFlow()
    private var defultUrlForFilterFind:String? = null
    private var paramsFilterFromDB:ParamsFilterFromDb? = null
    fun setStateLoadNextEnd(){
        _stateEndLoadingNextPage.value = false
    }
    fun setStateLoadRefreshDisable(){
        _stateEndLoadingRefresh.value = false
    }
    fun getDefaultUrlForFindWithFilter(name:String?, status:String?,
                                       species:String?, type:String?, gender:String?){
        val urlForFindFilter:StringBuilder = StringBuilder("https://rickandmortyapi.com/api/character/?")
        if (name!=null){
            urlForFindFilter.append("name=")
            urlForFindFilter.append(name+"&")
        }
        if (status!=null){
            urlForFindFilter.append("status=")
            urlForFindFilter.append(status+"&")
        }
        if (type!=null){
            urlForFindFilter.append("type=")
            urlForFindFilter.append(type+"&")
        }
        if (species!=null){
            urlForFindFilter.append("species=")
            urlForFindFilter.append(species+"&")
        }
        if (type!=null){
            urlForFindFilter.append("type=")
            urlForFindFilter.append(type+"&")
        }
        if (gender!=null){
            urlForFindFilter.append("gender=")
            urlForFindFilter.append(gender+"&")
        }
        paramsFilterFromDB = ParamsFilterFromDb(name = name,status = status,
        species = species,type = type,gender = gender)
        defultUrlForFilterFind = urlForFindFilter.toString()
        getDefaultPageWithFilter(defultUrlForFilterFind!!)
        println(isFilterEnable)
        println(_isFilterEnable.value)

    }
    fun enableFilterFind(){
        _isFilterEnable.value = true
    }
    fun getDefaultPage(){
        viewModelScope.launch(Dispatchers.Main) {
            getCharactersUseCase.execute().let {
                   if (it!=null){
                       _characters.postValue(it)
                       saveInDb(it)
                   }else{
                       println("нет интернета")
                       getPageFromDB()
                   }
                   if (_stateEndLoadingRefresh.value!=true){
                       _stateEndLoadingRefresh.value = true
                   }
                }
            }
        }
    fun getDefaultPageWithFilter(urlDefaultPageWithFilter:String){
        viewModelScope.launch(Dispatchers.IO) {
            getCharactersNewPageUseCase.execute(urlDefaultPageWithFilter).let {
                if (it!=null){
                    _characters.postValue(it)
                }
                else{
                    println("eror internet")
                    getPageFromDB()

                }
                _stateEndLoadingNextPage.value = true
                if (_stateEndLoadingRefresh.value!=true && _isFilterEnable.value ==true){
                    _stateEndLoadingRefresh.value = true
                }
            }
        }
    }
    suspend fun getPageFromDB(){
        getCharactersFromDbUseCase.execute().let {
            if (it!=null){
                if (_isFilterEnable.value==true&&paramsFilterFromDB!=null){
                    findfilterFromDB(it,paramsFilterFromDB!!).let {
                        _characters.postValue(it)
                    }
                }else{
                    _characters.value = it
                }
            }else{
                println("нет сохранненного кеша")
            }
        }
    }
    fun nullableDefaultUrlAfterClosFilter(){
        defultUrlForFilterFind = null
    }
    fun pullToRefresh(){
        if (_isFilterEnable.value==true&&defultUrlForFilterFind!=null){
            getDefaultPageWithFilter(defultUrlForFilterFind!!)
        }else{
            getDefaultPage()
        }
    }
    fun loadNextPage(url:String){
        if (url==null||url=="null"){
            println("дошли до конца списка")
            _stateEndLoadingNextPage.value = true
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                    getCharactersNewPageUseCase.execute(url).let {
                    println("end loading")
                    if (it!=null){
                        _characters.value?.info?.next = it.info?.next
                        _characters.value?.results?.addAll(it.results!!)
                        if (_isFilterEnable.value!=true){
                            saveInDb(it)
                        }
                    }
                    else{
                        println("eror internet")
                    }
                    _stateEndLoadingNextPage.value = true
                    if (_stateEndLoadingRefresh.value!=true && _isFilterEnable.value ==true){
                        _stateEndLoadingRefresh.value = true
                    }
                }

            }
       }
    }
    fun findfilterFromDB(charactersFromDb: CharactersDomain,paramsFilter: ParamsFilterFromDb)
    :CharactersDomain{
        val foundCharacters = ArrayList<CharacterDomain>()
        val info = Info(next = null, prev = null)
        for (elem in charactersFromDb.results!!){
            val boolFiltersItem = ArrayList<Boolean>()
            if (paramsFilter.name!=null){
                boolFiltersItem.add(elem.name?.lowercase()!!.contains(paramsFilter.name.lowercase()))
            }
            if (paramsFilter.status!=null){
                boolFiltersItem.add(elem.status?.lowercase().equals(paramsFilter.status.lowercase()))
            }
            if (paramsFilter.species!=null){
                boolFiltersItem.add(elem.species?.lowercase()!!.contains(paramsFilter.species.lowercase()))
            }
            if (paramsFilter.type!=null){
                boolFiltersItem.add(elem.type?.lowercase()!!.contains(paramsFilter.type.lowercase()))
            }
            if (paramsFilter.gender!=null){
                boolFiltersItem.add(elem.gender?.lowercase().equals(paramsFilter.gender.lowercase()))
            }
            if (!boolFiltersItem.contains(false)){
                foundCharacters.add(elem)
            }
        }
        return CharactersDomain(info = info, results = foundCharacters)
    }
    fun resetEnableFilterFind() {
        if (_isFilterEnable.value==true){
            _isFilterEnable.value = false
        }
    }
    fun saveInDb(charactersDomain: CharactersDomain){
        viewModelScope.launch(Dispatchers.IO){
            saveCharactersInDbUseCase.execute(charactersDomain)
        }
    }
    }

