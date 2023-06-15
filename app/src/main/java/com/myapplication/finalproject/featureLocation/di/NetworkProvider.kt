package com.myapplication.finalproject.featureLocation.di

import retrofit2.Retrofit

interface NetworkProvider {

    fun provideRetrofit(): Retrofit
}