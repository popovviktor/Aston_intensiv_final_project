package com.myapplication.finalproject.featureEpisodes.presentation

import com.myapplication.finalproject.featureEpisodes.domain.models.EpisodesDomain
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromDBUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesNewPageUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.SaveEpisodesInDBUseCase
import io.kotest.assertions.timing.eventually
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodesViewModelTest : BehaviorSpec({
    isolationMode = IsolationMode.InstancePerLeaf
    Dispatchers.setMain(Dispatchers.Default)
    val getEpisodesUseCase= mockk<GetEpisodesFromWebUseCase>()
    val saveEpisodesInDbUseCase= mockk<SaveEpisodesInDBUseCase>()
    val getEpisodesFromDbUseCase= mockk<GetEpisodesFromDBUseCase>()
    val getEpisodesNewPageUseCase= mockk<GetEpisodesNewPageUseCase>()
    val episodesViewModel = EpisodesViewModel(getEpisodesUseCase = getEpisodesUseCase,
        saveEpisodesInDbUseCase = saveEpisodesInDbUseCase,
        getEpisodesFromDbUseCase = getEpisodesFromDbUseCase,
        getEpisodesNewPageUseCase=getEpisodesNewPageUseCase)
    val testGetEpisodesFromEthernet = mockk<EpisodesDomain>() as EpisodesDomain?
    Given("check get episodes from Cache after getWeb with Ehernet disable default"){
        coEvery { getEpisodesUseCase.execute() } returns null
        coEvery { getEpisodesFromDbUseCase.execute() } returns null
        When("load default page"){
            eventually{episodesViewModel.getDefaultPage()}
            Then("should call getEpisodesFromDbUseCase"){
                coVerifySequence {
                    getEpisodesUseCase.execute()
                }
            }
        }
    }
    //not correct in work... truble
    Given("check save episodes in Cache after fortunately getWeb "){
        coEvery { getEpisodesUseCase.execute() } returns testGetEpisodesFromEthernet
        coEvery { saveEpisodesInDbUseCase.execute(testGetEpisodesFromEthernet!!) } returns Unit
        When("load default page"){
            eventually{episodesViewModel.getDefaultPage()}
            Then("should call saveEpisodesInDbUseCase"){
                coVerifySequence {
                    saveEpisodesInDbUseCase.execute(testGetEpisodesFromEthernet!!)
                }
            }
        }
    }


})
