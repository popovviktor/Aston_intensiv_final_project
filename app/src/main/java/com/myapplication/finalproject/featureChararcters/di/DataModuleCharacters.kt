package com.myapplication.finalproject.featureChararcters.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myapplication.finalproject.featureChararcters.data.AppDb.AppDataBaseCharacters
import com.myapplication.finalproject.featureChararcters.data.api.ApiServiceCharacters
import com.myapplication.finalproject.featureChararcters.data.api.RemoteDataSourceCharacters
import com.myapplication.finalproject.featureChararcters.data.repository.RepositoryCharactersImpl
import com.myapplication.finalproject.featureChararcters.domain.repository.RepositoryCharacters
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
class DataModuleCharacters() {
    @Provides
    fun provideAppDataBase(context: Context): AppDataBaseCharacters {
        return AppDataBaseCharacters.getAppDataBase(context.applicationContext)
    }
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
    fun provideApi(retrofit: Retrofit): ApiServiceCharacters {
        return retrofit.create(ApiServiceCharacters::class.java)
    }
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSourceCharacters, dao: AppDataBaseCharacters): RepositoryCharacters {
        return RepositoryCharactersImpl(remoteDataSource,dao.getCharactersDao())
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiServiceCharacters): RemoteDataSourceCharacters {
        return RemoteDataSourceCharacters(apiService)
    }


}