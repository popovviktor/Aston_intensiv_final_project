package com.myapplication.finalproject.featureEpisodes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodeDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.models.InfoEpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromDBUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesNewPageUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.SaveEpisodesInDBUseCase
import com.myapplication.finalproject.featureEpisodes.presentation.models.ParamsFilterEpisodesForDBFind
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesFromWebUseCase,
    private val saveEpisodesInDbUseCase: SaveEpisodesInDBUseCase,
    private val getEpisodesFromDbUseCase: GetEpisodesFromDBUseCase,
    private val getEpisodesNewPageUseCase: GetEpisodesNewPageUseCase
):ViewModel() {


    private val _stateEndLoadingRefresh= MutableStateFlow<Boolean>(false)
    val stateEndLoadingRefresh: StateFlow<Boolean> = _stateEndLoadingRefresh.asStateFlow()
    private val _stateEndLoadingNextPage = MutableStateFlow<Boolean>(false)
    val stateEndLoadingNextPage: StateFlow<Boolean> = _stateEndLoadingNextPage.asStateFlow()
    private val _episodes = MutableLiveData<EpisodesDomain>()
    val episodes: LiveData<EpisodesDomain>
        get() = _episodes
    private val _isFilterEnable= MutableStateFlow<Boolean>(false)
    private val _informationToast= MutableStateFlow<String>("default")
    val informationToast: StateFlow<String> = _informationToast.asStateFlow()
    val isFilterEnable: StateFlow<Boolean> = _isFilterEnable.asStateFlow()
    private var defultUrlForFilterFind:String? = null
    private var paramsFilterFromDBEpisodes: ParamsFilterEpisodesForDBFind? = null
    fun setStateLoadNextEnd(){
        _stateEndLoadingNextPage.value = false
    }
    fun setStateLoadRefreshDisable(){
        _stateEndLoadingRefresh.value = false
    }
    fun getDefaultUrlForFindWithFilter(params: ParamsFilterEpisodesForDBFind){
        val urlForFindFilter:StringBuilder = StringBuilder("https://rickandmortyapi.com/api/episode/?")
        if (params.name!=null){
            urlForFindFilter.append("name=")
            urlForFindFilter.append(params.name+"&")
        }
        if (params.episode!=null){
            urlForFindFilter.append("episode=")
            urlForFindFilter.append(params.episode+"&")
        }

        paramsFilterFromDBEpisodes = ParamsFilterEpisodesForDBFind(params.name,params.episode)
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
            getEpisodesUseCase.execute().let {
                if (it!=null){
                    _episodes.postValue(it)
                    saveInDb(it)
                }else{
                    _informationToast.value = "error internet"
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
            getEpisodesNewPageUseCase.execute(urlDefaultPageWithFilter).let {
                if (it!=null){
                    _episodes.postValue(it)
                }
                else{
                    _informationToast.value = "error internet"
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
        getEpisodesFromDbUseCase.execute().let {
            if (it!=null){
                if (_isFilterEnable.value==true&&paramsFilterFromDBEpisodes!=null){
                    findfilterFromDB(it,paramsFilterFromDBEpisodes!!).let {
                        _episodes.postValue(it)
                    }
                }else{
                    _episodes.value = it
                }
            }else{
                _informationToast.value = "not found in cache"
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
            _informationToast.value = "this is last page"
            _stateEndLoadingNextPage.value = true
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                getEpisodesNewPageUseCase.execute(url).let {
                    if (it!=null){
                        _episodes.value?.info?.next = it.info?.next
                        _episodes.value?.results?.addAll(it.results!!)
                        if (_isFilterEnable.value!=true){
                            saveInDb(it)
                        }
                    }
                    else{
                        _informationToast.value = "Error internet"
                    }
                    _stateEndLoadingNextPage.value = true
                    if (_stateEndLoadingRefresh.value!=true && _isFilterEnable.value ==true){
                        _stateEndLoadingRefresh.value = true
                    }
                }

            }
        }
    }
    fun findfilterFromDB(episodesFromDb: EpisodesDomain, paramsFilter: ParamsFilterEpisodesForDBFind)
            : EpisodesDomain {
        val foundCharacters = ArrayList<EpisodeDomain>()
        val info = InfoEpisodesDomain(next = null, prev = null)
        for (elem in episodesFromDb.results!!){
            val boolFiltersItem = ArrayList<Boolean>()
            if (paramsFilter.name!=null){
                boolFiltersItem.add(elem.name?.lowercase()!!.contains(paramsFilter.name.lowercase()))
            }
            if (paramsFilter.episode!=null){
                boolFiltersItem.add(elem.episode?.lowercase()!!.contains(paramsFilter.episode.lowercase()))
            }
            if (!boolFiltersItem.contains(false)){
                foundCharacters.add(elem)
            }
        }
        return EpisodesDomain(info = info, results = foundCharacters)
    }
    fun resetEnableFilterFind() {
        if (_isFilterEnable.value==true){
            _isFilterEnable.value = false
        }
    }
    fun saveInDb(episodesDomain: EpisodesDomain){
        viewModelScope.launch(Dispatchers.IO){
            saveEpisodesInDbUseCase.execute(episodesDomain)
        }
    }
}