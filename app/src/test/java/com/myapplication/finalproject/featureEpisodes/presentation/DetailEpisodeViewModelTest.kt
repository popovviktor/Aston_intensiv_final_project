package com.myapplication.finalproject.featureEpisodes.presentation

import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersWithoutInfoPageWeb
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodeFromWebUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationWebUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk

class DetailEpisodeViewModelTest: BehaviorSpec({
     val getEpisode = mockk<GetEpisodeFromWebUseCase>()
     val getCharacterUseCase = mockk<GetCharacterWebUseCase>()
     val getCharactersWithoutInfoPageWeb = mockk<GetCharactersWithoutInfoPageWeb>()
     val detailEpisodeViewModel = DetailEpisodeViewModel(getEpisode,getCharacterUseCase,getCharactersWithoutInfoPageWeb)

    Given("check load episode"){
        //every { getEpisode.execute("ss") } returns null
        When(""){
            val actualResult = detailEpisodeViewModel.startLoadDetailEpisode("ss")
            Then(""){

            }
        }
    }
})