package com.myapplication.finalproject.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.finalproject.domain.usecase.GetCharactersUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase,):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCharactersUseCase = getCharactersUseCase) as T
    }
}