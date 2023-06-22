package com.myapplication.finalproject.ComponentForMissCircularDependency

import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase

interface ProviderGetCharacter {
    fun getCharacter():GetCharacterWebUseCase
}