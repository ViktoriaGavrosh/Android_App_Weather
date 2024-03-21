package com.viktoriagavrosh.weather

import android.app.Application
import com.viktoriagavrosh.weather.data.AppContainer
import com.viktoriagavrosh.weather.data.DefaultAppContainer

class WeatherApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}