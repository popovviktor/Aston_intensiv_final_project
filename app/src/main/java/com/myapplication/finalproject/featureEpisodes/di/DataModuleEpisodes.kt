package com.myapplication.finalproject.featureEpisodes.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myapplication.finalproject.featureEpisodes.data.AppDb.AppDataBaseEpisodes
import com.myapplication.finalproject.featureEpisodes.data.api.ApiServiceEpisodes
import com.myapplication.finalproject.featureEpisodes.data.api.RemoteDataSourceEpisodes
import com.myapplication.finalproject.featureEpisodes.data.repository.RepositoryEpisodesImpl
import com.myapplication.finalproject.featureEpisodes.domain.repository.RepositoryEpisodes
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModuleEpisodes {
    @Provides
    fun provideAppDataBase(context: Context): AppDataBaseEpisodes {
        return AppDataBaseEpisodes.getAppDataBase(context.applicationContext)
    }
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
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
    fun provideApi(retrofit: Retrofit): ApiServiceEpisodes {
        return retrofit.create(ApiServiceEpisodes::class.java)
    }
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSourceEpisodes, dao: AppDataBaseEpisodes): RepositoryEpisodes {
        return RepositoryEpisodesImpl(remoteDataSource,dao.getEpisodesDao())
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiServiceEpisodes): RemoteDataSourceEpisodes {
        return RemoteDataSourceEpisodes(apiService)
    }
}