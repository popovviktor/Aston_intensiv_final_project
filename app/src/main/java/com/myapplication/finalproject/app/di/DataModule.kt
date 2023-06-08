package com.myapplication.finalproject.app.di

import android.app.Application
import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myapplication.finalproject.data.AppDb.AppDataBase
import com.myapplication.finalproject.data.api.ApiService
import com.myapplication.finalproject.data.api.RemoteDataSource
import com.myapplication.finalproject.data.dao.CharactersDao
import com.myapplication.finalproject.data.repository.RepositoryImpl
import com.myapplication.finalproject.domain.repository.Repository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

import javax.inject.Singleton

@Module
class DataModule() {
    @Singleton
    @Provides
    fun provideGson():Gson{
        return GsonBuilder().create()
    }
    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson):GsonConverterFactory{
        return GsonConverterFactory.create(gson)
    }
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,dao: AppDataBase):Repository{
        return RepositoryImpl(remoteDataSource,dao.getCharactersDao())
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService):RemoteDataSource{
        return RemoteDataSource(apiService)
    }


}