package com.myapplication.finalproject.app.app


import android.app.Application
import com.myapplication.finalproject.app.di.AppComponent
import com.myapplication.finalproject.app.di.AppModule
import com.myapplication.finalproject.app.di.DaggerAppComponent
import com.myapplication.finalproject.app.di.DataModule


class App:Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .build()

    }
}