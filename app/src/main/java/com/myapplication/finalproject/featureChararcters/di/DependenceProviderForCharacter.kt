package com.myapplication.finalproject.featureChararcters.di

import com.myapplication.finalproject.featureEpisodes.di.ProviderGetEpisode
import com.myapplication.finalproject.featureLocation.di.ProviderGetLocation

interface DependenceProviderForCharacter: ProviderGetEpisode,
    ProviderGetLocation