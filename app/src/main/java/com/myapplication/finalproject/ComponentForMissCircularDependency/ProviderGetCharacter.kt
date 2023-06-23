package com.myapplication.finalproject.ComponentForMissCircularDependency

import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersWithoutInfoPageWeb

interface ProviderGetCharacter {
    fun getCharacter():GetCharacterWebUseCase
    fun getCharactersWithoutInfoPage():GetCharactersWithoutInfoPageWeb
}