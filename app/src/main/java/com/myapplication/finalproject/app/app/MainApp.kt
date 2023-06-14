package com.myapplication.finalproject.app.app

import android.app.Application
import com.myapplication.finalproject.app.di.AppComponent

class MainApp:Application(),App {
    private var appComponent:AppComponent?= null
    override fun appComponent(): AppComponent {
        return getAppComponent()
    }
    private fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = AppComponent.init(applicationContext)
        }
        return appComponent!!
    }
}