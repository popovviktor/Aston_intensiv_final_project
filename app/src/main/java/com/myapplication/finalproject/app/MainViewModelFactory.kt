package com.myapplication.finalproject.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.finalproject.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.domain.usecase.SaveCharactersInDbUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase,
private val saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
private val getCharactersNewPageUseCase: GetCharactersNewPageUseCase):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCharactersUseCase = getCharactersUseCase,saveCharactersInDbUseCase = saveCharactersInDbUseCase,
        getCharactersFromDbUseCase = getCharactersFromDbUseCase,getCharactersNewPageUseCase) as T
    }
}