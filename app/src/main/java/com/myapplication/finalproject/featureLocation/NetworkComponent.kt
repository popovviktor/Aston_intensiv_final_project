package com.myapplication.finalproject.featureLocation

import com.myapplication.finalproject.app.MainActivity
import dagger.Component


@Component(
    dependencies =[NetworkProvider::class]
)
interface NetworkComponent:DependenciesProvider {
    @Component.Factory
    interface Factory{
        fun create(networkProvider: NetworkProvider):NetworkComponent
    }
    companion object{
        fun init():NetworkComponent{
            val networkProvider = NetworkComponent.init()
            return DaggerNetworkComponent.factory().create(networkProvider=networkProvider)
        }
    }

}