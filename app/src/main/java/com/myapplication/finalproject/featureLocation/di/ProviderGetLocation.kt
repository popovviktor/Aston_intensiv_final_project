package com.myapplication.finalproject.featureLocation.di

import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationWebUseCase

interface ProviderGetLocation {
    fun getLocation():GetLocationWebUseCase
}