package com.myapplication.finalproject.featureLocation.di

import android.content.Context
import com.example.daggerlecture2023.core.di.ViewModelFactoryModule
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.app.core.di.CharactersViewModelModule
import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.featureChararcters.di.DaggerCharactersComponent
import com.myapplication.finalproject.featureChararcters.di.DataModuleCharacters
import com.myapplication.finalproject.featureChararcters.di.DomainModuleCharacters
import com.myapplication.finalproject.featureChararcters.presentation.CharactersFragment
import com.myapplication.finalproject.featureChararcters.presentation.DetailCharacterFragment
import com.myapplication.finalproject.featureLocation.presentation.DetailLocationFragment
import com.myapplication.finalproject.featureLocation.presentation.LocationsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, LocationViewModelModule::class,
            DomainModuleLocations::class, DataModuleLocations::class])
interface LocationsComponent:ProviderGetLocation {
    fun inject(mainActivity: MainActivity)
    fun inject(locationsFragment: LocationsFragment)
    fun inject(detailLocationFragment: DetailLocationFragment)
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): LocationsComponent
    }
    companion object{
        fun init(context: Context): LocationsComponent {
            return DaggerLocationsComponent.factory().create(context)
        }
    }
}