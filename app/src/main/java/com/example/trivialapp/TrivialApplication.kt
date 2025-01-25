package com.example.trivialapp

import android.app.Application
import com.example.trivialapp.data.AppContainer
import com.example.trivialapp.data.DefaultAppContainer

class TrivialApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}