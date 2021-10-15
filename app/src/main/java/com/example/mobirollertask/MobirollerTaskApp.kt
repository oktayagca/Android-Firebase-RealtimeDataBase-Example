package com.example.mobirollertask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobirollerTaskApp:Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}