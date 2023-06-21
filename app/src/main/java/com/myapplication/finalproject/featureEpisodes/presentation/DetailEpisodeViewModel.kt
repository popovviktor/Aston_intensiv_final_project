package com.myapplication.finalproject.featureEpisodes.presentation

import androidx.lifecycle.ViewModel
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromDBUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesFromWebUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.GetEpisodesNewPageUseCase
import com.myapplication.finalproject.featureEpisodes.domain.usecase.SaveEpisodesInDBUseCase
import javax.inject.Inject

class DetailEpisodeViewModel@Inject constructor(
    private val getEpisodesUseCase: GetEpisodesFromWebUseCase,
    private val saveEpisodesInDbUseCase: SaveEpisodesInDBUseCase,
    private val getEpisodesFromDbUseCase: GetEpisodesFromDBUseCase,
    private val getEpisodesNewPageUseCase: GetEpisodesNewPageUseCase
):ViewModel() {
}