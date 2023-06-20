package com.myapplication.finalproject.featureChararcters.presentation

import androidx.lifecycle.ViewModel
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.GetCharactersFromWebUseCase
import com.myapplication.finalproject.featureChararcters.domain.usecase.SaveCharactersInDbUseCase
import javax.inject.Inject

class DetailCharacterViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersFromWebUseCase,
                                                   private val saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
                                                   private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
                                                   private val getCharactersNewPageUseCase: GetCharactersNewPageUseCase
):ViewModel() {
}