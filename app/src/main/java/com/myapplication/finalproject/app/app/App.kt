package com.myapplication.finalproject.app.app


import com.myapplication.finalproject.featureChararcters.di.CharactersComponent
import com.myapplication.finalproject.featureEpisodes.di.EpisodesComponent
import com.myapplication.finalproject.featureLocation.di.LocationsComponent


interface App {

    fun charactersComponent(): CharactersComponent
    fun locationsComponent():LocationsComponent
    fun episodesComponent():EpisodesComponent
}