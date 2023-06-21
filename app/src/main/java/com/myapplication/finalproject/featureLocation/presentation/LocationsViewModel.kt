package com.myapplication.finalproject.featureLocation.presentation

import androidx.lifecycle.ViewModel
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.SaveCharactersInDbUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsFromDBUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsFromWebUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationsNewPageUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.SaveLocationsInDBUseCase
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsFromWebUseCase,
    private val saveLocationsInDbUseCase: SaveLocationsInDBUseCase,
    private val getLocationsFromDbUseCase: GetLocationsFromDBUseCase,
    private val getLocationsNewPageUseCase: GetLocationsNewPageUseCase
):ViewModel() {
}