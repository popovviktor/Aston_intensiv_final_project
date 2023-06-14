package com.myapplication.finalproject.app.app


import android.app.Application
import com.myapplication.finalproject.app.di.AppComponent
import com.myapplication.finalproject.app.di.AppModule
import com.myapplication.finalproject.app.di.DataModule


interface App {

    fun appComponent(): AppComponent
}