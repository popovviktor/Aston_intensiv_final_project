package com.myapplication.finalproject.featureLocation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.finalproject.featureLocation.domain.models.InfoLocationPageDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationDomain
import com.myapplication.finalproject.featureLocation.domain.models.LocationsDomain
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsFromDBUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsFromWebUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsNewPageUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.SaveLocationsInDBUseCase
import com.myapplication.finalproject.featureLocation.presentation.models.ParamsFilterLocations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsFromWebUseCase,
    private val saveLocationsInDbUseCase: SaveLocationsInDBUseCase,
    private val getLocationsFromDbUseCase: GetLocationsFromDBUseCase,
    private val getLocationsNewPageUseCase: GetLocationsNewPageUseCase
):ViewModel() {

    private val _stateEndLoadingRefresh= MutableStateFlow<Boolean>(false)
    val stateEndLoadingRefresh: StateFlow<Boolean> = _stateEndLoadingRefresh.asStateFlow()
    private val _stateEndLoadingNextPage = MutableStateFlow<Boolean>(false)
    val stateEndLoadingNextPage: StateFlow<Boolean> = _stateEndLoadingNextPage.asStateFlow()
    private val _locations = MutableLiveData<LocationsDomain>()
    val locations: LiveData<LocationsDomain>
        get() = _locations
    private val _isFilterEnable= MutableStateFlow<Boolean>(false)
    private val _informationToast= MutableStateFlow<String>("default")
    val informationToast: StateFlow<String> = _informationToast.asStateFlow()
    val isFilterEnable: StateFlow<Boolean> = _isFilterEnable.asStateFlow()
    private var defultUrlForFilterFind:String? = null
    private var paramsFilterFromDBLocations: ParamsFilterLocations? = null
    fun setStateLoadNextEnd(){
        _stateEndLoadingNextPage.value = false
    }
    fun setStateLoadRefreshDisable(){
        _stateEndLoadingRefresh.value = false
    }
    fun getDefaultUrlForFindWithFilter(params:ParamsFilterLocations){
        val urlForFindFilter:StringBuilder = StringBuilder("https://rickandmortyapi.com/api/location/?")
        if (params.name!=null){
            urlForFindFilter.append("name=")
            urlForFindFilter.append(params.name+"&")
        }
        if (params.type!=null){
            urlForFindFilter.append("type=")
            urlForFindFilter.append(params.type+"&")
        }
        if (params.dimension!=null){
            urlForFindFilter.append("dimension=")
            urlForFindFilter.append(params.dimension+"&")
        }

        paramsFilterFromDBLocations = ParamsFilterLocations(params.name,params.type,params.dimension)
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
            getLocationsUseCase.execute().let {
                if (it!=null){
                    _locations.postValue(it)
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
            getLocationsNewPageUseCase.execute(urlDefaultPageWithFilter).let {
                if (it!=null){
                    _locations.postValue(it)
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
        getLocationsFromDbUseCase.execute().let {
            if (it!=null){
                if (_isFilterEnable.value==true&&paramsFilterFromDBLocations!=null){
                    findfilterFromDB(it,paramsFilterFromDBLocations!!).let {
                        _locations.postValue(it)
                    }
                }else{
                    _locations.value = it
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
                getLocationsNewPageUseCase.execute(url).let {
                    if (it!=null){
                        _locations.value?.info?.next = it.info?.next
                        _locations.value?.results?.addAll(it.results!!)
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
    fun findfilterFromDB(locationsFromDb: LocationsDomain, paramsFilter: ParamsFilterLocations)
            : LocationsDomain {
        val foundCharacters = ArrayList<LocationDomain>()
        val info = InfoLocationPageDomain(next = null, prev = null)
        for (elem in locationsFromDb.results!!){
            val boolFiltersItem = ArrayList<Boolean>()
            if (paramsFilter.name!=null){
                boolFiltersItem.add(elem.name?.lowercase()!!.contains(paramsFilter.name.lowercase()))
            }
            if (!boolFiltersItem.contains(false)){
                foundCharacters.add(elem)
            }
        }
        return LocationsDomain(info = info, results = foundCharacters)
    }
    fun resetEnableFilterFind() {
        if (_isFilterEnable.value==true){
            _isFilterEnable.value = false
        }
    }
    fun saveInDb(locationsDomain: LocationsDomain){
        viewModelScope.launch(Dispatchers.IO){
            saveLocationsInDbUseCase.execute(locationsDomain)
        }
    }
}