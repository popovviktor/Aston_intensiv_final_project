package com.myapplication.finalproject.featureLocation

import com.myapplication.finalproject.featureLocation.api.ApiLocation
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {
    @Provides
    fun provideApiLocation(retrofit: Retrofit):ApiLocation{
        return retrofit.create(ApiLocation::class.java)
    }
}