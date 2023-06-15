package com.myapplication.finalproject.app.app

import android.app.Application
import com.myapplication.finalproject.featureChararcters.di.CharactersComponent

class MainApp:Application(),App {
    private var appComponent: CharactersComponent?= null
    override fun appComponent(): CharactersComponent {
        return getAppComponent()
    }
    private fun getAppComponent(): CharactersComponent {
        if (appComponent == null) {
            appComponent = CharactersComponent.init(applicationContext)
        }
        return appComponent!!
    }
}