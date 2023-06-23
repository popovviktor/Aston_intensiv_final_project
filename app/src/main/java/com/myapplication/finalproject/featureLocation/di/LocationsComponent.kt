package com.myapplication.finalproject.featureLocation.di

import android.content.Context
import com.example.daggerlecture2023.core.di.ViewModelFactoryModule
import com.myapplication.finalproject.ComponentForMissCircularDependency.ComponentOther
import com.myapplication.finalproject.ComponentForMissCircularDependency.ProviderGetCharacter
import com.myapplication.finalproject.app.MainActivity
import com.myapplication.finalproject.featureLocation.presentation.DetailLocationFragment
import com.myapplication.finalproject.featureLocation.presentation.LocationsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, LocationViewModelModule::class,
            DomainModuleLocations::class, DataModuleLocations::class],
            dependencies = [ProviderGetCharacter::class])
interface LocationsComponent:ProviderGetLocation ,DependencyProviderCharacterForLocation{
    fun inject(mainActivity: MainActivity)
    fun inject(locationsFragment: LocationsFragment)
    fun inject(detailLocationFragment: DetailLocationFragment)
    @Component.Factory
    interface Factory{
        fun create(
            providerCharacterForLocation:ProviderGetCharacter,@BindsInstance context: Context): LocationsComponent
    }
    companion object{
        fun init(context: Context): LocationsComponent {
            val providerCharacterForLocation = ComponentOther.init(context)
            return DaggerLocationsComponent.factory().create(
                providerCharacterForLocation,context)
        }
    }
}