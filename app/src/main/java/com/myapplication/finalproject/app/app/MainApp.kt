package com.myapplication.finalproject.app.app

import android.app.Application
import com.myapplication.finalproject.ComponentForMissCircularDependency.ComponentOther
import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.featureEpisodes.di.EpisodesComponent
import com.myapplication.finalproject.featureLocation.di.LocationsComponent

class MainApp:Application(),App {
    private var charactersComponent: CharactersComponent?= null
    private var locationsComponent:LocationsComponent? = null
    private var episodesComponent:EpisodesComponent? = null
    private var componentOther:ComponentOther?= null
    override fun charactersComponent(): CharactersComponent {
        return getCharactersComponent()
    }

    override fun locationsComponent(): LocationsComponent {
        return getLocationsComponent()
    }

    override fun episodesComponent(): EpisodesComponent {
        return getEpisodesComponent()
    }

    override fun componentOther(): ComponentOther {
        return getComponentOther()
    }
    private fun getComponentOther():ComponentOther{
        if (componentOther==null){
            componentOther = ComponentOther.init(applicationContext)
        }
        return componentOther!!
    }

    private fun getCharactersComponent(): CharactersComponent {
        if (charactersComponent == null) {
            charactersComponent = CharactersComponent.init(applicationContext)
        }
        return charactersComponent!!
    }
    private fun getLocationsComponent():LocationsComponent{
        if (locationsComponent==null){
            locationsComponent = LocationsComponent.init(applicationContext)
        }
        return locationsComponent!!
    }
    private fun getEpisodesComponent():EpisodesComponent{
        if (episodesComponent==null){
            episodesComponent = EpisodesComponent.init(applicationContext)
        }
        return episodesComponent!!
    }
}