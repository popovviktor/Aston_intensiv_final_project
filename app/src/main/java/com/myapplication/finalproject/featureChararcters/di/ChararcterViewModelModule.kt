package com.myapplication.finalproject.app.core.di

import androidx.lifecycle.ViewModel
import com.example.daggerlecture2023.core.di.ViewModelKey
import com.myapplication.finalproject.featureChararcters.presentation.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CharactersViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindProfileViewModel(characterViewModel: CharactersViewModel): ViewModel
}