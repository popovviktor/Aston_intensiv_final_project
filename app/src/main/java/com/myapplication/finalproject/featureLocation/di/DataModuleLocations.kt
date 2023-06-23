package com.myapplication.finalproject.featureLocation.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myapplication.finalproject.featureLocation.data.AppDb.AppDataBaseLocations
import com.myapplication.finalproject.featureLocation.data.api.ApiServiceLocations
import com.myapplication.finalproject.featureLocation.data.api.RemoteDataSourceLocations
import com.myapplication.finalproject.featureLocation.data.repository.RepositoryLocationsImpl
import com.myapplication.finalproject.featureLocation.domain.repository.RepositoryLocations
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModuleLocations {
    @Provides
    fun provideAppDataBase(context: Context): AppDataBaseLocations {
        return AppDataBaseLocations.getAppDataBase(context.applicationContext)
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
    fun provideApi(retrofit: Retrofit): ApiServiceLocations {
        return retrofit.create(ApiServiceLocations::class.java)
    }
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSourceLocations, dao: AppDataBaseLocations): RepositoryLocations {
        return RepositoryLocationsImpl(remoteDataSource,dao.getLocationsDao())
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiServiceLocations): RemoteDataSourceLocations {
        return RemoteDataSourceLocations(apiService)
    }
}