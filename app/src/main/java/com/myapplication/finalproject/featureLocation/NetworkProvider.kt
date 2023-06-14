package com.myapplication.finalproject.featureLocation

import retrofit2.Retrofit

interface NetworkProvider {

    fun provideRetrofit(): Retrofit
}