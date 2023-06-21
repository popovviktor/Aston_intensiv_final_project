package com.myapplication.finalproject.featureLocation.di

import androidx.lifecycle.ViewModel
import com.example.daggerlecture2023.core.di.ViewModelKey
import com.myapplication.finalproject.featureChararcters.presentation.CharactersViewModel
import com.myapplication.finalproject.featureChararcters.presentation.DetailCharacterViewModel
import com.myapplication.finalproject.featureLocation.presentation.DetailLocationViewModel
import com.myapplication.finalproject.featureLocation.presentation.LocationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LocationViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    fun bindLocationViewModel(locationsViewModel: LocationsViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(DetailLocationViewModel::class)
    fun bindDetailCharacterViewModel(detailLocationViewModel: DetailLocationViewModel): ViewModel
}