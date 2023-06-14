package com.myapplication.finalproject.app.di

import android.content.Context
import com.myapplication.finalproject.app.MainViewModelFactory
import com.myapplication.finalproject.data.AppDb.AppDataBase
import com.myapplication.finalproject.domain.usecase.GetCharactersFromDbUseCase
import com.myapplication.finalproject.domain.usecase.GetCharactersNewPageUseCase
import com.myapplication.finalproject.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.domain.usecase.SaveCharactersInDbUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {
    //@Provides
    //fun providesContext(): Context {
    //    return context
    //}

    @Singleton
    @Provides
    fun providesViewModel(getCharactersUseCaseFactory :GetCharactersUseCase,
    saveCharactersInDbUseCase: SaveCharactersInDbUseCase,
    getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
    getCharactersNewPageUseCase: GetCharactersNewPageUseCase):MainViewModelFactory{
        return MainViewModelFactory(getCharactersUseCaseFactory,saveCharactersInDbUseCase,
        getCharactersFromDbUseCase,getCharactersNewPageUseCase)
    }
}