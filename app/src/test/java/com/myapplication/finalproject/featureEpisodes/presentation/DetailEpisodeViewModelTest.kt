package com.myapplication.finalproject.featureEpisodes.presentation

import com.myapplication.finalproject.featureChararcters.domain.models.CharacterDomain
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharacterWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersWithoutInfoPageWeb
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodeFromWebUseCase
import com.myapplication.finalproject.featureLocation.domain.usecase.GetLocationWebUseCase
import io.kotest.assertions.timing.eventually
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldNot
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
@OptIn(ExperimentalCoroutinesApi::class)
class DetailEpisodeViewModelTest: BehaviorSpec({
    isolationMode = IsolationMode.InstancePerLeaf
    Dispatchers.setMain(Dispatchers.Default)
     val getEpisode = mockk<GetEpisodeFromWebUseCase>()
     val getCharacterUseCase = mockk<GetCharacterWebUseCase>()
     val getCharactersWithoutInfoPageWeb = mockk<GetCharactersWithoutInfoPageWeb>()
     val detailEpisodeViewModel = DetailEpisodeViewModel(getEpisode,getCharacterUseCase,getCharactersWithoutInfoPageWeb)
     val defUrl = "defUrl"
    Given("check load episode"){
        coEvery { getEpisode.execute(defUrl)  } returns null
        When("detailVMEpisode.startloadEpisode "){
            eventually {detailEpisodeViewModel.startLoadDetailEpisode(defUrl)  }
            Then("should call getEpisode"){
                coVerifySequence {
                   getEpisode.execute(defUrl)
                }
            }
        }
    }
    Given("check load characters"){
        coEvery { getCharactersWithoutInfoPageWeb.execute(defUrl)  } returns null
        coEvery { getCharacterUseCase.execute(defUrl)  } returns null
        When("detailVMEpisode.startLoadCharacters"){
            eventually {detailEpisodeViewModel.startLoadCharacters(defUrl)  }
            Then("Should call getCharactersWithoutInfoPageWeb")
                coVerifySequence {
                    getCharactersWithoutInfoPageWeb.execute(defUrl)
                    getCharacterUseCase.execute(defUrl)
                }
        }
    }

})