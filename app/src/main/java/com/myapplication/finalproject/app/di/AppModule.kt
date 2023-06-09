package com.myapplication.finalproject.app.di

import android.content.Context
import com.myapplication.finalproject.app.MainViewModelFactory
import com.myapplication.finalproject.data.AppDb.AppDataBase
import com.myapplication.finalproject.domain.usecase.GetCharactersUseCase
import com.myapplication.finalproject.domain.usecase.SaveCharactersInDbUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    fun providesContext(): Context {
        return context
    }
    @Provides
    fun provideAppDataBase(context: Context): AppDataBase {
        return AppDataBase.getAppDataBase(context.applicationContext)
    }
    @Singleton
    @Provides
    fun providesViewModel(getCharactersUseCaseFactory :GetCharactersUseCase,
    saveCharactersInDbUseCase: SaveCharactersInDbUseCase):MainViewModelFactory{
        return MainViewModelFactory(getCharactersUseCaseFactory,saveCharactersInDbUseCase)
    }
}